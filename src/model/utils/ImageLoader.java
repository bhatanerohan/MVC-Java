package model.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * Class to implement functionality to load images.
 */
public class ImageLoader {

  private BufferedImage readJpgPNGImage(String imagePath) {
    File imageFile = new File(imagePath);
    BufferedImage image = null;
    try {
      image = ImageIO.read(imageFile);
      return image;
    } catch (IOException e) {
      System.err.println("File not found! Check the path.");
    }
    return image;
  }

  private BufferedImage readPPm(String imagePath) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(imagePath));
    } catch (FileNotFoundException e) {
      System.out.println("File " + imagePath + " not found!");
      return null; // Return null to indicate that there was an error.
    }

    StringBuilder builder = new StringBuilder();
    // Read the file line by line, and populate a string. This will throw away any comment lines.
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    // Set up the scanner to read from the string we just built.
    sc = new Scanner(builder.toString());

    String token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
      return null; // Return null to indicate an error.
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    // Create a BufferedImage with the specified width and height.
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        // Set the color of the pixel in the BufferedImage.
        int rgb = (255 << 24) | (r << 16) | (g << 8) | b;
        image.setRGB(j, i, rgb);
      }
    }

    return image;
  }

  /**
   * Loads and returns image array.
   *
   * @param imagePath path of the image
   * @param ext       extension of the image
   * @return double[][][] image
   */
  public double[][][] getImageArray(String imagePath, String ext) {
    BufferedImage image;
    if (ext.equals("jpg") || ext.equals("png")) {
      image = readJpgPNGImage(imagePath);
    } else {
      image = readPPm(imagePath);
    }
    int width = image.getWidth();
    int height = image.getHeight();

    double[][][] pixelData = new double[width][height][3];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        int rgb = image.getRGB(x, y);
        pixelData[x][y][0] = (rgb >> 16) & 0xFF;
        pixelData[x][y][1] = (rgb >> 8) & 0xFF;
        pixelData[x][y][2] = rgb & 0xFF;
      }
    }
    return pixelData;
  }
}
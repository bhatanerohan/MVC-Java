package model.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class to help save images.
 */
public class ImageSaver {
  private ImageUtils imageUtils = new ImageUtils();

  /**
   * Save as ppm.
   *
   * @param image    double[][][] image
   * @param filename file to save to
   * @throws IOException if IO error
   */
  public void saveAsPPM(double[][][] image, String filename) {
    int width = image.length;
    int height = image[0].length;

    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write("255\n"); // Maximum color value

      for (int y = 0; y < height; y++) {
        for (double[][] ints : image) {
          int red = (int) ints[y][0];
          int green = (int) ints[y][1];
          int blue = (int) ints[y][2];
          writer.write(red + " " + green + " " + blue + " ");
        }
        writer.write("\n");
      }

      writer.close();
      System.out.println("Image saved as " + filename);
    } catch (IOException e) {
      System.err.println("File not found: " + e.getMessage());
    }
  }

  /**
   * Save image.
   *
   * @param image    double[][][] image to be saved.
   * @param filename file name to be saved to
   * @param ext      extension of the image
   */
  public void saveImg(double[][][] image, String filename, String ext) {
    int[][][] newImage = imageUtils.convertDoubleToInt(image);
    try {
      BufferedImage img = imageUtils.convertToBuffered(newImage);
      File outputfile = new File(filename);
      ImageIO.write(img, ext, outputfile);
      System.out.println("Image saved as " + ext + " in " + filename);
    } catch (IOException e) {
      System.err.println("Invalid Path");
    }
  }

}

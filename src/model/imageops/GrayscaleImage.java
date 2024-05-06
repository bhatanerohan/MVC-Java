package model.imageops;

import model.utils.ImageClamp;
import model.utils.ImageStorage;

/**
 * Implements grayscale filtering of an image.
 */
public class GrayscaleImage {

  private final ImageStorage storage;
  private final ImageSplitter imageSplitter = new ImageSplitter();
  private final ImageClamp clamp = new ImageClamp();

  public GrayscaleImage(ImageStorage st) {
    this.storage = st;
  }

  /**
   * Get grayscale image.
   *
   * @param srcImgName  src image name
   * @param destImgName dest image name
   * @param p split percent
   */
  public void applyGrayscale(String srcImgName, String destImgName, int p) {

    double[][][] image = storage.getImage(srcImgName);

    int height = image[0].length;
    int width = image.length;
    double[][][] grayscaleImage = new double[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int newValue = (int) (image[x][y][0] * 0.2126 + image[x][y][1] * 0.7152
            + image[x][y][2] * 0.0722);
        grayscaleImage[x][y][0] = newValue;
        grayscaleImage[x][y][1] = newValue;
        grayscaleImage[x][y][2] = newValue;
      }
    }
    grayscaleImage = imageSplitter.splitImage(image, grayscaleImage, p);
    storage.putImage(destImgName, clamp.clampImage(grayscaleImage));

  }

}

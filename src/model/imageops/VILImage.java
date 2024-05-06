package model.imageops;

import model.utils.ImageStorage;

/**
 * Get value intensity and luma of image.
 */
public class VILImage {

  private ImageStorage storage;
  private double[][][] image;

  public VILImage(ImageStorage st) {
    this.storage = st;
  }

  /**
   * Get value only grayscale.
   *
   * @param srcImgName src image
   * @param destImgName dest image
   */
  public void applyValue(String srcImgName, String destImgName) {
    image = storage.getImage(srcImgName);

    int height = image[0].length;
    int width = image.length;

    double[][][] valGrayScale = new double[width][height][1];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        valGrayScale[x][y][0] = Math.max(Math.max(image[x][y][0], image[x][y][1]), image[x][y][2]);
      }
    }
    storage.putImage(destImgName, valGrayScale);

  }

  /**
   * Get intensity only grayscale.
   *
   * @param srcImgName src image
   * @param destImgName dest image
   */
  public void applyIntensity(String srcImgName, String destImgName) {
    image = storage.getImage(srcImgName);

    int height = image[0].length;
    int width = image.length;

    double[][][] valIntScale = new double[width][height][1];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        valIntScale[x][y][0] = (int) ((image[x][y][0] + image[x][y][1] + image[x][y][2]) / 3);
      }
    }
    storage.putImage(destImgName, valIntScale);

  }

  /**
   * Get luma only grayscale.
   *
   * @param srcImgName src image
   * @param destImgName dest image
   */
  public void applyLuma(String srcImgName, String destImgName) {
    image = storage.getImage(srcImgName);
    int height = image[0].length;
    int width = image.length;

    double[][][] valLumaScale = new double[width][height][1];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        valLumaScale[x][y][0] = (int) (image[x][y][0] * 0.2126 + image[x][y][1] * 0.7152);
      }
    }
    storage.putImage(destImgName, valLumaScale);
  }
}

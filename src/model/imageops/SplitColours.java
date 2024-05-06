package model.imageops;

import model.utils.ImageClamp;
import model.utils.ImageStorage;

/**
 * Implements splitting of RGB image into various channels.
 */
public class SplitColours {

  private final ImageClamp clamp = new ImageClamp();
  private ImageStorage storage;
  private double[][][] image;

  public SplitColours(ImageStorage st) {
    this.storage = st;
  }

  /**
   * Get red image.
   *
   * @param srcImgName src image
   * @param destImgName dest image
   */
  public void redImage(String srcImgName, String destImgName) {
    image = storage.getImage(srcImgName);
    int height = image[0].length;
    int width = image.length;
    double[][][] redImage = new double[width][height][3];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        redImage[x][y][0] = image[x][y][0];
        redImage[x][y][1] = 0;
        redImage[x][y][2] = 0;

      }
    }
    storage.putImage(destImgName, clamp.clampImage(redImage));
  }

  /**
   * Get green image.
   *
   * @param srcImgName src image
   * @param destImgName dest image
   */
  public void greenImage(String srcImgName, String destImgName) {
    image = storage.getImage(srcImgName);

    int height = image[0].length;
    int width = image.length;
    double[][][] greenImage = new double[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        greenImage[x][y][0] = 0;
        greenImage[x][y][1] = image[x][y][1];
        greenImage[x][y][2] = 0;

      }
    }
    storage.putImage(destImgName, clamp.clampImage(greenImage));
  }

  /**
   * Get blue image.
   *
   * @param srcImgName src image
   * @param destImgName dest image
   */
  public void blueImage(String srcImgName, String destImgName) {
    image = storage.getImage(srcImgName);

    int height = image[0].length;
    int width = image.length;
    double[][][] blueImage = new double[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        blueImage[x][y][0] = 0;
        blueImage[x][y][1] = 0;
        blueImage[x][y][2] = image[x][y][2];
      }
    }
    storage.putImage(destImgName, clamp.clampImage(blueImage));
  }

}

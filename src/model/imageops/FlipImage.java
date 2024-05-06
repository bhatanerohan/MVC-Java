package model.imageops;

import model.utils.ImageClamp;
import model.utils.ImageStorage;

/**
 * Class to implement flipping of image.
 */
public class FlipImage {

  private final ImageClamp clamp = new ImageClamp();
  private ImageStorage storage;

  /**
   * Constructor for flip image.
   * @param st storage for image
   */
  public FlipImage(ImageStorage st) {
    this.storage = st;
  }

  /**
   * Apply vertical flipping on an image.
   * @param srcImgName src img
   * @param destImgName dest img
   */
  public void applyVerticalFlip(String srcImgName, String destImgName) {
    double[][][] image = storage.getImage(srcImgName);

    int height = image[0].length;
    int width = image.length;
    double[][][] verticalFlipImage = new double[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        verticalFlipImage[x][y][0] = image[x][height - 1 - y][0];
        verticalFlipImage[x][y][1] = image[x][height - 1 - y][1];
        verticalFlipImage[x][y][2] = image[x][height - 1 - y][2];
      }
    }
    storage.putImage(destImgName, clamp.clampImage(verticalFlipImage));
  }

  /**
   * Apply horizontal flipping on an image.
   * @param srcImgName src img
   * @param destImgName dest img
   */
  public void applyHorizontalFLip(String srcImgName, String destImgName) {
    double[][][] image = storage.getImage(srcImgName);

    int height = image[0].length;
    int width = image.length;
    double[][][] horizontalFlipImage = new double[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        horizontalFlipImage[x][y][0] = image[width - 1 - x][y][0];
        horizontalFlipImage[x][y][1] = image[width - 1 - x][y][1];
        horizontalFlipImage[x][y][2] = image[width - 1 - x][y][2];
      }
    }
    storage.putImage(destImgName, clamp.clampImage(horizontalFlipImage));
  }

}

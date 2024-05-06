package model.imageops;

import model.utils.ImageClamp;
import model.utils.ImageStorage;

/**
 * Implements sepia filter on an image.
 */
public class SepiaTone {

  private final ImageSplitter imageSplitter = new ImageSplitter();
  private final ImageClamp clamp = new ImageClamp();
  private ImageStorage storage;

  public SepiaTone(ImageStorage st) {
    this.storage = st;
  }

  /**
   * Applies sepia tone on an image.
   * @param srcImgName src img
   * @param destImgName dest image
   * @param p split percent
   */
  public void applySepia(String srcImgName, String destImgName, int p) {
    double[][][] image = storage.getImage(srcImgName);

    int height = image[0].length;
    int width = image.length;
    double[][][] sepiaToneImage = new double[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        sepiaToneImage[x][y][0] = (int) (image[x][y][0] * 0.393 + image[x][y][1] * 0.769
            + image[x][y][2] * 0.189);
        sepiaToneImage[x][y][1] = (int) (image[x][y][0] * 0.349 + image[x][y][1] * 0.686
            + image[x][y][2] * 0.168);
        sepiaToneImage[x][y][2] = (int) (image[x][y][0] * 0.272 + image[x][y][1] * 0.534
            + image[x][y][2] * 0.131);
      }
    }
    sepiaToneImage = imageSplitter.splitImage(image, sepiaToneImage, p);
    storage.putImage(destImgName, clamp.clampImage(sepiaToneImage));
  }
}

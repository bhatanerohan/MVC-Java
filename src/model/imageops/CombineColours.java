package model.imageops;

import model.utils.ImageClamp;
import model.utils.ImageStorage;

/**
 * Combines RGB images into one.
 */
public class CombineColours {

  private final ImageClamp clamp = new ImageClamp();
  private ImageStorage storage;

  public CombineColours(ImageStorage st) {
    this.storage = st;
  }

  /**
   * Combines RGB channels into one image.
   *
   * @param redName red image
   * @param greenName green image
   * @param blueName blue image
   * @param destImgName dest image
   */
  public void applyCombine(String redName, String greenName, String blueName, String destImgName) {
    double[][][] red = storage.getImage(redName);
    double[][][] green = storage.getImage(greenName);
    double[][][] blue = storage.getImage(blueName);

    int height = red[0].length;
    int width = red.length;
    double[][][] combinedImage = new double[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        combinedImage[x][y][0] = red[x][y][0];
        combinedImage[x][y][1] = green[x][y][1];
        combinedImage[x][y][2] = blue[x][y][2];
      }
    }
    storage.putImage(destImgName, clamp.clampImage(combinedImage));
  }

}

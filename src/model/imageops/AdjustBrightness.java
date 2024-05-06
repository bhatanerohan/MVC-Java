package model.imageops;

import model.utils.ImageClamp;
import model.utils.ImageStorage;

/**
 * Class to implement brightness adjustment on an image.
 */
public class AdjustBrightness {
  private final ImageClamp clamp = new ImageClamp();
  private ImageStorage storage;

  public AdjustBrightness(ImageStorage st) {
    this.storage = st;
  }

  private void adjust(String srcImgName, String destImgName, int value) {
    double[][][] image = storage.getImage(srcImgName);
    int height = image[0].length;
    int width = image.length;
    double[][][] newImage = new double[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newImage[x][y][0] = image[x][y][0] + value;
        newImage[x][y][1] = image[x][y][1] + value;
        newImage[x][y][2] = image[x][y][2] + value;
      }
    }
    storage.putImage(destImgName, clamp.clampImage(newImage));
  }

  public void applyBrighten(String srcImgName, String destImgName, int value) {
    adjust(srcImgName, destImgName, value);
  }

  public void applyDarken(String srcImgName, String destImgName, int value) {
    adjust(srcImgName, destImgName, value * -1);
  }

}

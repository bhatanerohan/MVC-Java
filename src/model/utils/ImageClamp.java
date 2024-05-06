package model.utils;

/**
 * Implements clamping of image between 0 and 255.
 */
public class ImageClamp {

  /**
   * Clamps image.
   *
   * @param img image to clamp
   * @return clamped image
   */
  public double[][][] clampImage(double[][][] img) {
    int height = img[0].length;
    int width = img.length;
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        img[x][y][0] = Math.max(0, Math.min(img[x][y][0], 255));
        img[x][y][1] = Math.max(0, Math.min(img[x][y][1], 255));
        img[x][y][2] = Math.max(0, Math.min(img[x][y][2], 255));
      }
    }
    return img;
  }

}

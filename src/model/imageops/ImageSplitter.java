package model.imageops;

/**
 * Splits an image to original and converted to show difference.
 */
public class ImageSplitter {

  /**
   * Get split view image for org and converted.
   *
   * @param original org image
   * @param converted converted image
   * @param p split percent
   * @return splitted image
   */
  public double[][][] splitImage(double[][][] original, double[][][] converted, int p) {
    double[][][] newImage = new double[original.length][original[0].length][3];
    if (p > 1) {
      int endPixel = (int) Math.round(((double) p / 100) * original.length);

      for (int i = 0; i < endPixel; i++) {
        for (int j = 0; j < converted[0].length; j++) {
          for (int c = 0; c < 3; c++) {
            if (i == endPixel - 1) {
              newImage[i][j][c] = 255;
            } else {
              newImage[i][j][c] = original[i][j][c];
            }
          }
        }
      }

      for (int i = endPixel; i < converted.length; i++) {
        for (int j = 0; j < converted[0].length; j++) {
          for (int c = 0; c < 3; c++) {
            newImage[i][j][c] = converted[i][j][c];
          }
        }
      }
      return newImage;
    }
    return converted;
  }
}

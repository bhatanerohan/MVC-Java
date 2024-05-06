package model.imageops;

/**
 * Class to apply filter on an image.
 */
public class FilterImage {

  /**
   * Apply filter on an image.
   *
   * @param image image to apply filter on
   * @param filter filter to be applied
   * @return result image
   */
  public double[][][] applyFilter(double[][][] image, float[][] filter) {
    int height = image[0].length;
    int width = image.length;

    double[][][] newImage = new double[width][height][3];

    int midPt = filter.length / 2;

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        for (int i = 0; i < filter.length; i++) {
          for (int j = 0; j < filter.length; j++) {
            int pixelX = x + i - midPt;
            int pixelY = y + j - midPt;

            if (pixelX >= 0 && pixelX < width && pixelY >= 0 && pixelY < height) {
              newImage[x][y][0] += (int) (image[pixelX][pixelY][0] * filter[i][j]);
              newImage[x][y][1] += (int) (image[pixelX][pixelY][1] * filter[i][j]);
              newImage[x][y][2] += (int) (image[pixelX][pixelY][2] * filter[i][j]);
            }
          }

        }
      }
    }
    return newImage;
  }
}

package model.imageops;

/**
 * Apply haar transform on an image.
 */
public class HaarTransform {

  private double[][] transformChannel(double[][] channel) {
    int c = channel.length;
    AvgDiffCompression compressor = new AvgDiffCompression();

    while (c > 1) {
      for (int i = 0; i < c; i++) {
        double[] row = new double[c];
        System.arraycopy(channel[i], 0, row, 0, c);
        double[] transformedRow = compressor.getAvgDiff(row);
        System.arraycopy(transformedRow, 0, channel[i], 0, c);
      }
      for (int j = 0; j < c; j++) {
        double[] column = new double[c];
        for (int i = 0; i < c; i++) {
          column[i] = channel[i][j];
        }
        double[] transformedColumn = compressor.getAvgDiff(column);
        for (int i = 0; i < c; i++) {
          channel[i][j] = transformedColumn[i];
        }
      }
      c = c / 2;
    }
    return channel;
  }

  private double[][] inverseTransformChannel(double[][] channel) {
    int c = 2;
    int s = channel.length;
    AvgDiffCompression compressor = new AvgDiffCompression();

    while (c <= s) {

      for (int j = 0; j < c; j++) {

        double[] column = new double[c];

        for (int i = 0; i < c; i++) {
          column[i] = channel[i][j];
        }
        double[] transformedColumn = compressor.getInverseAvgDiff(column);
        for (int i = 0; i < c; i++) {
          channel[i][j] = transformedColumn[i];

        }
      }
      for (int i = 0; i < c; i++) {
        double[] row = new double[c];
        System.arraycopy(channel[i], 0, row, 0, c);
        double[] transformedRow = compressor.getInverseAvgDiff(row);
        System.arraycopy(transformedRow, 0, channel[i], 0, c);
      }

      c = c * 2;
    }
    return channel;
  }

  /**
   * Applies transform on transformed image.
   * @param image image
   * @return transformed image
   */
  public double[][][] transformImage(double[][][] image) {
    double[][][] newImage = new double[image.length][image[0].length][3];

    for (int c = 0; c < 3; c++) {
      double[][] channel = new double[image.length][image[0].length];
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[0].length; j++) {
          channel[i][j] = image[i][j][c];
        }
      }
      double[][] transformedChannel = transformChannel(channel);
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[0].length; j++) {
          newImage[i][j][c] = transformedChannel[i][j];
        }
      }
    }

    return newImage;
  }

  /**
   * Applies inverse transform on transformed image.
   * @param image transformed image
   * @return inverted image
   */
  public double[][][] inverseTransformImage(double[][][] image) {
    double[][][] newImage = new double[image.length][image.length][3];

    for (int c = 0; c < 3; c++) {
      double[][] channel = new double[image.length][image.length];
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image.length; j++) {
          channel[i][j] = image[i][j][c];
        }
      }
      double[][] transformedChannel = inverseTransformChannel(channel);
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image.length; j++) {
          newImage[i][j][c] = transformedChannel[i][j];
        }
      }
    }
    return newImage;
  }

}

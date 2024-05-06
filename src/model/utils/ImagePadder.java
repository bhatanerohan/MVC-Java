package model.utils;

/**
 * Implements padding of image to power of 2.
 */
public class ImagePadder {

  private int checkNumber(int num) {
    int newLength = 1;
    while (newLength < num) {
      newLength = newLength * 2;
      if (newLength == num) {
        return newLength;
      }
    }
    return newLength;
  }

  private double[] padSequence(double[] sequence) {
    int l = sequence.length;
    int newLength = 1;

    while (newLength < l) {
      newLength = newLength * 2;
      if (newLength == l) {
        return sequence;
      }
    }
    double[] newSequence = new double[newLength];
    System.arraycopy(sequence, 0, newSequence, 0, l);
    for (int i = l; i < newLength; i++) {
      newSequence[i] = 0;
    }
    return newSequence;
  }

  /**
   * Get padded image.
   * @param image image to pad
   * @return padded image
   */
  public double[][][] padImage(double[][][] image) {
    int width = image.length;
    int height = image[0].length;
    int newHeight;
    int newWidth;
    if (width >= height) {
      newWidth = checkNumber(width);
      newHeight = newWidth;
    } else {
      newHeight = checkNumber(height);
      newWidth = newHeight;
    }
    if (newHeight == height && newWidth == width) {
      return image;
    } else {
      double[][][] newImage = new double[newWidth][newHeight][3];
      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          for (int c = 0; c < 3; c++) {
            newImage[i][j][c] = image[i][j][c];
          }
        }
      }
      for (int i = width; i < newWidth; i++) {
        for (int j = height; j < newHeight; j++) {
          for (int c = 0; c < 3; c++) {
            newImage[i][j][c] = 0;
          }
        }
      }
      return newImage;
    }
  }

  /**
   * Get unpadded image.
   *
   * @param image image to unpad
   * @param width org width
   * @param height org height
   * @return unpadded image
   */
  public double[][][] unpadImage(double[][][] image, int width, int height) {
    double[][][] newImage = new double[width][height][3];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        for (int c = 0; c < 3; c++) {
          newImage[i][j][c] = image[i][j][c];
        }
      }
    }
    return newImage;
  }
}

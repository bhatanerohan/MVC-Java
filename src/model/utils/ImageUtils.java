package model.utils;

import java.awt.image.BufferedImage;

/**
 * Utility class for image processing operations. Provides methods to convert
 * image data format supporting operations in image
 * processing.
 */

public class ImageUtils {

  /**
   * Converts a 3D array representation of an image from double to integer values.
   *
   *
   * @param image The 3D double array representing the image, with dimensions
   *               width, height, and color channels.
   * @return A 3D integer array representing the converted image.
   */

  public int[][][] convertDoubleToInt(double[][][] image) {
    int channels = image[0][0].length;
    int[][][] newImage;
    if (channels == 1) {
      newImage = new int[image.length][image[0].length][1];
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[0].length; j++) {
          newImage[i][j][0] = (int) image[i][j][0];
        }
      }
    } else {
      newImage = new int[image.length][image[0].length][3];

      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[0].length; j++) {
          for (int c = 0; c < 3; c++) {
            newImage[i][j][c] = (int) image[i][j][c];
          }
        }
      }
    }
    return newImage;
  }

  /**
   * Converts a 3D integer array representing an image into a BufferedImage.
   *
   * @param imgArr The 3D integer array representing the image.
   * @return A BufferedImage created from the provided array.
   */

  public BufferedImage convertToBuffered(int[][][] imgArr) {
    int width = imgArr.length;
    int height = imgArr[0].length;
    int channels = imgArr[0][0].length;
    int rgb = 0;
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    if (channels == 1) {
      bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (channels == 1) {
          int grayValue = imgArr[x][y][0];
          rgb = (grayValue << 16) | (grayValue << 8) | grayValue;
        } else {
          rgb = imgArr[x][y][0] << 16 | imgArr[x][y][1] << 8 | imgArr[x][y][2];
        }
        bufferedImage.setRGB(x, y, rgb);
      }
    }
    return bufferedImage;
  }
}

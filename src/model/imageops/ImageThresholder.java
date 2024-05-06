package model.imageops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Calculates threshold and applies it for compression.
 */
public class ImageThresholder {

  private double calculateThreshold(double[][][] image, double percent) {
    Set<Double> allValues = new HashSet<>();
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image.length; j++) {
        for (int c = 0; c < 3; c++) {
          double val = Math.abs(image[i][j][c]);
          allValues.add(val);
        }
      }
    }
    List<Double> uniqueValues = new ArrayList<>(allValues);
    Collections.sort(uniqueValues);
    int index = (int) Math.ceil((percent / 100) * uniqueValues.size()) - 1;

    return uniqueValues.get(index);
  }

  private double[][][] resetOnThresh(double[][][] image, double threshold) {
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image.length; j++) {
        for (int c = 0; c < 3; c++) {
          if (Math.abs(image[i][j][c]) < threshold) {
            image[i][j][c] = 0;
          }
        }
      }
    }
    return image;
  }

  /**
   * Get thresholded image.
   *
   * @param image image
   * @param percent thresh percent
   * @return thresholded image
   */
  public double[][][] thresholdImage(double[][][] image, double percent) {
    double threshold = calculateThreshold(image, percent);
    return resetOnThresh(image, threshold);
  }

}

package model.imageops;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculates averages and differences on 1D array for compression.
 */
public class AvgDiffCompression {

  private double[] interleaveArrays(double[] averages, double[] differences) {
    int length = averages.length;
    double[] finalArray = new double[length * 2];

    for (int i = 0; i < length; i++) {
      finalArray[2 * i] = averages[i];
      finalArray[2 * i + 1] = differences[i];
    }

    return finalArray;
  }

  /**
   * Get avg and diff for 1D array.
   *
   * @param arr 1D array
   * @return 1D array of avg and diff
   */
  public double[] getAvgDiff(double[] arr) {
    List<Double> avg = new ArrayList<>();
    List<Double> diff = new ArrayList<>();

    for (int i = 0; i < arr.length - 1; i += 2) {
      double a = arr[i];
      double b = arr[i + 1];

      double av = (a + b) / Math.sqrt(2);
      double di = (a - b) / Math.sqrt(2);

      avg.add(av);
      diff.add(di);
    }

    double[] result = new double[arr.length];
    for (int i = 0, j = 0; i < avg.size(); i++, j++) {
      result[j] = avg.get(i);
      result[j + arr.length / 2] = diff.get(i);
    }

    return result;
  }

  /**
   * Get inverse of avg and diff for 1D array.
   * @param rowValues 1D array
   * @return 1D array
   */
  public double[] getInverseAvgDiff(double[] rowValues) {

    int rowLength = rowValues.length;
    int halfLength = rowLength / 2;
    double[] avgValues = new double[rowLength / 2];
    double[] diffValues = new double[rowLength / 2];

    for (int i = 0; i < rowLength / 2; i++) {
      double a = rowValues[i];
      double b = rowValues[i + halfLength];
      avgValues[i] = (a + b) / Math.sqrt(2);
      diffValues[i] = (a - b) / Math.sqrt(2);
    }
    return interleaveArrays(avgValues, diffValues);
  }

}

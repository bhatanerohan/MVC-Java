package model.imageops;

import model.utils.ImageClamp;
import model.utils.ImageStorage;

/**
 * Implements color correction on an image.
 */
public class ColorCorrection {

  private final ImageStorage storage;
  private final ImageClamp clamp = new ImageClamp();
  private final ImageSplitter imageSplitter = new ImageSplitter();


  public ColorCorrection(ImageStorage st) {
    this.storage = st;
  }

  private double[][] findHistogramPeaks(double[][][] image) {
    double[] redHistogram = new double[256];
    double[] greenHistogram = new double[256];
    double[] blueHistogram = new double[256];
    double[][] peaks = new double[3][2];

    for (int x = 0; x < image.length; x++) {
      for (int y = 0; y < image[0].length; y++) {
        redHistogram[(int) image[x][y][0]]++;
        greenHistogram[(int) image[x][y][1]]++;
        blueHistogram[(int) image[x][y][2]]++;
      }
    }

    peaks[0] = findMeaningfulPeak(redHistogram);
    peaks[1] = findMeaningfulPeak(greenHistogram);
    peaks[2] = findMeaningfulPeak(blueHistogram);

    return peaks;
  }

  private double[] findMeaningfulPeak(double[] histogram) {
    int peakValue = 0;
    int peakIndex = 0;
    for (int i = 11; i <= 244; i++) {
      if (histogram[i] > peakValue) {
        peakValue = (int) histogram[i];
        peakIndex = i;
      }
    }
    return new double[]{peakValue, peakIndex};
  }

  /**
   * Corrects the color of given image.
   *
   * @param srcImgName src image
   * @param destImgName dest image
   * @param p split percent
   */
  public void colorCorrect(String srcImgName, String destImgName, int p) {
    double[][][] image = storage.getImage(srcImgName);
    double[][] peaks = findHistogramPeaks(image);
    int averagePeak = (int) ((peaks[0][1] + peaks[1][1] + peaks[2][1]) / 3);
    double[] offsets = {
        averagePeak - peaks[0][1],
        averagePeak - peaks[1][1],
        averagePeak - peaks[2][1]
    };

    double[][][] converted = applyOffsetsToImage(image, offsets);
    converted = imageSplitter.splitImage(storage.getImage(srcImgName), converted, p);

    storage.putImage(destImgName, converted);
  }

  private double[][][] applyOffsetsToImage(double[][][] image, double[] offsets) {
    double[][][] correctedImage = new double[image.length][image[0].length][3];

    for (int x = 0; x < image.length; x++) {
      for (int y = 0; y < image[0].length; y++) {
        correctedImage[x][y][0] = image[x][y][0] + offsets[0];
        correctedImage[x][y][1] = image[x][y][1] + offsets[1];
        correctedImage[x][y][2] = image[x][y][2] + offsets[2];
      }
    }

    return clamp.clampImage(correctedImage);
  }


}

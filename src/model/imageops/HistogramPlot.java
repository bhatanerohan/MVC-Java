package model.imageops;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.utils.ImageClamp;
import model.utils.ImageStorage;

/**
 * Creates histogram plot for a given image.
 */
public class HistogramPlot {

  private final ImageClamp clamp = new ImageClamp();
  private ImageStorage storage;

  public HistogramPlot(ImageStorage st) {
    this.storage = st;
  }

  private double findMaxValue(double[] redHist, double[] greenHist, double[] blueHist) {
    double maxValue = 0;
    for (int i = 0; i < 256; i++) {
      maxValue = Math.max(maxValue, redHist[i]);
      maxValue = Math.max(maxValue, greenHist[i]);
      maxValue = Math.max(maxValue, blueHist[i]);
    }
    return maxValue;
  }

  private void drawGrid(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.lightGray);
    g2d.setStroke(new BasicStroke(0.25F));

    for (int i = 0; i < 256; i++) {
      if (i % 10 == 0) {
        g.drawLine(i, 0, i, 255);
        g.drawLine(0, i, 255, i);

      }
    }
  }

  private void plotLines(Graphics g, double[] redHist, double[] greenHist, double[] blueHist,
      double maxValue) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setStroke(new BasicStroke(1));

    g2d.setColor(Color.RED);
    drawLine(g2d, redHist, maxValue);

    g2d.setColor(Color.GREEN);
    drawLine(g2d, greenHist, maxValue);

    g2d.setColor(Color.BLUE);
    drawLine(g2d, blueHist, maxValue);
  }

  private void drawLine(Graphics2D g2d, double[] histogram, double maxValue) {
    int width = 256;
    int height = 256;
    int barWidth = width / histogram.length;

    for (int i = 0; i < 255; i++) {
      int y1 = i * barWidth;
      int x1 = (int) (height - histogram[i] / maxValue * height);
      int x2 = (int) (height - histogram[i + 1] / maxValue * height);
      int y2 = (i + 1) * barWidth;
      g2d.drawLine(x1, y1, x2, y2);
    }
  }

  private double[][][] bufferedImageTo3DArray(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    double[][][] array = new double[height][width][3];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Color color = new Color(image.getRGB(x, y));
        array[y][x][0] = color.getRed();
        array[y][x][1] = color.getGreen();
        array[y][x][2] = color.getBlue();
      }
    }

    return array;
  }

  /**
   * Get histogram for an image.
   * @param srcImgName src image
   * @param destImgName dest image
   */
  public void getHistogram(String srcImgName, String destImgName) {
    double[][][] image = storage.getImage(srcImgName);
    double[] redHistogram = new double[256];
    double[] greenHistogram = new double[256];
    double[] blueHistogram = new double[256];

    for (int x = 0; x < image.length; x++) {
      for (int y = 0; y < image[0].length; y++) {
        if (image[x][y][0] > 10 && image[x][y][0] < 245) {
          redHistogram[(int) image[x][y][0]]++;
        }

        if (image[x][y][1] > 10 && image[x][y][1] < 245) {
          greenHistogram[(int) image[x][y][1]]++;
        }

        if (image[x][y][2] > 10 && image[x][y][2] < 245) {
          blueHistogram[(int) image[x][y][2]]++;
        }
      }
    }

    double maxValue = findMaxValue(redHistogram, greenHistogram, blueHistogram);

    BufferedImage histogramImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = histogramImage.createGraphics();

    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, 256, 256);
    drawGrid(g2d);

    plotLines(g2d, redHistogram, greenHistogram, blueHistogram, maxValue);

    double[][][] histoImage = bufferedImageTo3DArray(histogramImage);
    g2d.dispose();
    storage.putImage(destImgName, clamp.clampImage(histoImage));
  }
}

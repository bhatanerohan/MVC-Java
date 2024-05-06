package model.imageops;

import model.utils.ImageClamp;
import model.utils.ImageStorage;

/**
 * This class provides methods for adjusting the levels of an image using a quadratic curve.
 *
 */
public class LevelAdjustment {

  private final ImageStorage storage;
  private final ImageClamp clamp = new ImageClamp();
  private final ImageSplitter imageSplitter = new ImageSplitter();


  public LevelAdjustment(ImageStorage st) {
    this.storage = st;
  }

  /**
   * Computes the coefficients for a quadratic curve based on provided control points.
   * This method is used to generate a quadratic curve for level adjustments.
   *
   * @param b The shadow value.
   * @param m The mid value.
   * @param w The highlight value.
   * @return An array containing the computed coefficients (a, b, c) of the quadratic curve.
   */

  private double[] computeQuadraticCurveCoefficients(int b, int m, int w) {
    double capA = b * b * (m - w) - b * (m * m - w * w) + w * m * m - m * w * w;
    double aa = -b * (128 - 255) + 128 * w - 255 * m;
    double ab = b * b * (128 - 255) + 255 * m * m - 128 * w * w;
    double ac = b * b * (255 * m - 128 * w) - b * (255 * m * m - 128 * w * w);
    double a = aa / capA;
    double b_c = ab / capA;
    double c = ac / capA;

    return new double[]{a, b_c, c};
  }

  /**
   * Applies level adjustments to an image using the specified quadratic curve coefficients.
   * This method adjusts the tonal range of the image's color channels.
   */

  public void adjustLevel(int b, int m, int w, String srcImgName, String destImgName, int p) {
    double[][][] image = storage.getImage(srcImgName);
    int width = image.length;
    int height = image[0].length;
    double[] coeffs = computeQuadraticCurveCoefficients(b, m, w);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        double red = image[x][y][0];
        double green = image[x][y][1];
        double blue = image[x][y][2];

        image[x][y][0] = Math.round((coeffs[0] * red * red) + coeffs[1] * red + coeffs[2]);
        image[x][y][1] = Math.round(((coeffs[0] * green * green) + coeffs[1] * green + coeffs[2]));
        image[x][y][2] = Math.round(((coeffs[0] * blue * blue + coeffs[1] * blue + coeffs[2])));
      }
    }
    image = imageSplitter.splitImage(storage.getImage(srcImgName), image, p);
    storage.putImage(destImgName, clamp.clampImage(image));
  }
}

package model;

import static org.junit.Assert.assertEquals;

import model.imageops.HaarTransform;
import org.junit.Test;

/**
 * Implements testing for haar transform.
 */
public class HaarTransformTest {
  @Test
  public void testTransformAndInverse() {
    HaarTransform transform = new HaarTransform();
    double[][][] testArr = new double[2][2][3];

    int x = 1;
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 3; k++) {
          testArr[i][j][k] = x;
          x++;
        }
      }
    }

    double[][][] expectedTransform =
        {{
            {11.0, 13.0, 15.0},
            {-3.0, -3.0, -3.0}},

            {{-6.0, -6.0, -6.0},
                {0.0, 0.0, 0.0}}
        };

    double[][][] transformed = transform.transformImage(testArr);

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expectedTransform[i][j][k], transformed[i][j][k], 0.1);
        }
      }
    }
  }
}
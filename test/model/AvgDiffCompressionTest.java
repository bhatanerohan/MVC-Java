package model;

import static org.junit.Assert.assertEquals;

import model.imageops.AvgDiffCompression;
import org.junit.Test;

/**
 * Class to test averaging and differencing of sequences.
 */
public class AvgDiffCompressionTest {

  @Test
  public void testAvgDiff() {

    AvgDiffCompression compressor = new AvgDiffCompression();
    double[] testArr = {5, 3, 2, 4, 2, 1, 0, 3};

    double[] expected = {5.65685424949238, 4.242640687119285, 2.1213203435596424,
        2.1213203435596424, 1.414213562373095, -1.414213562373095, 0.7071067811865475,
        -2.1213203435596424};

    double[] finalArr = compressor.getAvgDiff(testArr);
    for (int i = 0; i < testArr.length; i++) {
      assertEquals(expected[i], finalArr[i], 0.1);
    }
  }

  @Test
  public void testInvAvgDiff() {

    AvgDiffCompression compressor = new AvgDiffCompression();
    double[] testArr = {5.65685424949238, 4.242640687119285, 2.1213203435596424, 2.1213203435596424,
        1.414213562373095, -1.414213562373095, 0.7071067811865475, -2.1213203435596424};

    double[] expected = {5, 3, 2, 4, 2, 1, 0, 3};

    double[] finalArr = compressor.getInverseAvgDiff(testArr);
    for (int i = 0; i < testArr.length; i++) {
      assertEquals(expected[i], finalArr[i], 0.1);
    }
  }
}
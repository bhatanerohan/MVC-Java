package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import model.utils.ImageStorage;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test functionality of Image Model.
 */
public class ImageModelTest {

  double[][][] testImg = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
      {{10, 11, 12}, {13, 14, 15}, {16, 17, 18}}, {{19, 20, 21}, {22, 23, 24}, {25, 26, 27}}};
  private ImageModel imageModel;
  private ImageStorage storage;

  @Before
  public void setUp() {
    storage = new ImageStorage();
    imageModel = new ImageModel(storage);
  }

  @Test
  public void testPNGLoadImage() {
    String imagePath = "res/fallneu.jpg";
    String ext = "png";
    String imageName = "fallneu-small";

    imageModel.loadImg(imagePath, ext, imageName);

    assertTrue(storage.checkKey(imageName));
  }

  @Test
  public void testJPGLoadImage() {
    String imagePath = "res/fallneu.jpg";  // Replace with the path to your sample image
    String ext = "jpg";
    String imageName = "fallneu-brighter";
    imageModel.loadImg(imagePath, ext, imageName);
    assertTrue(storage.checkKey(imageName));
  }

  @Test
  public void testPPMLoadImage() {
    String imagePath = "res/fallneugreen.ppm";
    String ext = "ppm";
    String imageName = "fallneuppm";
    imageModel.loadImg(imagePath, ext, imageName);
    assertTrue(storage.checkKey(imageName));
  }

  @Test
  public void testJPGSaveImage() {
    imageModel.loadImg("res/fallneu.jpg", "jpg", "fallneu");
    String savePath = "fallneu.jpg";
    String imgName = "fallneu";
    imageModel.saveImg(savePath, "jpg", imgName);

    File savedImageFile = new File(savePath);
    assertTrue(savedImageFile.exists());
  }

  @Test
  public void testPNGSaveImage() {
    imageModel.loadImg("res/fallneu.jpg", "jpg", "fallneu");
    String savePath = "fallneunew1.png";
    String imgName = "fallneu";
    imageModel.saveImg(savePath, "png", imgName);

    File savedImageFile = new File(savePath);
    assertTrue(savedImageFile.exists());
  }

  @Test
  public void testPPMSaveImage() {
    imageModel.loadImg("res/fallneu.jpg", "jpg", "fallneu");
    String savePath = "fallneunew.ppm";
    String imgName = "fallneu";
    imageModel.saveImg(savePath, "ppm", imgName);

    File savedImageFile = new File(savePath);
    assertTrue(savedImageFile.exists());
  }

  @Test
  public void testFlipVertically() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{7, 8, 9}, {4, 5, 6}, {1, 2, 3}},
        {{16, 17, 18}, {13, 14, 15}, {10, 11, 12}}, {{25, 26, 27}, {22, 23, 24}, {19, 20, 21}}};

    String srcImgName = "img1";

    String destImgName = "img2";

    storage.putImage(srcImgName, originalImage);

    imageModel.flipVertically(srcImgName, destImgName);

    assertEquals(expected, storage.getImage("img2"));


  }


  @Test
  public void testFlipHorizontally() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{19, 20, 21}, {22, 23, 24}, {25, 26, 27}},
        {{10, 11, 12}, {13, 14, 15}, {16, 17, 18}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};

    String srcImgName = "img1";
    String destImgName = "img2";

    storage.putImage(srcImgName, originalImage);
    imageModel.flipHorizontally(srcImgName, destImgName);
    assertEquals(expected, storage.getImage("img2"));
  }

  @Test
  public void testDarkenImage() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}},
        {{9, 10, 11}, {12, 13, 14}, {15, 16, 17}}};

    String srcImgName = "img1";
    String destImgName = "img2";
    int dec_value = 10;
    storage.putImage(srcImgName, originalImage);

    imageModel.darkenImage(srcImgName, destImgName, dec_value);

    assertEquals(expected, storage.getImage("img2"));

  }

  @Test
  public void testBrightenImage() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{11, 12, 13}, {14, 15, 16}, {17, 18, 19}},
        {{20, 21, 22}, {23, 24, 25}, {26, 27, 28}}, {{29, 30, 31}, {32, 33, 34}, {35, 36, 37}}};

    String srcImgName = "img1";
    String destImgName = "img2";
    int inc_value = 10;
    storage.putImage(srcImgName, originalImage);
    imageModel.brightenImage(srcImgName, destImgName, inc_value);
    assertEquals(expected, storage.getImage("img2"));
  }

  @Test
  public void testBlurImg() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{1, 1, 1}, {3, 4, 4}, {3, 4, 4}},
        {{6, 6, 7}, {10, 10, 11}, {9, 10, 10}},
        {{7, 8, 9}, {12, 12, 13}, {10, 10, 11}}};

    String srcImgName = "img1";
    String destImgName = "img2";
    storage.putImage(srcImgName, originalImage);
    imageModel.getBlurredImage(srcImgName, destImgName, 0);
    assertEquals(expected, storage.getImage("img2"));
  }

  @Test
  public void testSharpen() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{0, 0, 0}, {7, 9, 10}, {7, 8, 8}},
        {{18, 19, 21}, {36, 39, 42}, {29, 31, 33}}, {{24, 24, 27}, {41, 42, 44}, {34, 34, 36}}};

    String srcImgName = "img1";
    String destImgName = "img2";
    storage.putImage(srcImgName, originalImage);
    imageModel.getSharpenedImage(srcImgName, destImgName, 0);
    assertEquals(expected, storage.getImage("img2"));
  }


  @Test
  public void testGrayImage() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{1, 1, 1}, {4, 4, 4}, {7, 7, 7}},
        {{10, 10, 10}, {13, 13, 13}, {16, 16, 16}}, {{19, 19, 19}, {22, 22, 22}, {25, 25, 25}}};

    String srcImgName = "img1";
    String destImgName = "img2";
    storage.putImage(srcImgName, originalImage);
    imageModel.getGrayscaleImage(srcImgName, destImgName, 0);
    assertEquals(expected, storage.getImage("img2"));
  }

  @Test
  public void testSepia() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{2, 2, 1}, {6, 5, 4}, {10, 9, 7}},
        {{14, 13, 10}, {18, 16, 12}, {22, 20, 15}}, {{26, 23, 18}, {30, 27, 21}, {34, 31, 24}}};

    String srcImgName = "img1";
    String destImgName = "img2";
    storage.putImage(srcImgName, originalImage);
    imageModel.sepiaToneImage(srcImgName, destImgName, 0);
    assertEquals(expected, storage.getImage("img2"));
  }

  @Test
  public void testGetRedImage() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{1, 0, 0}, {4, 0, 0}, {7, 0, 0}},
        {{10, 0, 0}, {13, 0, 0}, {16, 0, 0}},
        {{19, 0, 0}, {22, 0, 0}, {25, 0, 0}}};

    String srcImgName = "img1";
    String destImgName = "img2";
    storage.putImage(srcImgName, originalImage);
    imageModel.getRedImage(srcImgName, destImgName);
    assertEquals(expected, storage.getImage("img2"));
  }

  @Test
  public void testGreenImage() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{0, 2, 0}, {0, 5, 0}, {0, 8, 0}},
        {{0, 11, 0}, {0, 14, 0}, {0, 17, 0}},
        {{0, 20, 0}, {0, 23, 0}, {0, 26, 0}}};
    String srcImgName = "img1";
    String destImgName = "img2";
    storage.putImage(srcImgName, originalImage);
    imageModel.getGreenImage(srcImgName, destImgName);
    assertEquals(expected, storage.getImage("img2"));
  }

  @Test
  public void testGetBlueImage() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{0, 0, 3}, {0, 0, 6}, {0, 0, 9}},
        {{0, 0, 12}, {0, 0, 15}, {0, 0, 18}},
        {{0, 0, 21}, {0, 0, 24}, {0, 0, 27}}};
    String srcImgName = "img1";
    String destImgName = "img2";
    storage.putImage(srcImgName, originalImage);
    imageModel.getBlueImage(srcImgName, destImgName);
    assertEquals(expected, storage.getImage("img2"));
  }

  @Test
  public void testCombineRGB() {
    double[][][] red = {{{1, 0, 0}, {4, 0, 0}, {7, 0, 0}}, {{10, 0, 0}, {13, 0, 0}, {16, 0, 0}},
        {{19, 0, 0}, {22, 0, 0}, {25, 0, 0}}};

    double[][][] green = {{{0, 2, 0}, {0, 5, 0}, {0, 8, 0}}, {{0, 11, 0}, {0, 14, 0}, {0, 17, 0}},
        {{0, 20, 0}, {0, 23, 0}, {0, 26, 0}}};

    double[][][] blue = {{{0, 0, 3}, {0, 0, 6}, {0, 0, 9}}, {{0, 0, 12}, {0, 0, 15}, {0, 0, 18}},
        {{0, 0, 21}, {0, 0, 24}, {0, 0, 27}}};
    double[][][] expected = testImg; // which is the original image that is combined back again

    String redImgName = "redimg";
    String greenImgName = "greenimg";
    String blueImgName = "blueimg";
    String destImgName = "img2";

    storage.putImage(redImgName, red);
    storage.putImage(greenImgName, green);
    storage.putImage(blueImgName, blue);

    imageModel.combineRGB(redImgName, greenImgName, blueImgName, destImgName);

    assertEquals(expected, storage.getImage("img2"));

  }

  @Test
  public void testGetValueGrayscale() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{3}, {6}, {9}}, {{12}, {15}, {18}}, {{21}, {24}, {27}}};

    String srcImgName = "img1";
    String destImgName = "img2";

    storage.putImage(srcImgName, originalImage);

    imageModel.getValueGrayscale(srcImgName, destImgName);

    assertEquals(expected, storage.getImage("img2"));

  }

  @Test
  public void testGetIntensityGrayscale() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{2}, {5}, {8}}, {{11}, {14}, {17}}, {{20}, {23}, {26}}};

    String srcImgName = "img1";
    String destImgName = "img2";

    storage.putImage(srcImgName, originalImage);

    imageModel.getIntensityGrayscale(srcImgName, destImgName);

    assertEquals(expected, storage.getImage("img2"));

  }

  @Test
  public void testGetLumaGrayscale() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{1}, {4}, {7}}, {{9}, {12}, {15}}, {{18}, {21}, {23}}};

    String srcImgName = "img1";
    String destImgName = "img2";

    storage.putImage(srcImgName, originalImage);

    imageModel.getLumaGrayscale(srcImgName, destImgName);

    assertEquals(expected, storage.getImage("img2"));
  }

  @Test
  public void testColorCorrect() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{0.0, 3.0, 3.0}, {3.0, 6.0, 6.0}, {6.0, 9.0, 9.0}},
        {{9.0, 12.0, 12.0}, {12.0, 15.0, 15.0}, {15.0, 18.0, 18.0}},
        {{18.0, 21.0, 21.0}, {21.0, 24.0, 24.0}, {24.0, 27.0, 27.0}}};

    String srcImgName = "img1";
    String destImgName = "img2";

    storage.putImage(srcImgName, originalImage);
    imageModel.getColorCorrection(srcImgName, destImgName, 0);
    double[][][] result = storage.getImage(destImgName);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        for (int c = 0; c < 3; c++) {
          assertEquals(expected[i][j][c], result[i][j][c], 0.1);
        }
      }
    }
  }

  @Test
  public void testLevelsAdjust() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}},
        {{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}},
        {{0.0, 0.0, 2.0}, {4.0, 6.0, 7.0}, {9.0, 11.0, 13.0}}};

    String srcImgName = "img1";
    String destImgName = "img2";

    storage.putImage(srcImgName, originalImage);
    imageModel.getLevelAdjustment(20, 100, 255, srcImgName, destImgName, 0);
    double[][][] result = storage.getImage(destImgName);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        for (int c = 0; c < 3; c++) {
          assertEquals(expected[i][j][c], result[i][j][c], 0.1);
        }
      }
    }
  }

  @Test
  public void testCompress() {
    double[][][] originalImage = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
        {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
        {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};

    double[][][] expected = {{{0.0, 2.8, 3.4}, {0.0, 2.8, 3.4}, {0.0, 2.8, 3.4}},
        {{0.0, 2.8, 3.4}, {0.0, 2.8, 3.4}, {0.0, 2.8, 3.4}},
        {{0.0, 2.8, 3.4}, {0.0, 2.8, 3.4}, {0.0, 2.8, 3.4}}};

    String srcImgName = "img1";
    String destImgName = "img2";

    storage.putImage(srcImgName, originalImage);
    imageModel.compressImage(srcImgName, destImgName, 90);
    double[][][] result = storage.getImage(destImgName);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        for (int c = 0; c < 3; c++) {
          assertEquals(expected[i][j][c], result[i][j][c], 0.1);
        }
      }
    }
  }

  @Test
  public void testCompressFileSize() {
    double[][][] originalImage = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
        {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
        {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
    String srcImgName = "img1";
    String destImgName = "img2";

    storage.putImage(srcImgName, originalImage);
    imageModel.compressImage(srcImgName, destImgName, 90);

    imageModel.saveImg("compressTestOrg.jpg", "jpg", srcImgName);
    imageModel.saveImg("compressTestComp.jpg", "jpg", destImgName);

    try {
      long sizeOrg = Files.size(Paths.get("compressTestOrg.jpg"));
      long sizeComp = Files.size(Paths.get("compressTestComp.jpg"));
      assertTrue(sizeComp < sizeOrg);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testMultipleOperations() {
    double[][][] originalImage = testImg;
    double[][][] expected = {{{26, 23, 18}, {30, 27, 21}, {34, 31, 24}},
        {{14, 13, 10}, {18, 16, 12}, {22, 20, 15}},
        {{2, 2, 1}, {6, 5, 4}, {10, 9, 7}}};

    String srcImgName = "img1";
    String midImgName = "img2";
    String destImgName = "img3";

    storage.putImage(srcImgName, originalImage);
    imageModel.flipHorizontally(srcImgName, midImgName);
    imageModel.sepiaToneImage(midImgName, destImgName, 0);
    assertEquals(expected, storage.getImage("img3"));
  }

  @Test
  public void testPPM() {
    double[][][] originalImage = testImg;

    String srcImgName = "img1";
    String destImgName = "img2";

    storage.putImage(srcImgName, originalImage);
    imageModel.saveImg("testImg.ppm", "ppm", srcImgName);
    imageModel.loadImg("testImg.ppm", "ppm", destImgName);

    assertEquals(originalImage, storage.getImage("img2"));
  }


}
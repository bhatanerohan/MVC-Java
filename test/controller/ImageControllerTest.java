package controller;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import model.ImageModelStub;
import model.utils.ImageStorage;
import org.junit.Before;
import org.junit.Test;
import view.ImageView;

/**
 * Class to test implementation of controller.
 */
public class ImageControllerTest {

  private ImageModelStub imageModelStub;
  private ImageView imageViewTest;

  private String imagePath = "./images/";

  @Before
  public void setup() {
    ImageStorage storage = new ImageStorage();
    imageModelStub = new ImageModelStub(storage);
    imageViewTest = new ImageView(imageModelStub);
  }

  private void runCommand(String command) {
    InputStream inputStream = new ByteArrayInputStream(command.getBytes());
    System.setIn(inputStream);

    Scanner scanner = new Scanner(System.in);
    ImageController imageController = new ImageController(imageModelStub, imageViewTest);

    imageController.run(scanner);
  }

  @Test
  public void testLoadImg() {
    String inputPath = this.imagePath + "fallneu.jpg";
    String input = "load " + inputPath + " fallneu\nexit\n";
    System.out.println(input);
    runCommand(input);
    assertTrue(imageModelStub.isLoadImgCalled());
  }

  @Test
  public void testSaveImg() {
    String input = "save " + this.imagePath + "fallneu-save.png fallneu\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isSaveImgCalled());
  }

  @Test
  public void testGetRedImage() {
    String input = "red-component fallneu fallneu-red\n\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isGetRedImageCalled());
  }

  @Test
  public void testGetGreenImage() {
    String input = "green-component fallneu fallneu-green\n\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isGetGreenImageCalled());
  }

  @Test
  public void testGetBlueImage() {
    String input = "blue-component fallneu fallneu-blue\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isGetBlueImageCalled());
  }

  @Test
  public void testFlipVertically() {
    String input = "vertical-flip fallneu fallneu-vert\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isFlipVerticallyCalled());
  }

  @Test
  public void testFlipHorizontally() {
    String input = "horizontal-flip fallneu fallneu-horiz\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isFlipHorizontallyCalled());
  }

  @Test
  public void testBrightenImage() {
    String input = "brighten 10 fallneu fallneu-bright\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isBrightenImageCalled());
  }

  @Test
  public void testDarkenImage() {
    String input = "darken 10 fallneu fallneu-dark\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isDarkenImageCalled());
  }

  @Test
  public void testBlurImage() {
    String input = "blur fallneu fallneu-blur\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isBlurImageCalled());
  }

  @Test
  public void testSharpenImage() {
    String input = "sharpen fallneu fallneu-sharp\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isSharpenImageCalled());
  }

  @Test
  public void testConvertToGrayscale() {
    String input = "grayscale fallneu fallneu-gray\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isConvertToGrayscaleCalled());
  }

  @Test
  public void testSepiaToneImage() {
    String input = "sepia fallneu fallneu-sepia\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isSepiaToneImageCalled());
  }

  @Test
  public void testCombineRGB() {
    String input = "rgb-combine fallneu-comb fallneu-red, fallneu-green, fallneu-blue\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isCombineRGBCalled());
  }

  @Test
  public void testGetValueGrayscale() {
    String input = "value-component fallneu fallneu-val\nexit\n";
    runCommand(input);

    assertTrue(imageModelStub.isGetValueGrayscaleCalled());
  }

  @Test
  public void testGetIntensityGrayscale() {
    String input = "intensity-component fallneu fallneu-int\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isGetIntensityGrayscaleCalled());
  }

  @Test
  public void testGetLumaGrayscale() {
    String input = "luma-component fallneu fallneu-luma\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isGetLumaGrayscaleCalled());
  }

  @Test
  public void testCompressImage() {
    String input = "compress 20 fallneu fallneu-compressed-20\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isCompressImageCalled());
  }

  @Test
  public void testGenerateHistogram() {
    String input = "histogram fallneu fallneu-hist\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isGenerateHistogramCalled());
  }

  @Test
  public void testColorCorrection() {
    String input = "color-correct fallneu fallneu-color-correct\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isGetColorCorrectionCalled());
  }

  @Test
  public void testLevelsAdjust() {
    String input = "levels-adjust 20 100 255 fallneu fallneu-levels-adjust\nexit\n";
    runCommand(input);
    assertTrue(imageModelStub.isGetLevelAdjustmentCalled());
  }

}





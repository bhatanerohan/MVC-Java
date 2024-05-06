package controller;

import static org.junit.Assert.assertTrue;

import model.ImageModelStub;
import model.utils.ImageStorage;
import org.junit.Before;
import org.junit.Test;
import view.ImageView;

/**
 * Class to test implementation of controller.
 */
public class ImageControllerGUITest {

  private ImageModelStub imageModelStub;

  private String displayImage = "dummy_name_1";

  private String originalImage = "dummy_name_2";

  private String histogramImage = "dummy_name_3";

  private String prevImageName = "dummy_name_4";

  private String splitPreviewImage = "dummy_name_5";

  @Before
  public void setup() {
    ImageView imageViewTest;

    ImageStorage storage = new ImageStorage();
    imageModelStub = new ImageModelStub(storage);
    imageViewTest = new ImageView(imageModelStub);
  }

  @Test
  public void testLoadGUI() {
    String filePath = "dummy_file.png";
    String[] tokens = filePath.split("\\.");
    imageModelStub.loadImg(filePath, tokens[1], displayImage);
    imageModelStub.copyImage(displayImage, originalImage);
    imageModelStub.generateHistogram(displayImage, histogramImage);

    assertTrue(imageModelStub.isLoadImgCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());
  }

  @Test
  public void testSaveGUI() {
    String filePath = "dummy_file.png";
    String[] tokens = filePath.split("\\.");
    imageModelStub.saveImg(filePath, tokens[1], displayImage);
    assertTrue(imageModelStub.isSaveImgCalled());
  }

  @Test
  public void testResetToPreviousGUI() {
    imageModelStub.copyImage(prevImageName, displayImage);
    imageModelStub.generateHistogram(displayImage, histogramImage);
    assertTrue(imageModelStub.isGenerateHistogramCalled());
  }

  @Test
  public void testResetToOriginalGUI() {
    imageModelStub.copyImage(originalImage, displayImage);
    imageModelStub.generateHistogram(displayImage, histogramImage);
    assertTrue(imageModelStub.isGenerateHistogramCalled());
  }

  @Test
  public void testRedComponentGUI() {
    imageModelStub.copyImage(displayImage, prevImageName);
    imageModelStub.getRedImage(displayImage, displayImage);
    imageModelStub.generateHistogram(displayImage, histogramImage);
    assertTrue(imageModelStub.isGetRedImageCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());
  }

  @Test
  public void testGreenComponentGUI() {
    imageModelStub.copyImage(displayImage, prevImageName);

    imageModelStub.getGreenImage(displayImage, displayImage);
    imageModelStub.generateHistogram(displayImage, histogramImage);

    assertTrue(imageModelStub.isGetGreenImageCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());

  }

  @Test
  public void testBlueComponentGUI() {
    imageModelStub.copyImage(displayImage, prevImageName);

    imageModelStub.getBlueImage(displayImage, displayImage);
    imageModelStub.generateHistogram(displayImage, histogramImage);


    assertTrue(imageModelStub.isGetBlueImageCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());
  }

  @Test
  public void testHorizontalFlipGUI() {
    imageModelStub.copyImage(displayImage, prevImageName);

    imageModelStub.flipHorizontally(displayImage, displayImage);
    imageModelStub.generateHistogram(displayImage, histogramImage);


    assertTrue(imageModelStub.isFlipHorizontallyCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());

  }

  @Test
  public void testVerticalFlipGUI() {
    imageModelStub.copyImage(displayImage, prevImageName);

    imageModelStub.flipVertically(displayImage, displayImage);
    imageModelStub.generateHistogram(displayImage, histogramImage);


    assertTrue(imageModelStub.isFlipVerticallyCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());
  }


  @Test
  public void testBlurGUI() {
    imageModelStub.copyImage(displayImage, prevImageName);

    imageModelStub.getBlurredImage(displayImage, displayImage, 0);
    imageModelStub.generateHistogram(displayImage, histogramImage);


    assertTrue(imageModelStub.isBlurImageCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());

  }

  @Test
  public void testSharpenGUI() {

    imageModelStub.copyImage(displayImage, prevImageName);

    imageModelStub.getSharpenedImage(displayImage, displayImage, 0);
    imageModelStub.generateHistogram(displayImage, histogramImage);

    assertTrue(imageModelStub.isSharpenImageCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());

  }

  @Test
  public void testGrayscaleLumaGUI() {
    imageModelStub.copyImage(displayImage, prevImageName);

    imageModelStub.getGrayscaleImage(displayImage, displayImage, 0);
    imageModelStub.generateHistogram(displayImage, histogramImage);

    assertTrue(imageModelStub.isConvertToGrayscaleCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());

  }

  @Test
  public void testSepiaGUI() {
    imageModelStub.copyImage(displayImage, prevImageName);

    imageModelStub.sepiaToneImage(displayImage, displayImage, 0);
    imageModelStub.generateHistogram(displayImage, histogramImage);


    assertTrue(imageModelStub.isSepiaToneImageCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());
  }

  @Test
  public void testCompressGUI() {
    imageModelStub.copyImage(displayImage, prevImageName);

    int p = 20;

    imageModelStub.compressImage(displayImage, displayImage, p);
    imageModelStub.generateHistogram(displayImage, histogramImage);

    assertTrue(imageModelStub.isCompressImageCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());
  }

  @Test
  public void testColorCorrectGUI() {
    imageModelStub.copyImage(displayImage, prevImageName);

    int p = 0;
    imageModelStub.getColorCorrection(displayImage, displayImage, p);
    imageModelStub.generateHistogram(displayImage, histogramImage);

    assertTrue(imageModelStub.isGetColorCorrectionCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());

  }

  @Test
  public void testLevelsAdjustGUI() {
    imageModelStub.copyImage(displayImage, prevImageName);

    int[] bmw = {0,100,200};
    int p = 0;
    int b = bmw[0];
    int m = bmw[1];
    int w = bmw[2];
    imageModelStub.getLevelAdjustment(b, m, w, displayImage, displayImage, p);
    imageModelStub.generateHistogram(displayImage, histogramImage);

    assertTrue(imageModelStub.isGetLevelAdjustmentCalled());
    assertTrue(imageModelStub.isGenerateHistogramCalled());
  }


}

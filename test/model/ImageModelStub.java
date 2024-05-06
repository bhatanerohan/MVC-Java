package model;

import model.utils.ImageStorage;

/**
 * Stub class to check the functionality of Model.
 */
public class ImageModelStub extends ImageModel implements ModelInterface {

  private boolean loadImgCalled = false;
  private boolean saveImgCalled = false;
  private boolean getRedImageCalled = false;
  private boolean getGreenImageCalled = false;
  private boolean getBlueImageCalled = false;
  private boolean flipVerticallyCalled = false;
  private boolean flipHorizontallyCalled = false;
  private boolean brightenImageCalled = false;
  private boolean darkenImageCalled = false;
  private boolean blurImageCalled = false;
  private boolean sharpenImageCalled = false;
  private boolean convertToGrayscaleCalled = false;
  private boolean sepiaToneImageCalled = false;
  private boolean combineRGBgCalled = false;
  private boolean getValueGrayscaleCalled = false;
  private boolean getIntensityGrayscaleCalled = false;
  private boolean getLumaGrayscaleCalled = false;
  private boolean compressImageCalled = false;
  private boolean generateHistogramCalled = false;
  private boolean getColorCorrectionCalled = false;
  private boolean getLevelAdjustmentCalled = false;

  public ImageModelStub(ImageStorage st) {
    super(st);
  }


  public void loadImg(String path, String ext, String imageName) {
    loadImgCalled = true;
  }

  public void saveImg(String path, String ext, String imageName) {
    saveImgCalled = true;
  }

  public void getRedImage(String srcImgName, String destImgName) {
    getRedImageCalled = true;
  }

  public void getGreenImage(String srcImgName, String destImgName) {
    getGreenImageCalled = true;
  }

  public void getBlueImage(String srcImgName, String destImgName) {
    getBlueImageCalled = true;
  }

  public void flipVertically(String srcImgName, String destImgName) {
    flipVerticallyCalled = true;
  }

  public void flipHorizontally(String srcImgName, String destImgName) {
    flipHorizontallyCalled = true;
  }

  public void brightenImage(String srcImgName, String destImgName, int value) {
    brightenImageCalled = true;
  }

  public void darkenImage(String srcImgName, String destImgName, int value) {
    darkenImageCalled = true;
  }

  public void getBlurredImage(String srcImgName, String destImgName, int p) {
    blurImageCalled = true;
  }

  public void getSharpenedImage(String srcImgName, String destImgName, int p) {
    sharpenImageCalled = true;
  }

  public void getGrayscaleImage(String srcImgName, String destImgName, int p) {
    convertToGrayscaleCalled = true;
  }

  public void sepiaToneImage(String srcImgName, String destImgName, int p) {
    sepiaToneImageCalled = true;
  }

  public void combineRGB(String redName, String greenName, String blueName, String destImgName) {
    combineRGBgCalled = true;
  }

  public void getValueGrayscale(String srcImgName, String destImgName) {
    getValueGrayscaleCalled = true;
  }

  public void getIntensityGrayscale(String srcImgName, String destImgName) {
    getIntensityGrayscaleCalled = true;
  }

  public void getLumaGrayscale(String srcImgName, String destImgName) {
    getLumaGrayscaleCalled = true;
  }

  public void compressImage(String srcImgName, String destImgName, double percentage) {
    compressImageCalled = true;
  }

  public void generateHistogram(String srcImgName, String destImgName) {
    generateHistogramCalled = true;
  }

  public void getColorCorrection(String srcImgName, String destImgName, int p) {
    getColorCorrectionCalled = true;
  }

  public void getLevelAdjustment(int b, int m, int w, String srcImgName, String destImgName,
                                 int p) {
    getLevelAdjustmentCalled = true;
  }


  public boolean isLoadImgCalled() {
    return loadImgCalled;
  }

  public boolean isSaveImgCalled() {
    return saveImgCalled;
  }

  public boolean isGetRedImageCalled() {
    return getRedImageCalled;
  }

  public boolean isGetGreenImageCalled() {
    return getGreenImageCalled;
  }

  public boolean isGetBlueImageCalled() {
    return getBlueImageCalled;
  }

  public boolean isFlipVerticallyCalled() {
    return flipVerticallyCalled;
  }

  public boolean isFlipHorizontallyCalled() {
    return flipHorizontallyCalled;
  }

  public boolean isBrightenImageCalled() {
    return brightenImageCalled;
  }

  public boolean isDarkenImageCalled() {
    return darkenImageCalled;
  }

  public boolean isBlurImageCalled() {
    return blurImageCalled;
  }

  public boolean isSharpenImageCalled() {
    return sharpenImageCalled;
  }

  public boolean isConvertToGrayscaleCalled() {
    return convertToGrayscaleCalled;
  }

  public boolean isSepiaToneImageCalled() {
    return sepiaToneImageCalled;
  }

  public boolean isCombineRGBCalled() {
    return combineRGBgCalled;
  }

  public boolean isGetValueGrayscaleCalled() {
    return getValueGrayscaleCalled;
  }

  public boolean isGetIntensityGrayscaleCalled() {
    return getIntensityGrayscaleCalled;
  }

  public boolean isGetLumaGrayscaleCalled() {
    return getLumaGrayscaleCalled;
  }

  public boolean isCompressImageCalled() {
    return compressImageCalled;
  }

  public boolean isGenerateHistogramCalled() {
    return generateHistogramCalled;
  }

  public boolean isGetColorCorrectionCalled() {
    return getColorCorrectionCalled;
  }

  public boolean isGetLevelAdjustmentCalled() {
    return getLevelAdjustmentCalled;
  }
}

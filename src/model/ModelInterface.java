package model;

/**
 * Interface for ImageModel.
 */
public interface ModelInterface {

  void loadImg(String path, String ext, String imageName);

  void saveImg(String path, String ext, String imageName);

  void getRedImage(String srcImgName, String destImgName);

  void getGreenImage(String srcImgName, String destImgName);

  void getBlueImage(String srcImgName, String destImgName);

  void flipVertically(String srcImgName, String destImgName);

  void flipHorizontally(String srcImgName, String destImgName);

  void brightenImage(String srcImgName, String destImgName, int value);

  void darkenImage(String srcImgName, String destImgName, int value);

  void getBlurredImage(String srcImgName, String destImgName, int p);

  void getSharpenedImage(String srcImgName, String destImgName, int p);

  void getGrayscaleImage(String srcImgName, String destImgName, int p);

  void sepiaToneImage(String srcImgName, String destImgName, int p);

  void combineRGB(String redName, String greenName, String blueName, String destImgName);

  void getValueGrayscale(String srcImgName, String destImgName);

  void getIntensityGrayscale(String srcImgName, String destImgName);

  void getLumaGrayscale(String srcImgName, String destImgName);

  void compressImage(String srcImgName, String destImgName, double percentage);

  void generateHistogram(String srcImgName, String destImgName);

  void getColorCorrection(String srcImgName, String destImgName, int p);

  void getLevelAdjustment(int b, int m, int w, String srcImgName, String destImgName, int p);

  double[][][] getImage(String srcImgName);

  void copyImage(String displayImage, String displayImage1);

  void getSplitImage(String originalImage, String srcImgName, String destImgName, int p);
}

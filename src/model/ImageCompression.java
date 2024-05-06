package model;

/**
 * Interface for image compression algorithms.
 */
public interface ImageCompression {


  /**
   * Method to compress an image.
   *
   * @param srcImgName src img name
   * @param destImgName dest img name
   * @param percentage compression percentage
   */
  void compressImage(String srcImgName, String destImgName, double percentage);
}

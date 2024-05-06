package model.imageops;

import model.utils.ImageClamp;
import model.utils.ImageStorage;

/**
 * Implements sharpening of an image.
 */
public class SharpenImage {

  private final ImageStorage storage;
  private final FilterImage filterImage = new FilterImage();
  private final ImageSplitter imageSplitter = new ImageSplitter();
  private final ImageClamp clamp = new ImageClamp();

  public SharpenImage(ImageStorage st) {
    this.storage = st;
  }

  /**
   * Get sharpened image.
   *
   * @param srcImgName src image
   * @param destImgName dest image
   * @param p split percent
   */
  public void applySharpen(String srcImgName, String destImgName, int p) {
    float[][] filter = {
        {-0.125f, -0.125f, -0.125f, -0.125f, -0.125f},
        {-0.125f, 0.25f, 0.25f, 0.25f, -0.125f},
        {-0.125f, 0.25f, 1, 0.25f, -0.125f},
        {-0.125f, 0.25f, 0.25f, 0.25f, -0.125f},
        {-0.125f, -0.125f, -0.125f, -0.125f, -0.125f}
    };
    double[][][] converted = filterImage.applyFilter(storage.getImage(srcImgName), filter);
    converted = imageSplitter.splitImage(storage.getImage(srcImgName), converted, p);
    storage.putImage(destImgName, clamp.clampImage(converted));
  }


}

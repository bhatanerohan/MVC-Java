package model.imageops;

import model.utils.ImageClamp;
import model.utils.ImageStorage;

/**
 * Implements blurring of an image.
 */
public class BlurImage {

  private final FilterImage filterImage = new FilterImage();
  private final ImageSplitter imageSplitter = new ImageSplitter();
  private final ImageClamp clamp = new ImageClamp();
  private ImageStorage storage;

  public BlurImage(ImageStorage st) {
    this.storage = st;
  }

  /**
   * Applies blur to an image.
   *
   * @param srcImgName src image
   * @param destImgName dest image
   * @param p split percentage
   */
  public void applyBlur(String srcImgName, String destImgName, int p) {
    float[][] filter = {
        {0.0625f, 0.125f, 0.0625f},
        {0.125f, 0.25f, 0.125f},
        {0.0625f, 0.125f, 0.0625f}
    };
    double[][][] converted = filterImage.applyFilter(storage.getImage(srcImgName), filter);
    converted = imageSplitter.splitImage(storage.getImage(srcImgName), converted, p);
    storage.putImage(destImgName, clamp.clampImage(converted));
  }


}

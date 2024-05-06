package model.imageops;

import model.ImageCompression;
import model.utils.ImageClamp;
import model.utils.ImagePadder;
import model.utils.ImageStorage;

/**
 * Implements compression of image using haar transform.
 */
public class HaarCompressor implements ImageCompression {

  private final HaarTransform transformer = new HaarTransform();
  private final ImageThresholder thresholder = new ImageThresholder();
  private final ImagePadder padder = new ImagePadder();
  private final ImageClamp clamp = new ImageClamp();
  private ImageStorage storage;

  public HaarCompressor(ImageStorage st) {
    this.storage = st;
  }

  /**
   * Compresses image with haar transform.
   *
   * @param srcImgName src img name
   * @param destImgName dest img name
   * @param percentage compression percentage
   */
  @Override
  public void compressImage(String srcImgName, String destImgName, double percentage) {

    double[][][] image = storage.getImage(srcImgName);
    int originalWidth = image.length;
    int originalHeight = image[0].length;

    image = padder.padImage(image);
    image = transformer.transformImage(image);
    if (percentage > 0) {
      image = thresholder.thresholdImage(image, percentage);
    }
    image = transformer.inverseTransformImage(image);
    image = padder.unpadImage(image, originalWidth, originalHeight);
    storage.putImage(destImgName, clamp.clampImage(image));
  }
}


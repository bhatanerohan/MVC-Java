package model;

import model.imageops.AdjustBrightness;
import model.imageops.BlurImage;
import model.imageops.ColorCorrection;
import model.imageops.CombineColours;
import model.imageops.FlipImage;
import model.imageops.GrayscaleImage;
import model.imageops.HaarCompressor;
import model.imageops.HistogramPlot;
import model.imageops.ImageSplitter;
import model.imageops.LevelAdjustment;
import model.imageops.SepiaTone;
import model.imageops.SharpenImage;
import model.imageops.SplitColours;
import model.imageops.VILImage;
import model.utils.ImageLoader;
import model.utils.ImageSaver;
import model.utils.ImageStorage;

/**
 * This class implements image model, which incorporates multiple operations on an image with its
 * methods.
 */
public class ImageModel implements ModelInterface {

  private final ImageStorage storage;
  private final SplitColours splitColours;
  private final BlurImage blurImage;
  private final SharpenImage sharpenImage;
  private final GrayscaleImage grayscaleImage;
  private final FlipImage flipImage;
  private final AdjustBrightness adjustBrightness;
  private final SepiaTone sepiaTone;
  private final CombineColours combineColours;
  private final VILImage vilImage;
  private final HaarCompressor haarCompressor;
  private final HistogramPlot histogramPlot;
  private final ColorCorrection colorCorrection;
  private final LevelAdjustment levelAdjustment;
  private ImageSplitter imageSplitter;
  private double[][][] image;


  /**
   * Model to execute operations on image.
   * @param st image storage object for read and write
   */
  public ImageModel(ImageStorage st) {
    storage = st;
    splitColours = new SplitColours(storage);
    blurImage = new BlurImage(storage);
    sharpenImage = new SharpenImage(storage);
    grayscaleImage = new GrayscaleImage(storage);
    flipImage = new FlipImage(storage);
    adjustBrightness = new AdjustBrightness(storage);
    sepiaTone = new SepiaTone(storage);
    combineColours = new CombineColours(storage);
    vilImage = new VILImage(storage);
    haarCompressor = new HaarCompressor(storage);
    histogramPlot = new HistogramPlot(storage);
    colorCorrection = new ColorCorrection(storage);
    levelAdjustment = new LevelAdjustment(storage);
    imageSplitter = new ImageSplitter();
  }


  /**
   * Method to load image from a path.
   *
   * @param path      image path
   * @param ext       extension of the image
   * @param imageName name to be used to refer to the image
   * @throws IllegalArgumentException if image extension is not valid
   */
  public void loadImg(String path, String ext, String imageName) {
    if (!(ext.equals("png") || ext.equals("jpg") || ext.equals("ppm"))) {
      System.err.println("Unsupported file format");
      return;
    }
    try {
      ImageLoader loader = new ImageLoader();
      image = loader.getImageArray(path, ext);
      storage.putImage(imageName, image);
    } catch (Exception ignored) {
    }
  }

  /**
   * Method to save image to the given path.
   *
   * @param path      path to save image to
   * @param ext       extension of the image to save
   * @param imageName name of the image
   * @throws IllegalArgumentException if the extension is not valid
   */
  public void saveImg(String path, String ext, String imageName) {
    if (!(ext.equals("png") || ext.equals("jpg") || ext.equals("ppm"))) {
      System.err.println("Unsupported file format");
      return;
    }
    ImageSaver saver = new ImageSaver();
    image = storage.getImage(imageName);
    if (image == null) {
      System.err.println("Image not found in database. Check again.");
    } else {
      if (ext.equals("ppm")) {
        saver.saveAsPPM(image, path);
      } else {
        saver.saveImg(image, path, ext);
      }
    }
  }

  /**
   * Get red image.
   *
   * @param srcImgName  src img name
   * @param destImgName dest image name
   */
  public void getRedImage(String srcImgName, String destImgName) {
    splitColours.redImage(srcImgName, destImgName);
  }

  /**
   * Get green image.
   *
   * @param srcImgName  src img name
   * @param destImgName dest image name
   */
  public void getGreenImage(String srcImgName, String destImgName) {
    splitColours.greenImage(srcImgName, destImgName);
  }

  /**
   * Get blue image.
   *
   * @param srcImgName  src img name
   * @param destImgName dest image name
   */
  public void getBlueImage(String srcImgName, String destImgName) {
    splitColours.blueImage(srcImgName, destImgName);
  }

  /**
   * Get vertically flipped image.
   *
   * @param srcImgName  src img name
   * @param destImgName dest image name
   */
  public void flipVertically(String srcImgName, String destImgName) {
    flipImage.applyVerticalFlip(srcImgName, destImgName);
  }

  /**
   * Get horizontally flipped image.
   *
   * @param srcImgName  src img name
   * @param destImgName dest image name
   */
  public void flipHorizontally(String srcImgName, String destImgName) {
    flipImage.applyHorizontalFLip(srcImgName, destImgName);
  }

  /**
   * Get brightened image.
   *
   * @param value       brightness
   * @param srcImgName  src img name
   * @param destImgName dest image name
   */
  public void brightenImage(String srcImgName, String destImgName, int value) {
    adjustBrightness.applyBrighten(srcImgName, destImgName, value);
  }

  /**
   * Get darkened image.
   *
   * @param srcImgName  src img name
   * @param destImgName dest image name
   * @param value       value to reduce
   */
  public void darkenImage(String srcImgName, String destImgName, int value) {
    adjustBrightness.applyDarken(srcImgName, destImgName, value);
  }


  /**
   * Get blurred image.
   *
   * @param srcImgName  src img name
   * @param destImgName dest image name
   */
  public void getBlurredImage(String srcImgName, String destImgName, int p) {
    blurImage.applyBlur(srcImgName, destImgName, p);
  }

  /**
   * Get sharpened image.
   *
   * @param srcImgName  src img name
   * @param destImgName src image name
   */
  public void getSharpenedImage(String srcImgName, String destImgName, int p) {
    sharpenImage.applySharpen(srcImgName, destImgName, p);
  }

  /**
   * Get grayscale image.
   *
   * @param srcImgName  src image name
   * @param destImgName dest image name
   */
  public void getGrayscaleImage(String srcImgName, String destImgName, int p) {
    grayscaleImage.applyGrayscale(srcImgName, destImgName, p);
  }

  /**
   * Get sepia toned image.
   *
   * @param srcImgName  src img name
   * @param destImgName dest image name
   */
  public void sepiaToneImage(String srcImgName, String destImgName, int p) {
    sepiaTone.applySepia(srcImgName, destImgName, p);
  }

  /**
   * Combine red, blue and green to one image.
   *
   * @param redName     red image name
   * @param greenName   green image name
   * @param blueName    blue image name
   * @param destImgName dest image name
   */
  public void combineRGB(String redName, String greenName, String blueName, String destImgName) {
    combineColours.applyCombine(redName, greenName, blueName, destImgName);
  }

  /**
   * Get value only grayscale.
   *
   * @param srcImgName  src image name
   * @param destImgName dest image name
   */
  public void getValueGrayscale(String srcImgName, String destImgName) {
    vilImage.applyValue(srcImgName, destImgName);
  }

  /**
   * Get intensity only grayscale.
   *
   * @param srcImgName  src image name
   * @param destImgName dest image name
   */
  public void getIntensityGrayscale(String srcImgName, String destImgName) {
    vilImage.applyIntensity(srcImgName, destImgName);
  }

  /**
   * Get luma only grayscale.
   *
   * @param srcImgName  src image name
   * @param destImgName dest image name
   */
  public void getLumaGrayscale(String srcImgName, String destImgName) {
    vilImage.applyLuma(srcImgName, destImgName);
  }

  /**
   * Method to compress an image using haar transform.
   *
   * @param srcImgName src image name
   * @param destImgName dest image name
   */
  public void compressImage(String srcImgName, String destImgName, double percentage) {
    haarCompressor.compressImage(srcImgName, destImgName, percentage);
  }

  /**
   * Method to generate RGB histogram of an image.
   *
   * @param srcImgName src image name
   * @param destImgName dest image name
   */
  public void generateHistogram(String srcImgName, String destImgName) {
    histogramPlot.getHistogram(srcImgName, destImgName);
  }

  /**
   * Method for color correction of an image.
   *
   * @param srcImgName src image name
   * @param destImgName destination image name
   */
  public void getColorCorrection(String srcImgName, String destImgName, int p) {
    colorCorrection.colorCorrect(srcImgName, destImgName, p);
  }

  /**
   * Method to apply level adjustment to an image.
   *
   * @param b position of black
   * @param m position of mid
   * @param w position of white
   * @param srcImgName source image name
   * @param destImgName destination image name
   */
  public void getLevelAdjustment(int b, int m, int w, String srcImgName, String destImgName,
      int p) {
    levelAdjustment.adjustLevel(b, m, w, srcImgName, destImgName, p);
  }

  @Override
  public double[][][] getImage(String srcImgName) {
    return storage.getImage(srcImgName);
  }

  public void copyImage(String srcImgName, String destImgName) {
    storage.putImage(destImgName, storage.getImage(srcImgName));
  }

  /**
   * Splits an image based on the specified percentage, merging portions from two source images.
   *
   * @param orgImgName The name of the first source image used in the splitting process.
   * @param srcImgName The name of the second source image used in the splitting process.
   * @param destImgName The name under which the resulting split image will be stored.
   * @param p The percentage split between previous and current image
   *
   */
  public void getSplitImage(String orgImgName, String srcImgName, String destImgName, int p) {
    double[][][] splitImage = imageSplitter.splitImage(storage.getImage(orgImgName),
            storage.getImage(srcImgName), p);
    storage.putImage(destImgName, splitImage);
  }
}

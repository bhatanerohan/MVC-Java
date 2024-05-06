package model.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to store images for the program.
 */
public class ImageStorage {

  private Map<String, double[][][]> imageMap = new HashMap<>();

  /**
   * Get image by key.
   *
   * @param key key string
   * @return double[][][] image
   */
  public double[][][] getImage(String key) {
    return imageMap.get(key);
  }

  /**
   * Check if key exists in map.
   *
   * @param key image key
   * @return true or false
   */
  public boolean checkKey(String key) {
    return imageMap.containsKey(key);
  }

  /**
   * Insert image at key.
   *
   * @param key      image key
   * @param keyImage double[][][] image
   */
  public void putImage(String key, double[][][] keyImage) {
    imageMap.put(key, keyImage);
  }

}

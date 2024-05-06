package controller;

import model.ModelInterface;

/**
 * The ProcessCommand class is responsible for processing commands related to image manipulation.
 * This class acts as a controller in the MVC pattern, translating user input into actions
 * on the model.
 */
public class ProcessCommand {

  /**
   * Processes a command line for image processing. It interprets and executes commands
   * based on the given tokens and the associated ModelInterface. Supported operations
   * include image loading, saving, color manipulation, transformations, and applying filters.
   *
   * @param tokens Array of strings representing the command and its arguments.
   * @param imageModel The ModelInterface instance for executing image processing tasks.
   */
  public void processLine(String[] tokens, ModelInterface imageModel) {
    String command = tokens[0];
    int p;

    switch (command) {
      case "load":
        if (tokens.length != 3) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        String[] fileNameTemp = tokens[1].split("\\.");
        String extension = fileNameTemp[fileNameTemp.length - 1];
        imageModel.loadImg(tokens[1], extension, tokens[2]);
        break;
      case "save":
        if (tokens.length != 3) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        String[] fileNameSave = tokens[1].split("\\.");
        String ext = fileNameSave[fileNameSave.length - 1];
        imageModel.saveImg(tokens[1], ext, tokens[2]);
        break;
      case "red-component":
        if (tokens.length != 3) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        imageModel.getRedImage(tokens[1], tokens[2]);
        break;
      case "green-component":
        if (tokens.length != 3) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        imageModel.getGreenImage(tokens[1], tokens[2]);
        break;
      case "blue-component":
        if (tokens.length != 3) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        imageModel.getBlueImage(tokens[1], tokens[2]);
        break;
      case "horizontal-flip":
        if (tokens.length != 3) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        imageModel.flipHorizontally(tokens[1], tokens[2]);
        break;
      case "vertical-flip":
        if (tokens.length != 3) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        imageModel.flipVertically(tokens[1], tokens[2]);
        break;
      case "brighten":
        if (tokens.length != 4) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        imageModel.brightenImage(tokens[2], tokens[3], Integer.parseInt(tokens[1]));
        break;
      case "darken":
        if (tokens.length != 4) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        imageModel.darkenImage(tokens[2], tokens[3], Integer.parseInt(tokens[1]));
        break;
      case "rgb-split":
        if (tokens.length != 5) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        String destImageNameRed = tokens[2];
        String destImageNameGreen = tokens[3];
        String destImageNameBlue = tokens[4];
        imageModel.getRedImage(tokens[1], destImageNameRed);
        imageModel.getGreenImage(tokens[1], destImageNameGreen);
        imageModel.getBlueImage(tokens[1], destImageNameBlue);
        break;
      case "rgb-combine":
        if (tokens.length != 5) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        String redImageName = tokens[2];
        String greenImageName = tokens[3];
        String blueImageName = tokens[4];
        imageModel.combineRGB(redImageName, greenImageName, blueImageName, tokens[1]);
        break;
      case "blur":
        if (!(tokens.length == 3 || tokens.length == 5)) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        p = 0;
        if (tokens.length == 5) {
          p = Integer.parseInt(tokens[4]);
        }
        imageModel.getBlurredImage(tokens[1], tokens[2], p);
        break;
      case "sharpen":
        if (!(tokens.length == 3 || tokens.length == 5)) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        p = 0;
        if (tokens.length == 5) {
          p = Integer.parseInt(tokens[4]);
        }
        imageModel.getSharpenedImage(tokens[1], tokens[2], p);
        break;
      case "grayscale":
        if (!(tokens.length == 3 || tokens.length == 5)) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        p = 0;
        if (tokens.length == 5) {
          p = Integer.parseInt(tokens[4]);
        }
        imageModel.getGrayscaleImage(tokens[1], tokens[2], p);
        break;
      case "sepia":
        if (!(tokens.length == 3 || tokens.length == 5)) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        p = 0;
        if (tokens.length == 5) {
          p = Integer.parseInt(tokens[4]);
        }
        imageModel.sepiaToneImage(tokens[1], tokens[2], p);
        break;
      case "value-component":
        if (tokens.length != 3) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        imageModel.getValueGrayscale(tokens[1], tokens[2]);
        break;
      case "intensity-component":
        if (tokens.length != 3) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        imageModel.getIntensityGrayscale(tokens[1], tokens[2]);
        break;
      case "luma-component":
        if (tokens.length != 3) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        imageModel.getLumaGrayscale(tokens[1], tokens[2]);
        break;
      case "compress":
        if (tokens.length != 4) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        if (Integer.parseInt(tokens[1]) < 0 || Integer.parseInt(tokens[1]) > 100) {
          System.err.println("Invalid compression percentage");
          break;
        }
        imageModel.compressImage(tokens[2], tokens[3], Integer.parseInt(tokens[1]));
        break;
      case "histogram":
        if (tokens.length != 3) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        imageModel.generateHistogram(tokens[1], tokens[2]);
        break;
      case "color-correct":
        if (!(tokens.length == 3 || tokens.length == 5)) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        p = 0;
        if (tokens.length == 5) {
          p = Integer.parseInt(tokens[4]);
        }
        imageModel.getColorCorrection(tokens[1], tokens[2], p);
        break;
      case "levels-adjust":
        if (!(tokens.length == 6 || tokens.length == 8)) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        p = 0;
        if (tokens.length == 8) {
          p = Integer.parseInt(tokens[7]);
        }
        int b = Integer.parseInt(tokens[1]);
        int m = Integer.parseInt(tokens[2]);
        int w = Integer.parseInt(tokens[3]);
        imageModel.getLevelAdjustment(b, m, w, tokens[4], tokens[5], p);
        break;
      default:
        System.err.println("Invalid command/Command not supported");
    }
  }

}

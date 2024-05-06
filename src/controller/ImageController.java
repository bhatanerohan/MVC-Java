package controller;

import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Scanner;
import model.ModelInterface;
import view.ImageView;
import view.SplitPercentDialog;
import view.ViewInterface;

/**
 * Controller to handle the user requests and interact with the model to perform required image
 * operations.
 */
public class ImageController implements ControllerInterface {

  private final ModelInterface imageModel;
  private final SplitPercentDialog splitPercentDialog = new SplitPercentDialog();
  private ImageView imageView;
  private ProcessCommand processCommand;
  private String displayImage;
  private String originalImage;
  private String histogramImage;
  private String prevImageName;
  private String splitPreviewImage;

  /**
   * Constructor to initialize the model and the view passed.
   *
   * @param model ImageModel model
   */
  public ImageController(ModelInterface model, ViewInterface view) {
    imageModel = model;
    imageView = (ImageView) view;
    this.addListeners();
    processCommand = new ProcessCommand();
    displayImage = imageView.getDisplayImageName();
    originalImage = imageView.getOriginalImageName();
    histogramImage = imageView.getHistogramImageName();
    prevImageName = imageView.getPrevImageName();
    splitPreviewImage = imageView.getSplitPreviewImageName();
  }


  /**
   * Method to handle user requests.
   */
  public void run() {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Choose input source (1 for file, 2 for manual input): ");
    int inputChoice = scanner.nextInt();
    scanner.nextLine();

    if (inputChoice == 1) {
      processScriptFromFile();
    } else if (inputChoice == 2) {
      processScriptFromManualInput(scanner);
    } else {
      System.out.println("Invalid input choice.");
    }
  }

  /**
   * Method to handle user requests.
   */
  public void run(Scanner scanner) {
    processScriptFromManualInput(scanner);
  }

  @Override
  public void run(String fileName) {
    processScriptFromFile(fileName);
  }

  private void processScriptFromFile(String fileName) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      processScript(reader);
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
      run();

    }
  }

  private void processScriptFromFile() {
    Scanner scanner = new Scanner(System.in);
    String fileName = scanner.nextLine();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      processScript(reader);
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
      run();
    }
  }

  private void processScriptFromManualInput(Scanner scanner) {
    System.out.println("Enter command: ");
    String line;
    while (!(line = scanner.nextLine()).equalsIgnoreCase("exit")) {
      processScript(new BufferedReader(new StringReader(line)));
    }
  }

  private void processScript(BufferedReader reader) {
    String line;
    while (true) {
      try {
        if ((line = reader.readLine()) == null) {
          break;
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      if (line.startsWith("#") || line.startsWith(" ") || line.startsWith("\n")) {
        continue;
      }
      String[] tokens = line.split(" ");
      if (tokens[0].equals("run")) {
        if (tokens.length != 2) {
          System.err.println("Inappropriate number of arguments");
          break;
        }
        processScriptFromFile(tokens[1]);
        break;
      } else {
        processCommand.processLine(tokens, imageModel);
      }
    }
  }

  public void runGUI() {
    imageView.display();
  }

  private void addListeners() {
    imageView.buildUI();
    imageView.addLoadItemListener(e -> loadGUI());
    imageView.addSaveItemListener(e -> saveGUI());
    imageView.addResetOrginalListener(e -> resetToOriginalGUI());
    imageView.addResetPreviousListener(e -> resetToPreviousGUI());
    imageView.addRedButtonListener(e -> redComponentGUI());
    imageView.addGreenButtonListener(e -> greenComponentGUI());
    imageView.addBlueButtonListener(e -> blueComponentGUI());
    imageView.addFlipHorizontalButtonListener(e -> horizontalFlipGUI());
    imageView.addFlipVerticalButtonListener(e -> verticalFlipGUI());
    imageView.addBlurButtonListener(e -> blurGUI());
    imageView.addSharpenButtonListener(e -> sharpenGUI());
    imageView.addGrayscaleLumaButtonListener(e -> grayscaleLumaGUI());
    imageView.addSepiaButtonListener(e -> sepiaGUI());
    imageView.addCompressButtonListener(e -> compressGUI());
    imageView.addColorCorrectButtonListener(e -> colorCorrectGUI());
    imageView.addLevelsAdjustButtonListener(e -> levelsAdjustGUI());
    imageView.addToggleSplitListener(e -> {
      if (checkDisplayOp(displayImage)) {

        if (e.getStateChange() == ItemEvent.SELECTED) {
          toggleSplitGUISelect();
        } else {
          toggleSplitGUIDeselect();
        }
      }
    });
  }

  private void loadGUI() {
    String filePath = imageView.getLoadFilePath();
    String[] tokens = filePath.split("\\.");
    imageModel.loadImg(filePath, tokens[1], displayImage);
    imageModel.copyImage(displayImage, originalImage);
    imageModel.generateHistogram(displayImage, histogramImage);
    imageView.updateImage();
  }

  private void saveGUI() {
    String[] allowed = {"jpg", "png", "ppm"};
    String filePath = imageView.getSaveFilePath();
    if (filePath == null) {
      return;
    }
    String[] tokens = filePath.split("\\.");
    if (tokens.length != 2) {
      imageView.showSaveError();
    } else if (!Arrays.asList(allowed).contains(tokens[1])) {
      imageView.showSaveError();
    } else {
      tokens = filePath.split("\\.");
      if (checkDisplay(displayImage)) {
        imageModel.saveImg(filePath, tokens[1], displayImage);
        imageView.showImageSaved(filePath);
      }
    }
  }

  private void resetToPreviousGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(prevImageName, displayImage);
      imageModel.generateHistogram(displayImage, histogramImage);
      imageView.updateImage();
    }
  }

  private void resetToOriginalGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(originalImage, displayImage);
      imageModel.generateHistogram(displayImage, histogramImage);
      imageView.updateImage();
    }
  }

  private void redComponentGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(displayImage, prevImageName);
      imageModel.getRedImage(displayImage, displayImage);
      imageModel.generateHistogram(displayImage, histogramImage);
      imageView.updateImage();
    }
  }

  private void greenComponentGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(displayImage, prevImageName);

      imageModel.getGreenImage(displayImage, displayImage);
      imageModel.generateHistogram(displayImage, histogramImage);

      imageView.updateImage();
    }

  }

  private void blueComponentGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(displayImage, prevImageName);

      imageModel.getBlueImage(displayImage, displayImage);
      imageModel.generateHistogram(displayImage, histogramImage);

      imageView.updateImage();

    }
  }

  private void horizontalFlipGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(displayImage, prevImageName);

      imageModel.flipHorizontally(displayImage, displayImage);
      imageModel.generateHistogram(displayImage, histogramImage);

      imageView.updateImage();
    }
  }

  private void verticalFlipGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(displayImage, prevImageName);

      imageModel.flipVertically(displayImage, displayImage);
      imageModel.generateHistogram(displayImage, histogramImage);

      imageView.updateImage();
    }
  }


  private void blurGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(displayImage, prevImageName);

      imageModel.getBlurredImage(displayImage, displayImage, 0);
      imageModel.generateHistogram(displayImage, histogramImage);

      imageView.updateImage();
    }

  }

  private void sharpenGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(displayImage, prevImageName);

      imageModel.getSharpenedImage(displayImage, displayImage, 0);
      imageModel.generateHistogram(displayImage, histogramImage);

      imageView.updateImage();
    }

  }

  private void grayscaleLumaGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(displayImage, prevImageName);

      imageModel.getGrayscaleImage(displayImage, displayImage, 0);
      imageModel.generateHistogram(displayImage, histogramImage);

      imageView.updateImage();
    }

  }

  private void sepiaGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(displayImage, prevImageName);

      imageModel.sepiaToneImage(displayImage, displayImage, 0);
      imageModel.generateHistogram(displayImage, histogramImage);

      imageView.updateImage();
    }

  }

  private void compressGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(displayImage, prevImageName);

      int p = imageView.getCompressionPercent();

      imageModel.compressImage(displayImage, displayImage, p);
      imageModel.generateHistogram(displayImage, histogramImage);

      imageView.updateImage();
    }

  }

  private void colorCorrectGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(displayImage, prevImageName);

      int p = 0;
      imageModel.getColorCorrection(displayImage, displayImage, p);
      imageModel.generateHistogram(displayImage, histogramImage);
      imageView.updateImage();
    }

  }

  private void levelsAdjustGUI() {
    if (checkDisplayOp(displayImage)) {
      imageModel.copyImage(displayImage, prevImageName);

      int[] bmw = imageView.getBMW();
      if (bmw != null) {
        int p = 0;
        int b = bmw[0];
        int m = bmw[1];
        int w = bmw[2];
        imageModel.getLevelAdjustment(b, m, w, displayImage, displayImage, p);
        imageModel.generateHistogram(displayImage, histogramImage);
        imageView.updateImage();
      }
    }
  }

  private void toggleSplitGUISelect() {
    int p = splitPercentDialog.getSplitPercentage();
    if (p != 0) {
      imageModel.getSplitImage(prevImageName, displayImage, splitPreviewImage, p);
      imageView.showSplitPreview();
    }
  }

  private void toggleSplitGUIDeselect() {
    imageView.delSplitPreview();
  }

  private boolean checkDisplay(String imgName) {
    if (imageModel.getImage(imgName) == null) {
      imageView.displayIsEmpty();
      return false;
    }
    return true;
  }

  private boolean checkDisplayOp(String imgName) {
    if (imageModel.getImage(imgName) == null) {
      imageView.operationOnEmpty();
      return false;
    }
    return true;
  }

}


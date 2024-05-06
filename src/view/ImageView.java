package view;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import model.ModelInterface;
import model.utils.ImageUtils;

/**
 * ImageView is a class that creates a user interface for an image editing application.
 * It includes a variety of components like menus, buttons, and panels for displaying images
 * and histograms. The class also provides mechanisms to interact with these components, such as
 * adding action listeners and updating displayed images.
 */
public class ImageView implements ViewInterface {

  static JFrame frame = new JFrame("Photoshop Ripoff");

  private final String originalImageName = "originalImage";
  private final String displayImageName = "displayImage";
  private final String histogramImageName = "histogramImage";
  private final String splitPreviewImageName = "splitPreviewImage";
  private final String prevImageName = "prevImage";
  private final FileChooser fileChooser = new FileChooser();
  private final ImageUtils imageUtils = new ImageUtils();
  private final ModelInterface imageModel;
  private final ImagePanel imagePanel;
  private final HistogramPanel histogramPanel;
  private final JPanel mainPanel = new JPanel();
  private final BMWDialog bmwDialog;
  private final CompressionDialog compressionDialog;
  private JPanel buttonPanel;
  private JMenuBar menuBar;
  private JButton redButton;
  private JButton greenButton;
  private JButton blueButton;
  private JButton flipHorizontalButton;
  private JButton flipVerticalButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton grayscaleLumaButton;
  private JButton sepiaButton;
  private JButton compressButton;
  private JButton colorCorrectButton;
  private JButton levelsAdjustButton;
  private JToggleButton toggleSplit;
  private JMenuItem loadItem;
  private JMenuItem saveItem;
  private JMenuItem resetOrginal;
  private JMenuItem resetPrevious;
  private JDialog splitPreviewDialog = new JDialog(frame);

  /**
   * Constructor that initializes the ImageView with a model interface. It sets up image
   * and histogram panels, file choosers, and other UI components.
   *
   * @param model The model interface that provides image processing functionalities.
   */

  public ImageView(ModelInterface model) {
    this.imagePanel = new ImagePanel();
    this.histogramPanel = new HistogramPanel();
    this.mainPanel.setLayout(new BorderLayout());
    this.bmwDialog = new BMWDialog();
    this.compressionDialog = new CompressionDialog();
    this.imageModel = model;
  }

  private void addMenuBar() {
    menuBar = new JMenuBar();
    JMenu fileMenu;
    fileMenu = new JMenu("File");
    JMenu resetMenu;
    resetMenu = new JMenu("Reset");
    menuBar.add(fileMenu);
    menuBar.add(resetMenu);
    loadItem = new JMenuItem("Load Image");
    saveItem = new JMenuItem("Save Image");
    resetOrginal = new JMenuItem("Reset Original");
    resetPrevious = new JMenuItem("Reset Previous");
    fileMenu.add(loadItem);
    fileMenu.add(saveItem);
    resetMenu.add(resetOrginal);
    resetMenu.add(resetPrevious);
    menuBar.add(fileMenu);
    menuBar.add(resetMenu);
  }

  private void addButtons() {
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, 0, 1, 2));
    redButton = new JButton("Red Component");
    greenButton = new JButton("Green Component");
    blueButton = new JButton("Blue Component");
    flipHorizontalButton = new JButton("Horizontal Flip");
    flipVerticalButton = new JButton("Vertical Flip");
    blurButton = new JButton("Blur");
    sharpenButton = new JButton("Sharpen");
    grayscaleLumaButton = new JButton("Grayscale");
    sepiaButton = new JButton("Sepia");
    compressButton = new JButton("Compress");
    colorCorrectButton = new JButton("Color Correct");
    levelsAdjustButton = new JButton("Levels Adjust");
    toggleSplit = new JToggleButton("Split View");

    buttonPanel.add(redButton);
    buttonPanel.add(greenButton);
    buttonPanel.add(blueButton);
    buttonPanel.add(flipHorizontalButton);
    buttonPanel.add(flipVerticalButton);
    buttonPanel.add(blurButton);
    buttonPanel.add(sharpenButton);
    buttonPanel.add(grayscaleLumaButton);
    buttonPanel.add(sepiaButton);
    buttonPanel.add(compressButton);
    buttonPanel.add(colorCorrectButton);
    buttonPanel.add(levelsAdjustButton);
    buttonPanel.add(toggleSplit);
  }

  /**
   * Builds the user interface by adding menus, buttons, image and histogram panels
   * to the frame. This method should be called to set up the UI after the object is constructed.
   */
  public void buildUI() {
    this.addMenuBar();
    this.addButtons();
    frame.setLayout(new BorderLayout());
    frame.setJMenuBar(menuBar);
    mainPanel.add(imagePanel, BorderLayout.CENTER);
    histogramPanel.setPreferredSize(new Dimension(256, 0));
    mainPanel.add(histogramPanel, BorderLayout.EAST);
    frame.add(mainPanel, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.SOUTH);
  }

  /**
   * Updates the displayed image and histogram in the UI. It fetches the processed images
   * from the model and updates the image and histogram panels.
   */

  public void updateImage() {
    double[][][] dispImage = imageModel.getImage(displayImageName);
    BufferedImage buffDispImage = imageUtils.convertToBuffered(
            imageUtils.convertDoubleToInt(dispImage));

    double[][][] histImage = imageModel.getImage(histogramImageName);
    BufferedImage buffHistImage = imageUtils.convertToBuffered(
            imageUtils.convertDoubleToInt(histImage));
    imagePanel.setImage(buffDispImage);
    histogramPanel.setImage(buffHistImage);
  }

  /**
   * This method displays UI needed for user interaction.
   */
  public void display() {
    frame.pack();
    frame.revalidate();
    frame.repaint();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    frame.setVisible(true);
  }

  // Methods to add listeners to various UI components
  public void addResetOrginalListener(ActionListener listener) {
    resetOrginal.addActionListener(listener);
  }

  public void addResetPreviousListener(ActionListener listener) {
    resetPrevious.addActionListener(listener);
  }

  public void addRedButtonListener(ActionListener listener) {
    redButton.addActionListener(listener);
  }

  public void addGreenButtonListener(ActionListener listener) {
    greenButton.addActionListener(listener);

  }

  public void addBlueButtonListener(ActionListener listener) {
    blueButton.addActionListener(listener);
  }

  public void addFlipHorizontalButtonListener(ActionListener listener) {
    flipHorizontalButton.addActionListener(listener);

  }

  public void addFlipVerticalButtonListener(ActionListener listener) {
    flipVerticalButton.addActionListener(listener);

  }

  public void addBlurButtonListener(ActionListener listener) {
    blurButton.addActionListener(listener);

  }

  public void addSharpenButtonListener(ActionListener listener) {
    sharpenButton.addActionListener(listener);

  }

  public void addGrayscaleLumaButtonListener(ActionListener listener) {
    grayscaleLumaButton.addActionListener(listener);

  }

  public void addSepiaButtonListener(ActionListener listener) {
    sepiaButton.addActionListener(listener);

  }

  public void addCompressButtonListener(ActionListener listener) {
    compressButton.addActionListener(listener);

  }

  public void addColorCorrectButtonListener(ActionListener listener) {
    colorCorrectButton.addActionListener(listener);

  }

  public void addLevelsAdjustButtonListener(ActionListener listener) {
    levelsAdjustButton.addActionListener(listener);

  }

  public void addToggleSplitListener(ItemListener listener) {
    toggleSplit.addItemListener(listener);
  }

  public void addLoadItemListener(ActionListener listener) {
    loadItem.addActionListener(listener);
  }

  public void addSaveItemListener(ActionListener listener) {
    saveItem.addActionListener(listener);
  }

  // Getter methods for various UI components and paths
  public String getDisplayImageName() {
    return displayImageName;
  }

  public String getOriginalImageName() {
    return originalImageName;
  }

  public String getHistogramImageName() {
    return histogramImageName;
  }

  public String getPrevImageName() {
    return prevImageName;
  }

  public String getLoadFilePath() {
    return fileChooser.getLoadFile();
  }

  public String getSaveFilePath() {
    return fileChooser.getSaveFile();
  }

  public String getSplitPreviewImageName() {
    return splitPreviewImageName;
  }


  // Methods for displaying dialogs and previews
  private void setPreviewDialog(BufferedImage image) {
    JLabel imageLabel = new JLabel();
    JScrollPane scrollPane = new JScrollPane(imageLabel);
    imageLabel.setIcon(new ImageIcon(image));
    splitPreviewDialog = new JDialog(frame, "Preview", false);
    splitPreviewDialog.getContentPane().add(scrollPane);
    splitPreviewDialog.setSize(400, 400);
    splitPreviewDialog.setModalityType(JDialog.ModalityType.MODELESS);
    splitPreviewDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    splitPreviewDialog.setVisible(true);
  }

  public void showSplitPreview() {
    setPreviewDialog(imageUtils.convertToBuffered(
            imageUtils.convertDoubleToInt(imageModel.getImage(splitPreviewImageName))));
  }

  public void delSplitPreview() {
    splitPreviewDialog.dispose();
  }

  // Dialog data fetching methods
  public int[] getBMW() {
    return bmwDialog.getValues();
  }

  public int getCompressionPercent() {
    return compressionDialog.getCompressionPercentage();
  }

  public void showImageSaved(String path) {
    JOptionPane.showMessageDialog(frame, "Image saved to " + path, "Image Saved",
            JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Displays error message if saving operation is incorrect.
   */
  public void showSaveError() {
    JOptionPane.showMessageDialog(frame, "Unable to save image\nPlease make sure to enter" +
                    " the path correctly along with extension as .jpg, .png or .ppm",
            "Save Error",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Displays error message if saving operation is done before loading.
   */
  public void displayIsEmpty() {
    JOptionPane.showMessageDialog(frame, "Nothing to save. Please load an image before " +
                    "saving.", "Save Error",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Displays error message if any operation is done before loading.
   */

  public void operationOnEmpty() {
    JOptionPane.showMessageDialog(frame, "Please load an image before operation."
            , "Operation Error",
            JOptionPane.ERROR_MESSAGE);
  }

}
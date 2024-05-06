package view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * Represents the interface for the view of the Image Processing application. Contains all the
 * methods to be implemented by the view.
 */
public interface ViewInterface {

  void display();

  void buildUI();

  void addLoadItemListener(ActionListener listener);

  void addSaveItemListener(ActionListener listener);

  void addRedButtonListener(ActionListener listener);

  void addGreenButtonListener(ActionListener listener);

  void addBlueButtonListener(ActionListener listener);

  void addFlipHorizontalButtonListener(ActionListener listener);

  void addFlipVerticalButtonListener(ActionListener listener);

  void addBlurButtonListener(ActionListener listener);

  void addSharpenButtonListener(ActionListener listener);

  void addGrayscaleLumaButtonListener(ActionListener listener);

  void addSepiaButtonListener(ActionListener listener);

  void addCompressButtonListener(ActionListener listener);

  void addColorCorrectButtonListener(ActionListener listener);

  void addLevelsAdjustButtonListener(ActionListener listener);

  void addResetOrginalListener(ActionListener listener);

  void addToggleSplitListener(ItemListener listener);

  void showSplitPreview();

  void delSplitPreview();

  int[] getBMW();

  void updateImage();

  int getCompressionPercent();


}

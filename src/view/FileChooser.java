package view;

import java.io.File;


import javax.swing.JFileChooser;

import javax.swing.filechooser.FileSystemView;

/**
 * FileChooser class provides user interface components to select files for loading and saving.
 */
public class FileChooser {

  /**
   * Presents an open file dialog to the user to select a file for loading.
   *
   * @return The path of the selected file as a string, or null if no file is selected.
   */

  public String getLoadFile() {

    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    jfc.setDialogTitle("Load Image");

    int returnValue = jfc.showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = jfc.getSelectedFile();
      return selectedFile.toString();
    }
    return null;
  }

  /**
   * Presents a save file dialog to the user to specify the file name and location for saving.
   *
   * @return The path of the file to be saved as a string, or null if the operation is cancelled.
   */

  public String getSaveFile() {
    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    jfc.setDialogTitle("Save Image as");
    int returnValue = jfc.showSaveDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = jfc.getSelectedFile();
      return selectedFile.toString();
    }
    return null;
  }

}

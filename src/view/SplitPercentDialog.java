package view;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * SplitPercentDialog is a JFrame subclass that provides a user interface for entering a
 * split percentage.
 */
public class SplitPercentDialog extends JFrame {

  /**
   * Prompts the user to enter a split percentage through an input dialog. The entered value
   * is validated to be a valid integer within the range of 1 to 100.
   *
   * @return The entered split percentage as an integer if valid, or 0 if the input is invalid
   *          or if the user cancels the input dialog.
   */
  public int getSplitPercentage() {
    String userInput = JOptionPane.showInputDialog("Enter the split percentage (1-100):");
    try {
      if (userInput != null) {
        int number = Integer.parseInt(userInput);
        checkPercent(number);
        return number;
      }
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid percent.", "Error",
              JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException ex) {
      System.err.println(ex.getMessage());
    }
    return 0;
  }

  private void checkPercent(int p) {
    if (p < 1 || p > 100) {
      JOptionPane.showMessageDialog(this,
              "Invalid percentage. Please enter a number between 1 and 100.", "Error",
              JOptionPane.ERROR_MESSAGE);
      throw new IllegalArgumentException("Invalid percentage: " + p);
    }
  }
}

package view;




import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class provides methods which display panel to enter b,m,w values.
 */
public class BMWDialog extends JFrame {

  /**
   * Presents a dialog to the user to enter values for b, m, and w. It validates the input to ensure
   * that the entered values are integers within the range of 0 to 255 and that they adhere to the
   * constraints b < m < w. If the input is invalid, an error message is displayed and null is
   * returned.
   *
   * @return An array of integers containing the values for b, m, and w if valid input is provided,
   *         or null if the input is invalid or the dialog is cancelled.
   */
  public int[] getValues() {
    JTextField bField = new JTextField(5);
    JTextField mField = new JTextField(5);
    JTextField wField = new JTextField(5);

    JPanel myPanel = new JPanel();
    myPanel.setLayout(new GridLayout(3, 2));
    myPanel.add(new JLabel("Enter value for b:"));
    myPanel.add(bField);
    myPanel.add(new JLabel("Enter value for m:"));
    myPanel.add(mField);
    myPanel.add(new JLabel("Enter value for w:"));
    myPanel.add(wField);
    int result = JOptionPane.showConfirmDialog(null, myPanel,
            "Please Enter Values for b, m, and w", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      try {
        int b = Integer.parseInt(bField.getText());
        int m = Integer.parseInt(mField.getText());
        int w = Integer.parseInt(wField.getText());

        checkValues(b, m, w);

        return new int[]{b, m, w};
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input. " +
                "Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
      } catch (IllegalArgumentException e) {
        return null;
      }
    }
    return null;
  }


  private void checkValues(int b, int m, int w) {
    int flag = 0;
    if (b < 0 || b > 255) {
      JOptionPane.showMessageDialog(this,
              "Invalid value for b. Please enter a number between 0 and 255 for b.", "Error",
              JOptionPane.ERROR_MESSAGE);
      flag = 1;
    }
    if (m < 0 || m > 255) {
      JOptionPane.showMessageDialog(this,
              "Invalid value for m. Please enter a number between 0 and 255 for m.", "Error",
              JOptionPane.ERROR_MESSAGE);
      flag = 1;

    }
    if (w < 0 || w > 255) {
      JOptionPane.showMessageDialog(this,
              "Invalid value for w. Please enter a number between 0 and 255 for w.", "Error",
              JOptionPane.ERROR_MESSAGE);
      flag = 1;

    }

    if (b >= m) {
      JOptionPane.showMessageDialog(this,
              "b cannot be greater than or equal to m.", "Error",
              JOptionPane.ERROR_MESSAGE);
      flag = 1;
    }
    if (m >= w) {
      JOptionPane.showMessageDialog(this,
              "m cannot be greater than or equal to w.", "Error",
              JOptionPane.ERROR_MESSAGE);
      flag = 1;
    }

    if (b >= w) {
      JOptionPane.showMessageDialog(this,
              "b cannot be greater than or equal to w.", "Error",
              JOptionPane.ERROR_MESSAGE);
      flag = 1;
    }
    if (flag == 1) {
      throw new IllegalArgumentException("Illegal values for b, m, or w.");
    }
  }
}

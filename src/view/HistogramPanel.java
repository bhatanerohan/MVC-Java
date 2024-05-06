package view;



import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;



/**
 * HistogramPanel is a subclass of JPanel designed specifically for displaying a histogram image.
 */

public class HistogramPanel extends JPanel {
  private final JLabel imageLabel;


  /**
   * Constructor for HistogramPanel. Initializes the panel with a BorderLayout,
   * sets its size, and adds a JLabel to display an image.
   */

  public HistogramPanel() {
    this.setLayout(new BorderLayout());
    this.setMaximumSize(new Dimension(256, 256));
    this.setMinimumSize(new Dimension(256, 256));
    this.setBounds(0, 0, 256, 256);

    imageLabel = new JLabel();
    add(imageLabel, BorderLayout.CENTER);
  }

  /**
   * Sets the image to be displayed in the panel. If the provided image is not null,
   * it is set as the icon of the internal JLabel. If the image is null, any existing
   * image is removed from the label. The panel is then revalidated and repainted.
   *
   * @param image The BufferedImage to be displayed, or null to clear any existing image.
   */
  public void setImage(BufferedImage image) {

    if (image != null) {
      ImageIcon icon = new ImageIcon(image);
      imageLabel.setIcon(icon);
    } else {
      imageLabel.setIcon(null);
    }

    revalidate();
    repaint();
  }

  @Override
  public Dimension getMinimumSize() {
    return new Dimension(256, 256); // Adjust the minimum size as needed
  }

  @Override
  public Dimension getMaximumSize() {
    return new Dimension(256, 256); // Adjust the minimum size as needed
  }
}

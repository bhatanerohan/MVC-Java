package view;



import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;


/**
 * ImagePanel is a subclass of JPanel specifically designed for displaying images.
 * It contains a JLabel within a JScrollPane, allowing for both the display of an
 * image and scrolling if the image is larger than the panel's visible area.
 */

public class ImagePanel extends JPanel {
  private final JLabel imageLabel;

  /**
   * Constructor for ImagePanel. Initializes the panel with a BorderLayout,
   * creates a JLabel for displaying an image, and adds it to a JScrollPane.
   */

  public ImagePanel() {
    this.setLayout(new BorderLayout());
    imageLabel = new JLabel();
    JScrollPane scrollPane = new JScrollPane(imageLabel);
    add(scrollPane, BorderLayout.CENTER);
  }

  /**
   * Sets the image to be displayed in the panel. If the provided image is not null,
   * it is converted to an ImageIcon and set as the icon for the internal JLabel.
   * If the image is null, any existing image is removed from the label. The panel
   * is then revalidated and repainted to reflect the changes.
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
}

import controller.ControllerInterface;
import controller.ImageController;
import model.ImageModel;
import model.ModelInterface;
import model.utils.ImageStorage;
import view.ImageView;

/**
 * Main class to run the implementation.
 */
public class Main {

  /**
   * Main method to invoke controller.
   *
   * @param args args to main
   */
  public static void main(String[] args) {
    ImageStorage storage = new ImageStorage();
    ModelInterface model = new ImageModel(storage);
    ImageView view = new ImageView(model);
    ControllerInterface controller = new ImageController(model, view);
    try {
      if (args.length > 0 && args[0].equals("-file")) {
        if (args.length > 1) {
          String fileName = args[1];
          controller.run(fileName);
        } else {
          System.err.println("Please provide a file name after -file option.");
        }
      } else if (args.length > 0 && args[0].equals("-text")) {
        controller.run();
      } else {
        controller.runGUI();
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}


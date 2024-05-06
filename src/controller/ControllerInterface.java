package controller;

/**
 * Interface for controller.
 */
public interface ControllerInterface {

  /**
   * Method to give control to the controller from main.
   */
  void run();

  void run(String fileName);

  void runGUI();
}


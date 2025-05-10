/**
* Entry point for the JavaFX application.
* Launches the main application window.
* 
* @author Thomas Villareale
*/
package view;

import stage.MainController;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application  {

/**
* Main method. Launches the JavaFX application.
     * @param args command line arguments
*/
	public static void main(String[] args) {
		launch(args);
	}
/**
* Starts the JavaFX application. 
     * @param primaryStage the primary Stage of the JavaFX application
*/
	@Override
	public void start(Stage primaryStage) {
		MainController controller = new MainController();
		controller.show(primaryStage);
	}
}
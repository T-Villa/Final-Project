package view;

import stage.MainController;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application  {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		MainController controller = new MainController();
		controller.show(primaryStage);
	}
}

package view;
import stage.MainViewController;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application  {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		MainViewController controller = new MainViewController();
		controller.mainView(primaryStage);
	}
}

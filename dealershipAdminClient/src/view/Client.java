package view;
import stage.InventoryViewController;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application  {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		InventoryViewController controller = new InventoryViewController();
		controller.mainView(primaryStage);
	}
}

package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Client extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Luxury Car inventory");
		Scene scene = new Scene(new Label("GUI SOON"),600,400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

package stage;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Node;

import control.InventoryManager;
import stage.ShowroomViewController;
import stage.InventoryViewController;


public class MainController {
	private final BorderPane mainLayout = new BorderPane();
	private final InventoryManager inventoryManager = new InventoryManager();
	
	public void show(Stage stage) {
		HBox navigation = new HBox(10);
		navigation.setPadding(new Insets(10));
		navigation.setStyle("-fx-background-color: #e0e0e0;");//used Internet to help me with this
		
		Button showroomButton = new  Button("Showroom");
		Button inventoryButton = new Button("Inventory");
		
		showroomButton.setOnAction(event -> setShowroomView());
		inventoryButton.setOnAction(event -> setInventoryView());
		
		navigation.getChildren().addAll(showroomButton,inventoryButton);
		mainLayout.setTop(navigation);
		
		setShowroomView();
		
		Scene scene = new Scene(mainLayout,1000,600);
		stage.setTitle("Luxury Car Sales System");
		stage.setScene(scene);
		stage.show();
	}

	private void setShowroomView() {
		ShowroomViewController showroom = new ShowroomViewController(inventoryManager);
		Node showroomView = showroom.getView();
		mainLayout.setCenter(showroomView);
	}
	
	private void setInventoryView() {
		InventoryViewController inventory = new InventoryViewController(inventoryManager);
		Node inventoryView = inventory.getView();
		mainLayout.setCenter(inventoryView);
		System.out.println("Switched to inventory view");
	}
}

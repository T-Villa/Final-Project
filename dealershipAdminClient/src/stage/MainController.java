package stage;

import control.InventoryManager;
import control.CustomerManager;
import control.SalesManager;
import stage.ShowroomViewController;
import stage.InventoryViewController;
import stage.CustomerViewController;
import stage.SalesViewController;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Node;


public class MainController {
	private final BorderPane mainLayout = new BorderPane();
	private final InventoryManager inventoryManager = new InventoryManager();
	
	public void show(Stage stage) {
		HBox navigation = new HBox(10);
		navigation.setPadding(new Insets(10));
		navigation.setStyle("-fx-background-color: #e0e0e0;");//used Internet to help me with this
		
		Button showroomButton = new  Button("Showroom");
		Button inventoryButton = new Button("Inventory");
		Button customersButton = new Button("Customers");
	    Button salesButton = new Button("Sales");

		
		showroomButton.setOnAction(event -> setShowroomView());
		inventoryButton.setOnAction(event -> setInventoryView());
		customersButton.setOnAction(event -> setCutomersView("Customers"));
	    salesButton.setOnAction(event -> setSalesView("Sales"));

		
		navigation.getChildren().addAll(showroomButton,inventoryButton,customersButton,salesButton);
		mainLayout.setTop(navigation);
		
		setShowroomView();
		
		Scene scene = new Scene(mainLayout,1000,600);
		stage.setTitle("Luxury Car Sales System");
		stage.setScene(scene);
		stage.show();
	}

	private void setShowroomView() {
		ShowroomViewController showroom = new ShowroomViewController(inventoryManager);
		//Node showroomView = showroom.getView();
	    Label label = new Label("Showroom" + " View - Under Construction");
	    label.setStyle("-fx-font-size: 20; -fx-text-fill: gray;");
	    VBox layout = new VBox(label);
	    layout.setPadding(new Insets(20));
	    mainLayout.setCenter(layout);
		//mainLayout.setCenter(showroomView);
	}
	
	private void setInventoryView() {
		InventoryViewController inventory = new InventoryViewController(inventoryManager);
		Node inventoryView = inventory.getView();
		mainLayout.setCenter(inventoryView);
	}
	
	private void setCutomersView(String name) {
	    Label label = new Label(name + " View - Under Construction");
	    label.setStyle("-fx-font-size: 20; -fx-text-fill: gray;");
	    VBox layout = new VBox(label);
	    layout.setPadding(new Insets(20));
	    mainLayout.setCenter(layout);
	}
	private void setSalesView(String name) {
	    Label label = new Label(name + " View - Under Construction");
	    label.setStyle("-fx-font-size: 20; -fx-text-fill: gray;");
	    VBox layout = new VBox(label);
	    layout.setPadding(new Insets(20));
	    mainLayout.setCenter(layout);
	}
}

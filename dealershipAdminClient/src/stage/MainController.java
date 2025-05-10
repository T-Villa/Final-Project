package stage;

import control.CustomerManager;
import control.SalesManager;
import control.InventoryManager;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainController {
	private final BorderPane mainLayout = new BorderPane();
	private final InventoryManager inventoryManager = new InventoryManager();
	private final CustomerManager customerManager = new CustomerManager();
	private final SalesManager salesManager = new SalesManager();
	
	public void show(Stage stage) {
		HBox nav = new HBox(10);
		nav.setPadding(new Insets(10));
		nav.setStyle("-fx-background-color: #e0e0e0;");
		
		Button inventoryButton = new Button("Inventory");
		Button customersButton = new Button("Customers");
	    Button salesButton = new Button("Sales");

		
		inventoryButton.setOnAction(event -> setInventoryView());
		customersButton.setOnAction(event -> setCutomersView());
	    salesButton.setOnAction(event -> setSalesView());

		
		nav.getChildren().addAll(inventoryButton,customersButton,salesButton);
		mainLayout.setTop(nav);
		
		setInventoryView();
		

		
		Scene scene = new Scene(mainLayout,1000,600);
		stage.setTitle("Luxury Car Sales System");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}
	
	private void setInventoryView() {
		InventoryViewController inventory = new InventoryViewController(inventoryManager, customerManager, salesManager);
		Node inventoryView = inventory.getView();
		mainLayout.setCenter(inventoryView);
	}
	
	private void setCutomersView() {
	    CustomerViewController customer = new CustomerViewController(customerManager);
	    Node customerView = customer.getView();
	    mainLayout.setCenter(customerView);
	}
	private void setSalesView() {
	    SalesViewController sales = new SalesViewController(salesManager);
	    Node salesView = sales.getView();
	    mainLayout.setCenter(salesView);
	}
}

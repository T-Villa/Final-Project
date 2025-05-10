//display inventory with filter option
package stage;

import control.CustomerManager;
import control.InventoryManager;
import control.SalesManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Car;
import util.ButtonController;
import util.Filter;

public class InventoryViewController {
	private final InventoryManager inventoryManager;
	private final CustomerManager customerManager;
	private final SalesManager salesManager;
	
	 public Node getView() {
		TableView<Car> table = new TableView<>();
			ObservableList<Car> carData = FXCollections.observableArrayList(inventoryManager.getAllCars());
			table.setItems(carData);
		
		//making columns
		TableColumn<Car, String> SKUColumn = new TableColumn<>("SKU");
			SKUColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSKU()));
		TableColumn<Car, String> makeColumn = new TableColumn<>("Make");
			makeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMake()));
		TableColumn<Car, String> modelColumn = new TableColumn<>("Model");
			modelColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getModel()));
		TableColumn<Car, String> trimColumn = new TableColumn<>("Trim");
			trimColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTrimLvl()));
		TableColumn<Car,String> colorColumn = new TableColumn<>("Color");
			colorColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getColor()));
		TableColumn<Car, Integer> yearColumn = new TableColumn<>("Year");
			yearColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getYear()).asObject());
		TableColumn<Car, Double> priceColumn = new TableColumn<>("Price");
			priceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
		TableColumn<Car, Boolean> availabilityColumn = new TableColumn<>("Availability");
			availabilityColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleBooleanProperty(cellData.getValue().isAvailable()));
		
		table.getColumns().addAll(SKUColumn,makeColumn,modelColumn,trimColumn,colorColumn,yearColumn,priceColumn,availabilityColumn);
		
		Button addCarButton = ButtonController.addCarButton(inventoryManager, table);
	    Button removeCarButton = ButtonController.removeCarButton(inventoryManager, table);
	    Button sellButton = ButtonController.sellButton(inventoryManager, customerManager, salesManager, table);
	    
	    HBox buttons = new HBox(10, addCarButton, removeCarButton, sellButton);
	     buttons.setPadding(new Insets(10));
	     
	    HBox panel = new HBox(20, Filter.filterLayout(inventoryManager, carData),table);
	     panel.setPadding(new Insets(10));
	     Filter.filterLayout(inventoryManager, carData).setPrefWidth(250);
	     table.setPrefWidth(1000);
	     
	    VBox layout = new VBox(10, panel, buttons);
		 layout.setPadding(new Insets(10));
	     priceColumn.setPrefWidth(100);

	     return layout;
	}
	 
	public InventoryViewController(InventoryManager inventoryManager, CustomerManager customerManager, SalesManager salesManager) {
		this.inventoryManager = inventoryManager;
		this.customerManager = customerManager;
		this.salesManager = salesManager;
	}
}
//add accessories (optional choice for user to add, not required)

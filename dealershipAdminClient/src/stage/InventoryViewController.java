//display inventory with filter option
package stage;

import model.Car;
import model.CarData;
import control.InventoryManager;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InventoryViewController {
	private final InventoryManager inventoryManager;
	
	 public Node getView() {
		TableView<Car> table = new TableView<>();
			ObservableList<Car> carData = FXCollections.observableArrayList(inventoryManager.getAllCars());
			table.setItems(carData);
		
		//making columns
		TableColumn<Car, String> makeColumn = new TableColumn<>("Make");
			makeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMake()));
		TableColumn<Car, String> modelColumn = new TableColumn<>("Model");
			modelColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getModel()));
		TableColumn<Car, String> SKUColumn = new TableColumn<>("SKU");
			SKUColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSKU()));
		TableColumn<Car, Integer> yearColumn = new TableColumn<>("Year");
			yearColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getYear()).asObject());
		TableColumn<Car, Double> priceColumn = new TableColumn<>("Price");
			priceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
		TableColumn<Car, Boolean> availabilityColumn = new TableColumn<>("Availability");
		availabilityColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleBooleanProperty(cellData.getValue().isAvailable()));
		
		table.getColumns().addAll(SKUColumn,makeColumn,modelColumn,yearColumn,priceColumn,availabilityColumn);
		
	     Button addCarButton = new Button("Add Car");
	     Button removeCarButton = new Button("Remove Car");
	     Button markSoldButton = new Button("Mark as Sold");
	     
	     addCarButton.setOnAction(e->{
	    	Stage formStage = new Stage();
	    	formStage.setTitle("Add New Car");
	    	
	    	VBox formLayout = new VBox(10);
	    		formLayout.setPadding(new Insets(10));
	    	ComboBox<Integer> yearField = new ComboBox<>();
	    		yearField.setPromptText("Year");
	    		yearField.getItems().addAll(CarData.getAvailableYears());
	    	ComboBox<String> makeField = new ComboBox<>();
	    		makeField.setPromptText("Make");
	    		makeField.getItems().addAll(CarData.getMakes());
	    		
	    	ComboBox<String> modelField = new ComboBox<>();
	    		modelField.setPromptText("model");
	    			makeField.setOnAction(event ->{
	    				String choosenMake = makeField.getValue();
	    				List<String> models = CarData.getModel(choosenMake);
	    				modelField.getItems().setAll(models);
	    				modelField.setValue(null);
	    			});
	    	    	
	    	ComboBox<String> trimLvlField = new ComboBox<>();
			    	trimLvlField.setPromptText("Trim");	
			    		modelField.setOnAction(event -> {
			    			String choosenMake = makeField.getValue();
			    			String choosenModel = modelField.getValue();
			    			List<String> trimLvl = CarData.getTrim(choosenMake, choosenModel);
			    			trimLvlField.getItems().setAll(trimLvl);
			    			trimLvlField.setValue(null);
			    		});
			    	
	    	ComboBox<String> colorField = new ComboBox<>();
	    		colorField.setPromptText("color");
	    		colorField.getItems().addAll(CarData.getColor());
		    TextField priceField = new TextField();
				priceField.setPromptText("Price");	
			Button submitButton = new Button("Confirm");
				submitButton.setOnAction(event ->{
					try {
						Integer year = yearField.getValue();
						String make = makeField.getValue(); 
						String model = modelField.getValue(); 
						String color = colorField.getValue();
						String trimLvl = trimLvlField.getValue();
						double price = Double.parseDouble(priceField.getText());
						
							if(year == null||make==null||model==null||color==null) {
								throw new IllegalArgumentException("Please fill out year, make, model and color");
							}
						
						Car newCar = new Car(year,make,model,trimLvl,color,List.of(),price);
						inventoryManager.addCar(newCar);
						table.getItems().add(newCar);
						
						formStage.close();
					} catch (Exception ex) {
						Alert error = new Alert(Alert.AlertType.ERROR, "Invalid Input: "+ ex.getMessage());
						error.showAndWait();
					}
				});
				formLayout.getChildren().addAll(yearField,makeField,modelField,trimLvlField,colorField,priceField, submitButton);
				
				Scene formScene = new Scene(formLayout, 300, 300);
				formStage.setScene(formScene);
			//	formStage.initOwner(primaryStage);
				formStage.show();
	     });
	     removeCarButton.setOnAction(event->{
	    	Car choosen = table.getSelectionModel().getSelectedItem();
	    			if(choosen != null) {
	    				boolean removed = inventoryManager.removeCar(choosen.getSKU());
	    				if(removed) {
	    					table.getItems().remove(choosen);
	    				}
	    				else {
	    					new Alert(Alert.AlertType.ERROR,"Car not found").showAndWait();
	    				}
	    			}
	    			else {
	    				new Alert(Alert.AlertType.ERROR,"Select car to remove").showAndWait();
	    			}
	     });
	     markSoldButton.setOnAction(event->{
	    	Car selected = table.getSelectionModel().getSelectedItem();
	    		if(selected != null) {
	    			selected.setAvailability(false);
	    			table.refresh();
	    		}
	     });
	     HBox buttons = new HBox(10, addCarButton, removeCarButton, markSoldButton);
	     buttons.setPadding(new Insets(10));
		
		 VBox root = new VBox(10, table, buttons);
		 root.setPadding(new Insets(10));
	     priceColumn.setPrefWidth(100);

	     return root;
	}
	public InventoryViewController(InventoryManager inventoryManager) {
		this.inventoryManager = inventoryManager;
	}
}
//add accessories (optional choice for user to add, not required)
//add filter for inventory
//be able to sort columns
//display inventory with filter option
package stage;

import model.Car;
import model.CarData;
import model.Customer;
import control.CustomerManager;
import control.InventoryManager;
import control.SalesManager;
import util.Filter;

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
		
	     Button addCarButton = new Button("Add Car");
	     Button removeCarButton = new Button("Remove Car");
	     Button sellButton = new Button("Process Sale");
	     
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
						String trimLvl = trimLvlField.getValue();
						String color = colorField.getValue();
						double price = Double.parseDouble(priceField.getText());
						
						required(year,"Year");
						required(make,"Make");
						required(model,"Model");
						required(trimLvl,"Trim");
						required(color,"Color");
						
						Car newCar = new Car(year,make,model,trimLvl,color,List.of(),price);
						inventoryManager.addCar(newCar);
						table.getItems().add(newCar);
						
						formStage.close();
					} catch (Exception ex) {
						Alert error = new Alert(Alert.AlertType.ERROR, "Invalid Input: "+ ex.getMessage());
						error.showAndWait();
						return;
					}
				});
				formLayout.getChildren().addAll(yearField,makeField,modelField,trimLvlField,colorField,priceField, submitButton);
				
				Scene formScene = new Scene(formLayout, 300, 300);
				formStage.setScene(formScene);
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
	    					return;
	    				}
	    			}
	    			else {
	    				new Alert(Alert.AlertType.ERROR,"Select car to remove").showAndWait();
	    				return;
	    			}
	     });
	     sellButton.setOnAction(event->{
	    	Car selected = table.getSelectionModel().getSelectedItem();
	    		if(selected == null) {
	    			new Alert(Alert.AlertType.ERROR,"Please select a vehicle").showAndWait();
	    			return;
	    		}
	    		if(!selected.isAvailable()) {
	    			new Alert(Alert.AlertType.ERROR,"This car has been sold\nSorry").showAndWait();
	    			return;
	    		}
	    		
	    		Stage sellStage = new Stage();
	    			sellStage.setTitle("Process Sale");
	    		
	    		VBox sellLayout = new VBox(10);
	    			sellLayout.setPadding(new Insets(10));
	    		
	    		ComboBox<Customer> buyer = new ComboBox<>();
	    			buyer.setPromptText("Select Customer");
	    			buyer.getItems().addAll(customerManager.getAllCustomers());
	    		
	    		Button newBuyer = new Button("Add Customer");
	    			newBuyer.setOnAction(e -> {
	    				Stage formStage = new Stage();
	    				formStage.setTitle("New Customer");
	    				
	    				VBox formLayout = new VBox(10);
	    					formLayout.setPadding(new Insets(10));
	    				TextField nameField = new TextField();
	    					nameField.setPromptText("Enter Name");
	    				TextField emailField = new TextField();
	    					emailField.setPromptText("Enter Email");
	    				TextField phoneField = new TextField();
	    					phoneField.setPromptText("Enter Phone #");
	    					
	    				Button submitButton = new Button("Add");
	    				submitButton.setOnAction(submit -> {
	    					try {
	    						String name = nameField.getText();
	    						String email = emailField.getText();
	    						String phone = phoneField.getText();
	    						
	    						required(name,"Name");
	    						required(email,"Email");
	    						required(phone,"Phone number");
	    						
	    						Customer newCustomer = new Customer(name,email,phone);
	    						customerManager.addCustomer(newCustomer);
	    						buyer.getItems().add(newCustomer);
	    						buyer.setValue(newCustomer);
	    						
	    						formStage.close();
	    					} catch (Exception ex) {
	    						new Alert(Alert.AlertType.ERROR, "Invalid Input: "+ ex.getMessage()).showAndWait();
	    					}
	    				});
	    				formLayout.getChildren().addAll(nameField,emailField,phoneField,submitButton);
	    				Scene formScene = new Scene(formLayout, 300, 250);
	    				formStage.setScene(formScene);
	    				formStage.show();
	    			});
	    			
	    			TextField priceField = new TextField();
		    		priceField.setPromptText("Sale Pice");
		    		
		    		Button confirm = new Button("Confirm");
		    			confirm.setOnAction(e ->{
		    				try {
		    					Customer customer = buyer.getValue();
		    					double salePrice = Double.parseDouble(priceField.getText());
		    					required(buyer,"Customer");
		    					required(salePrice,"Price");
		    					
		    					salesManager.processSale(selected, customer, salePrice);
		    					table.refresh();
		    					sellStage.close();
		    					
		    				} catch (Exception ex) {
	    						new Alert(Alert.AlertType.ERROR, "Invalid Input: "+ ex.getMessage()).showAndWait();
		    				}
		    			});
		    			sellLayout.getChildren().addAll(new Label("Customer: "), buyer, newBuyer, new Label("Sale Price: "),priceField,confirm);
		    			sellStage.setScene(new Scene(sellLayout,350,250));
		    			sellStage.show();
	     });
	     
	     //Filter
/*	     VBox filters = new VBox(10);
	     	filters.setPadding(new Insets(10));
	     	
	     ComboBox<String> makeFilter = new ComboBox<>();
	     	makeFilter.setPromptText("Make");
	     	makeFilter.getItems().addAll(CarData.getMakes());
		 
	     ComboBox<Integer> yearFilter = new ComboBox<>();
	     	yearFilter.setPromptText("Year");
	     	yearFilter.getItems().addAll(CarData.getAvailableYears());
	
		 TextField minPriceFilter = new TextField();
		    minPriceFilter.setPromptText("Minimum Price");
		 TextField maxPriceFilter = new TextField();
		 	maxPriceFilter.setPromptText("Maximum Price");
		 	
		 Button apply = new Button("Apply Filter");
		 	apply.setOnAction(event->{
		 		String make = makeFilter.getValue();
		 		//String model = modelFilter.getValue();
		 		Integer year = yearFilter.getValue();
		 		
		 		
		 	List<Car> filtered = Filter.filterCars(inventoryManager.getAllCars(),make,year,minprice,maxPrice);
		 	carData.setAll(filtered);
		 	});
		 Button reset = new Button("Clear Fliters");
		 	reset.setOnAction(event->{
		 		carData.setAll(inventoryManager.getAllCars());
		 		makeFilter.setValue(null);
		 		yearFilter.setValue(null);
		 		//modelFilter.setValue(null);
		 		minPriceFilter.clear();
		 		maxPriceFilter.clear();
		 	});
		 	filters.getChildren().addAll(new Label("Filter Cars"),makeFilter,yearFilter,apply,reset);*/
	     
	     HBox buttons = new HBox(10, addCarButton, removeCarButton, sellButton);
	     buttons.setPadding(new Insets(10));
		
		 VBox layout = new VBox(10, table, buttons);
		 layout.setPadding(new Insets(10));
	     priceColumn.setPrefWidth(100);

	     return layout;
	}
	 private void required(Object val, String field) {
		 if(val == null) {
			 throw new IllegalArgumentException(field+" must be filled out");
		 }
	 }
	public InventoryViewController(InventoryManager inventoryManager, CustomerManager customerManager, SalesManager salesManager) {
		this.inventoryManager = inventoryManager;
		this.customerManager = customerManager;
		this.salesManager = salesManager;
	}
}
//add accessories (optional choice for user to add, not required)
//add filter for inventory
//be able to sort columns
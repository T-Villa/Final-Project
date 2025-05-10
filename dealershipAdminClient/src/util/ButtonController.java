package util;

import control.CustomerManager;
import control.InventoryManager;
import control.SalesManager;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Car;
import model.Customer;
import model.Sale;
import javafx.collections.ObservableList;


public class ButtonController {
	
	//Add car button
	public static Button addCarButton(InventoryManager inventoryManager,TableView<Car> table) {
		Button addCar = new Button("Add Car");
		addCar.setOnAction(e->{
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
		   	ComboBox<String> trimLvlField = new ComboBox<>();
		    	trimLvlField.setPromptText("Trim");	
		    ComboBox<String> colorField = new ComboBox<>();
	    		colorField.setPromptText("color");
	    		colorField.getItems().addAll(CarData.getColor());
		    TextField priceField = new TextField();
				priceField.setPromptText("Price");

	    		
	    		makeField.setOnAction(event ->{
	    			String choosenMake = makeField.getValue();
	    				if (choosenMake != null) {
	    					List<String> models = CarData.getModel(choosenMake);
	    					modelField.getItems().setAll(models);
		    				modelField.setValue(null);
							trimLvlField.getItems().clear();
		    		        trimLvlField.setValue(null);

	    			    }
	    			});
	    	    	
	    		modelField.setOnAction(event -> {
			    	String choosenMake = makeField.getValue();
			   		String choosenModel = modelField.getValue();
			   		    if (choosenMake != null && choosenModel != null) {
			    			List<String> trimLvl = CarData.getTrim(choosenMake, choosenModel);
			    			trimLvlField.getItems().setAll(trimLvl);
			    			trimLvlField.setValue(null);
		    		    }
		    		});
			    	
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
			return addCar;
	}
	
	//Remove car button
	public static Button removeCarButton(InventoryManager inventoryManager,TableView<Car> table) {
	    Button removeCar = new Button("Remove Car");

		removeCar.setOnAction(event->{
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
		return removeCar;
	}
	
	//Sell car button
	public static Button sellButton(InventoryManager inventoryManager,CustomerManager customerManager, SalesManager salesManager,TableView<Car> table) {
	    Button sell = new Button("Process Sale");

	     sell.setOnAction(event->{
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
	    				Stage addCustomerStage = new Stage();
	    					addCustomerStage.setTitle("New Customer");
	    				
	    				VBox layout = new VBox(10);
	    					layout.setPadding(new Insets(10));
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
	    						
	    						addCustomerStage.close();
	    					} catch (Exception ex) {
	    						new Alert(Alert.AlertType.ERROR, "Invalid Input: "+ ex.getMessage()).showAndWait();
	    					}
	    				});
	    				layout.getChildren().addAll(nameField,emailField,phoneField,submitButton);
	    				Scene formScene = new Scene(layout, 300, 250);
	    				addCustomerStage.setScene(formScene);
	    				addCustomerStage.show();
	    			});
	    			
	    			TextField priceField = new TextField();
		    			priceField.setPromptText("Sale Pice");
		    		
		    		TextField sellerField = new TextField();
		    			sellerField.setPromptText("Seller");
		    		
		    		Button confirm = new Button("Confirm");
		    			confirm.setOnAction(e ->{
		    				try {
		    					Customer customer = buyer.getValue();
		    					//Sale seller = sellerField.getText();
		    					double salePrice = Double.parseDouble(priceField.getText());
		    					String seller = sellerField.getText();
		    					required(buyer,"Customer");
		    					required(salePrice,"Price");
		    					required(sellerField,"Employee");
		    					
		    					inventoryManager.removeCar(selected.getSKU());
		    					table.getItems().remove(selected);
		    					
		    					salesManager.processSale(selected, customer,seller,salePrice);
		    						table.refresh();
		    						sellStage.close();
		    					
		    				} catch (Exception ex) {
	    						new Alert(Alert.AlertType.ERROR, "Invalid Input: "+ ex.getMessage()).showAndWait();
		    				}
		    			});
		    			
		    			sellLayout.getChildren().addAll(new Label("Customer: "), buyer, newBuyer, 
		    											new Label("Sale Price: "),priceField,
		    											new Label("Employee: "),sellerField,confirm);
		    			sellStage.setScene(new Scene(sellLayout,350,280));
		    			sellStage.show();
	     });
	     return sell;
	}
	
	//Add customer button
	public static Button addCustomerButton(ObservableList<Customer> customerData,CustomerManager customerManager,TableView<Customer> table) {
		Button addCustomer = new Button("Add Customer");

		addCustomer.setOnAction(event -> { 
			Stage formStage = new Stage();
			formStage.setTitle("Add New Customer");
			
			VBox formLayout = new VBox(10);
				formLayout.setPadding(new Insets(10));
			TextField nameField = new TextField();
				nameField.setPromptText("Enter Name");
			TextField emailField = new TextField();
				emailField.setPromptText("Enter Email");
			TextField phoneField = new TextField();
				phoneField.setPromptText("Enter Phone #");
				
			Button submitButton = new Button("Confirm");
			submitButton.setOnAction(e -> {
				try {
					String name = nameField.getText();
					String email = emailField.getText();
					String phone = phoneField.getText();
					
					required(name,"Name");
					required(email,"Email");
					required(phone,"Phone number");
					
					Customer newCustomer = new Customer(name,email,phone);
					customerManager.addCustomer(newCustomer);
					table.getItems().add(newCustomer);
					
					formStage.close();
				} catch (Exception ex) {
					Alert error = new Alert(Alert.AlertType.ERROR, "Invalid Input: "+ ex.getMessage());
					error.showAndWait();
				}
			});
			formLayout.getChildren().addAll(nameField,emailField,phoneField,submitButton);
			Scene formScene = new Scene(formLayout, 300, 250);
			formStage.setScene(formScene);
			formStage.show();
				
		});
		return addCustomer;
	}
	
	//Remove Customer Button
	public static Button removeCustomerButton(CustomerManager customerManager,TableView<Customer> table) {
		Button removeCustomer = new Button("Remove Customer");
		removeCustomer.setOnAction(e->{
			Customer choosen = table.getSelectionModel().getSelectedItem();
			if(choosen != null) {
				boolean removed = customerManager.removeCustomer(choosen.getID());
				if(removed) {
					table.getItems().remove(choosen);
				}
				else {
					new Alert(Alert.AlertType.ERROR,"Customer not found").showAndWait();
				}
			}
			else {
				new Alert(Alert.AlertType.ERROR,"Select customer to remove").showAndWait();
			}
	
		});
		return removeCustomer;
	}

	
	//Generate reports button
	public static Button genReportButton(ObservableList<Sale> salesData) {
		Button genReport = new Button("Create Sales Report");

		genReport.setOnAction(event ->{
			Stage reportStage = new Stage();
				reportStage.setTitle("Sales Report");
				
			VBox layout = new VBox(10);
				layout.setPadding(new Insets(10));
				
				Label totalSales = new Label("Total sales = "+SalesManager.countSales(salesData));
				Label totalRev = new Label ("Total revenue = $"+SalesManager.calculateTotalRev(salesData));
				
			layout.getChildren().addAll(totalSales, totalRev);	
			Scene reportScene = new Scene(layout,400,100);
				reportStage.setScene(reportScene);
				reportStage.show();	
		});
		return genReport;
	}

	 private static void required(Object val, String field) {
		 if(val == null) {
			 throw new IllegalArgumentException(field+" must be filled out");
		 }
	 }
}

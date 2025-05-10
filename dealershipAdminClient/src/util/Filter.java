/**
* Utility class providing filtering functionality for inventory and sales.
* Contains static methods to filter lists and create filter GUI components.
* 
* @author Thomas Villareale 
*/
package util;

import model.Car;
import model.Customer;
import model.Sale;

import java.util.*;
import java.util.stream.Collectors;

import control.CustomerManager;
import control.InventoryManager;
import control.SalesManager;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;


public class Filter {

/**
* Filters a list of cars based on optional search criteria.
     * @param cars      list of Car objects
     * @param make      optional make
     * @param model     optional model
     * @param minYear   optional minimum year
     * @param maxYear   optional maximum year
     * @param minPrice  optional minimum price
     * @param maxPrice  optional maximum price
     * @return filtered list of Car objects
*/
	public static List<Car> filterCars(List<Car> cars, String make, String model, Integer minYear,Integer maxYear,Double minPrice, Double maxPrice){
		return cars.stream()
				.filter(car->make==null||car.getMake().equalsIgnoreCase(make))
				.filter(car->model==null||car.getModel().equalsIgnoreCase(model))
				.filter(car->minYear==null||car.getYear() >= minYear)
				.filter(car->maxYear==null||car.getYear() <= maxYear)
				
				.filter(car->minPrice == null||car.getPrice() >= minPrice)
				.filter(car->maxPrice == null||car.getPrice() <= maxPrice)
				.collect(Collectors.toList());
	}
    
/**
* Creates a VBox with car filter controls for the inventory view.
     * @param inventoryManager the InventoryManager
     * @param carData          the ObservableList of cars to update
     * @return VBox containing filter controls
*/
	public static VBox filterLayout(InventoryManager inventoryManager, ObservableList<Car> carData) {
	     VBox filters = new VBox(10);
	     	filters.setPadding(new Insets(10));
	     	
	     ComboBox<String> makeFilter = new ComboBox<>();
	     	makeFilter.setPromptText("Make");
	     	makeFilter.getItems().addAll(CarData.getMakes());
	     	
	     ComboBox<String> modelFilter = new ComboBox<>();
	     	modelFilter.setPromptText("Model");
	     	makeFilter.setOnAction(event ->{
				String choosenMake = makeFilter.getValue();
				if (choosenMake == null) {
			        modelFilter.getItems().clear();
			        return;
			    }
				List<String> models = CarData.getModel(choosenMake);
				modelFilter.getItems().setAll(models);
				modelFilter.setValue(null);
			});

		 
	     ComboBox<Integer> minYearFilter = new ComboBox<>();
	     minYearFilter.setPromptText("Min Year");
	     minYearFilter.getItems().addAll(CarData.getAvailableYears());
	     
	     ComboBox<Integer> maxYearFilter = new ComboBox<>();
	     maxYearFilter.setPromptText("Max Year");
	     maxYearFilter.getItems().addAll(CarData.getAvailableYears());
	
		 TextField minPriceFilter = new TextField();
		    minPriceFilter.setPromptText("Minimum Price");
		 TextField maxPriceFilter = new TextField();
		 	maxPriceFilter.setPromptText("Maximum Price");
		 	
		 Button apply = new Button("Apply Filter");
		 	apply.setOnAction(event->{
		 		String make = makeFilter.getValue();
		 		String model = modelFilter.getValue();
		 		Integer minYear = minYearFilter.getValue();
		 		Integer maxYear = maxYearFilter.getValue();
		 		
		 		Double minPrice = null;
		 		Double maxPrice = null;	
		 			try {
		 				if(!minPriceFilter.getText().isEmpty()) {
		 			        minPrice = Double.parseDouble(minPriceFilter.getText());
		 			    }
		 			    if (!maxPriceFilter.getText().isEmpty()) {
		 			        maxPrice = Double.parseDouble(maxPriceFilter.getText());
		 			    }
		 			} catch (NumberFormatException e) {
		 			    new Alert(Alert.AlertType.ERROR, "Please enter valid price values").showAndWait();
		 			    return;
		 			} 		 					 		
		 		
		 	List<Car> filtered = Filter.filterCars(inventoryManager.getAllCars(),make,model,minYear,maxYear,minPrice,maxPrice);
		 	carData.setAll(filtered);
		 	});
		 Button reset = new Button("Clear Filters");
		 	reset.setOnAction(event->{
		 		carData.setAll(inventoryManager.getAllCars());
		 		
		 		makeFilter.setValue(null);		 		
		 		minYearFilter.setValue(null);
		 		maxYearFilter.setValue(null);
		 		modelFilter.setValue(null);
		 		minPriceFilter.clear();
		 		maxPriceFilter.clear();
		 	});
		 	filters.getChildren().addAll(new Label("Filter Cars"),makeFilter,modelFilter,minYearFilter,maxYearFilter,minPriceFilter,maxPriceFilter,apply,reset);
			return filters;
	}
	
/**
* Creates a VBox to filter sales by seller name. 
     * @param salesManager the SalesManager
     * @param salesData    the ObservableList of sales to update
     * @return VBox containing seller filter controls
*/
	public static VBox sellerFilter(SalesManager salesManager,ObservableList<Sale> salesData) {
	     VBox filters = new VBox(10);
	     	filters.setPadding(new Insets(10));
	     	
	     TextField sellerFilterField = new TextField();
	        sellerFilterField.setPromptText("Enter Seller Name");

	     Button filterButton = new Button("Filter by Seller");
	        filterButton.setOnAction(e -> {
	            
	     String seller = sellerFilterField.getText();
	            if (seller != null && !seller.isEmpty()) {
	                salesData.setAll(salesManager.getSalesBySeller(seller));
	            } else {
	                new Alert(Alert.AlertType.WARNING, "Please enter a seller name").showAndWait();
	            }
	        });

	        Button resetButton = new Button("Reset");
	        resetButton.setOnAction(e -> {
	            salesData.setAll(salesManager.genReport());
	            sellerFilterField.clear();
	        });
	        filters.getChildren().addAll(new Label("Filter Sales by Seller"), sellerFilterField, filterButton, resetButton);
	        return filters;
	}
	

/**
* Creates a VBox to filter customers by customer name.
     * @param customerManager the CustomerManager
     * @param customerData the ObservableList of customers to update
     * @return VBox containing customer filter controls
*/
	public static VBox customerFilter(CustomerManager customerManager,ObservableList<Customer> customerData) {
	     VBox filters = new VBox(10);
	     	filters.setPadding(new Insets(10));
	     	
	     TextField nameFilterField = new TextField();
	        nameFilterField.setPromptText("Enter Customer Name");

	     Button filterButton = new Button("Filter by Name");
	        filterButton.setOnAction(e -> {
	            
	        String name = nameFilterField.getText();
	            if (name != null && !name.isEmpty()) {
	                List<Customer> filtered = customerManager.getAllCustomers().stream().filter(c -> c.getName().toLowerCase().contains(name.toLowerCase())).toList();
	                customerData.setAll(filtered);
	            } else {
	                new Alert(Alert.AlertType.WARNING, "Please enter a customer name").showAndWait();
	            }
	        });

	        Button resetButton = new Button("Reset");
	        resetButton.setOnAction(e -> {
	            customerData.setAll(customerManager.getAllCustomers());
	            nameFilterField.clear();
	        });
	        filters.getChildren().addAll(new Label("Filter Customers by Name"), nameFilterField, filterButton, resetButton);
	        return filters;
	}
	
/**
* Creates a VBox to filter sales records by customer name.
     * @param salesManager the SalesManager
     * @param salesData    the ObservableList of sales to update
     * @return VBox containing customer filter controls for sales
*/
	public static VBox customerSalesFilter(SalesManager salesManager,ObservableList<Sale> salesData) {
	     VBox filters = new VBox(10);
	     	filters.setPadding(new Insets(10));
	     	
	     TextField nameFilterField = new TextField();
	        nameFilterField.setPromptText("Enter Customer Name");

	     Button filterButton = new Button("Filter by Name");
	        filterButton.setOnAction(e -> {
	            
	        String name = nameFilterField.getText();
	            if (name != null && !name.isEmpty()) {
	                List<Sale> filtered = salesManager.genReport().stream().filter(sale -> sale.getCustomer().getName().toLowerCase().contains(name.toLowerCase())).toList();
	                salesData.setAll(filtered);
	            } else {
	                new Alert(Alert.AlertType.WARNING, "Please enter a customer name").showAndWait();
	            }
	        });

	        Button resetButton = new Button("Reset");
	        resetButton.setOnAction(e -> {
	        	salesData.setAll(salesManager.genReport());
	            nameFilterField.clear();
	        });
	        filters.getChildren().addAll(new Label("Filter Sales by Customer"), nameFilterField, filterButton, resetButton);
	        return filters;
	}

}
// methods for filtering cards by criteria
package util;

import model.Car;
import model.CarData;

import java.util.*;
import java.util.stream.Collectors;

import control.InventoryManager;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;


public class Filter {
	public static List<Car> filterCars(List<Car> cars, String make, String model, Integer minYear,Integer maxYear,Double minPrice, Double maxPrice){
		return cars.stream()
				.filter(car->make==null||car.getMake().equalsIgnoreCase(make))
				.filter(car->model==null||car.getModel().equalsIgnoreCase(model))
				//.filter(car->trimLvl==null||car.getTrimLvl().equalsIgnoreCase(trimLvl))
				//.filter(car->color==null||car.getColor().equalsIgnoreCase(color))
				.filter(car->minYear==null||car.getYear() >= minYear)
				.filter(car->maxYear==null||car.getYear() <= maxYear)
				
				.filter(car->minPrice == null||car.getPrice() >= minPrice)
				.filter(car->maxPrice == null||car.getPrice() <= maxPrice)
				.collect(Collectors.toList());
	}
	
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
}
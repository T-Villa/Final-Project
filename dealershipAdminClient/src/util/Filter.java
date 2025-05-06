// methods for filtering cards by criteria
package util;

import model.Car;

import java.util.*;
import java.util.stream.Collectors;

public class Filter {
	public static List<Car> filterCars(List<Car> cars, String make, String model, String trimLvl, String color, Integer year,Double minPrice, Double maxPrice){
		return cars.stream()
				.filter(car->make==null||car.getMake().equalsIgnoreCase(make))
				.filter(car->model==null||car.getModel().equalsIgnoreCase(model))
				.filter(car->trimLvl==null||car.getTrimLvl().equalsIgnoreCase(trimLvl))
				.filter(car->color==null||car.getColor().equalsIgnoreCase(color))
				.filter(car->year==null||car.getYear() == year)
				
				.filter(car->minPrice == null||car.getPrice() >= minPrice)
				.filter(car->maxPrice == null||car.getPrice() <= maxPrice)
				.collect(Collectors.toList());
	}
}
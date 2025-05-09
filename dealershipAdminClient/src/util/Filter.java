// methods for filtering cards by criteria
package util;

import model.Car;

import java.util.*;
import java.util.stream.Collectors;

public class Filter {
	public static List<Car> filterCars(List<Car> cars, String make, /*String model,*/ Integer minYear,Integer maxYear,Double minPrice, Double maxPrice){
		return cars.stream()
				.filter(car->make==null||car.getMake().equalsIgnoreCase(make))
				//.filter(car->model==null||car.getModel().equalsIgnoreCase(model))
				//.filter(car->trimLvl==null||car.getTrimLvl().equalsIgnoreCase(trimLvl))
				//.filter(car->color==null||car.getColor().equalsIgnoreCase(color))
				.filter(car->minYear==null||car.getYear() >= minYear)
				.filter(car->maxYear==null||car.getYear() <= maxYear)
				
				.filter(car->minPrice == null||car.getPrice() >= minPrice)
				.filter(car->maxPrice == null||car.getPrice() <= maxPrice)
				.collect(Collectors.toList());
	}
}
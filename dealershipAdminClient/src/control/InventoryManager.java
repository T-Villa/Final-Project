/**Manages car listings (add/edit/delete/filter)
 * methods:
 * addCar(), removeCar(), filterByBrand(), filterByPrice(), etc
 **/
package control;

import model.Car;

import java.util.*;

public class InventoryManager {
	private final Map<String, Car> inventory = new HashMap<>(); //would map or list be better?
	
	public void addCar(Car car) {
		String SKU = car.getSKU();
		
		if(inventory.containsKey(SKU)) {
			throw new IllegalArgumentException("This SKU already exsists");
		}
		inventory.put(SKU, car);
	}
	public boolean carUnavailable(String SKU) { 
		Car car = inventory.get(SKU);
		
		if (car != null) {
			car.setAvailability(false);
			return true;
		}
		return false;
	}
	public boolean removeCar(String SKU) {
		return inventory.remove(SKU) != null;
	}
	
	public List<Car> getAllCars(){
		return new ArrayList<>(inventory.values());
	}
	public Car getCarBySKU(String SKU) {
	    return inventory.values().stream()
	               .filter(car -> car.getSKU().equals(SKU))
	               .findFirst()
	               .orElse(null);
	}
}	
	
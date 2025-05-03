/**Manages car listings (add/edit/delete/filter)
 * methods:
 * addCar(), removeCar(), filterByBrand(), filterByPrice(), etc
 **/
package control;

import java.util.*;
import model.Car;

public class InventoryManager {
	private final Map<String, Car> inventory = new HashMap<>(); //would map or list be better?
	
	public void addCar(Car car) {
		String SKU = car.getSKU();
		
		if(inventory.containsKey(SKU)) {
			throw new IllegalArgumentException("This SKU already exsists");
		}
		inventory.put(SKU, car);
	}
	public boolean carUnavailable(String SKU) { //setting car to just unavailable instead of fully removing may be better to keep track of history 
		Car car = inventory.get(SKU);
		
		if (car != null) {
			car.setAvailability(false);
			return true;
		}
		return false;
	}
	
	public List<Car> getAllCars(){
		return new ArrayList<>(inventory.values());
	}
	}
	//public List<Car> filterMake(String Make);
	//public List<Car> filterAvailable(boolean availability);
	
	
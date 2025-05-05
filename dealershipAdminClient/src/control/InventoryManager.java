/**Manages car listings (add/edit/delete/filter)
 * methods:
 * addCar(), removeCar(), filterByBrand(), filterByPrice(), etc
 **/
package control;

import model.Car;

import java.util.*;

public class InventoryManager {
	private final Map<String, Car> inventory = new HashMap<>(); //would map or list be better?
	
	public InventoryManager(){
    // Sample cars
    Car car1 = new Car(2021, "Toyota", "Camry", "Black", "SE", List.of("Bluetooth", "Backup Camera"), 27000);
    Car car2 = new Car(2020, "BMW", "X5", "White", "M Sport", List.of("Sunroof", "Leather Seats"), 55000);
    Car car3 = new Car(2022, "Audi", "A4", "Blue", "Premium", List.of("Navigation", "Heated Seats"), 42000);

    addCar(car1);
    addCar(car2);
    addCar(car3);
	}
	
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
	public boolean removeCar(String SKU) {
		return inventory.remove(SKU) != null;
	}
	
	public List<Car> getAllCars(){
		return new ArrayList<>(inventory.values());
	}
	}
	//public List<Car> filterMake(String Make);
	//public List<Car> filterAvailable(boolean availability);
	
	
/**
* Manages car inventory including adding, removing, and filtering cars.
* Implements the InterfaceCarRepo interface.
* Provides access to car data.
* 
* @author Thomas Villareale
*/
package control;

import model.Car;
import repository.InterfaceCarRepo;

import java.util.*;

public class InventoryManager implements InterfaceCarRepo {
	private final Map<String, Car> inventory = new HashMap<>();
	
    @Override
	public void addCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car must not be null");
        }
        String SKU = car.getSKU();
        if (inventory.containsKey(SKU)) {
            throw new IllegalArgumentException("This SKU already exists");
        }
        inventory.put(SKU, car);
    }
    	
    @Override
    public boolean removeCar(String SKU) {
        return inventory.remove(SKU) != null;
    }
    
    @Override
    public boolean carUnavailable(String SKU) {
        Car car = inventory.get(SKU);
        if (car != null) {
            car.setAvailability(false);
            return true;
        }
        return false;
    }
	
    @Override
    public List<Car> getAllCars() {
        return new ArrayList<>(inventory.values());
    }
    
    @Override
    public Car getCarBySKU(String SKU) {
        return inventory.get(SKU);
    }
}	
	
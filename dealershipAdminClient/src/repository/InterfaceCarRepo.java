/**
* Interface for car data operations.
* Defines methods to manage car inventory.
* Implemented by InventoryManager.
* 
* Thomas Villareale 
*/
package repository;

import model.Car;
import java.util.List;

public interface InterfaceCarRepo {
    void addCar(Car car);
    boolean removeCar(String SKU);
    List<Car> getAllCars();
    Car getCarBySKU(String SKU);
    boolean carUnavailable(String SKU);

}
//For car data operations.
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
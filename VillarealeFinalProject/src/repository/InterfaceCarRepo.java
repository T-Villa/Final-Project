package repository;
//Abstraction for car data operations.
public interface InterfaceCarRepo {
	void addCar(Car car);
	void removeCar(Car car);
	
	List<Car> filterCars(Map<String, Object> filters);
	Optional<Car> findById(String id);
	List<Car> getAllCars();
}
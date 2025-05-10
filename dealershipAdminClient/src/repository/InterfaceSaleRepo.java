///Handles sale transaction records.
package repository;

import model.Sale;
import model.Car;
import model.Customer;
import java.util.List;

public interface InterfaceSaleRepo {
	void processSale(Car car, Customer customer, String seller, double price);
    List<Sale> genReport();
    List<Sale> getSalesBySeller(String seller);
}
/**
* Interface for sale transaction records.
* Defines methods for processing and retrieving sales data.
* Implemented by SalesManager.
* 
* @author Thomas Villareale 
*/
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
package repository;
import model.Sales;

import java.util.*;
///Handles sale transaction records.
public interface InterfaceSaleRepo {
	void recordSale(Sales sale);
	
	List<Sales> getSaleByCustomerId(String customerId);
	List<Sales> getAllSales();
}
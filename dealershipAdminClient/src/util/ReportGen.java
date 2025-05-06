// Generate sales reports / summary
package util;

import model.Sale;
import java.util.*;

public class ReportGen {
	public static double calculateTotalRev(List<Sale> sales) {
		return sales.stream().mapToDouble(Sale::getPrice).sum();	
		}
	public static int countSales(List<Sale> sales) {
		return sales.size();
	}
}
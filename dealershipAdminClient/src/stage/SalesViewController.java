package stage;

import control.SalesManager;
import model.Customer;
import model.Sale;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// sale processing interface
public class SalesViewController {
	private final SalesManager salesManager;
	
	public Node getView() {
		TableView<Sale> table = new TableView<>();
		ObservableList<Sale> saleData = FXCollections.observableArrayList(salesManager.get);
		table.setItems(customerData);
		
		return null;
	}
}
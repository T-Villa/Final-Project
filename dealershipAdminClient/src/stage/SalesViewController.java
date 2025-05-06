package stage;

import control.CustomerManager;
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
		ObservableList<Sale> salesData = FXCollections.observableArrayList(salesManager.genReport());
		table.setItems(salesData);
		
		return null;
	}
	public SalesViewController(SalesManager salesManager) {
		this.salesManager = salesManager;
	}
}
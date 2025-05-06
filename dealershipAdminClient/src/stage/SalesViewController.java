package stage;

import control.CustomerManager;
import control.SalesManager;
import model.Customer;
import model.Sale;
import model.Customer;

import java.time.LocalDate;
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

		//Columns 
		TableColumn<Sale,String> idColumn = new TableColumn<>("Sale ID");
			idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSaleID()));
		TableColumn<Sale,LocalDate> saleDateColumn = new TableColumn<>("Date Sold");
			saleDateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getSaleDate()));
		TableColumn<Sale,String> buyerColumn = new TableColumn<>("Buyer");
			buyerColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCustomer()));
		TableColumn<Sale,Double> priceColumn = new TableColumn<>("Sold Price");
			priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPrice()));

		table.getColumns().addAll(idColumn,saleDateColumn,buyerColumn,priceColumn);

		//Buttons
		
		//Stage
		VBox layout = new VBox(10, table/* buttons*/);
		layout.setPadding(new Insets(10));
		
		return layout;
	}
	public SalesViewController(SalesManager salesManager) {
		this.salesManager = salesManager;
	}
}

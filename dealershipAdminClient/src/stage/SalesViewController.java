package stage;

import java.time.LocalDate;

import control.SalesManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Sale;
import util.ButtonController;
import util.Filter;

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
			buyerColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCustomer().getName()));
		TableColumn<Sale,Double> priceColumn = new TableColumn<>("Sold Price");
			priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPrice()));
		TableColumn<Sale,String> sellerColumn = new TableColumn<>("Seller");
			sellerColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSeller()));
		TableColumn<Sale,String> carBought = new TableColumn<>("Car Purchased");
			carBought.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescription()));


		table.getColumns().addAll(idColumn,saleDateColumn,buyerColumn,priceColumn,sellerColumn, carBought);

		//Buttons
		Button genReport = ButtonController.genReportButton(salesData);
			
		//Stage
		HBox filter = new HBox(10, Filter.sellerFilter(salesManager, salesData), Filter.customerSalesFilter(salesManager, salesData));
        filter.setPadding(new Insets(10));
		
		VBox layout = new VBox(10,filter, table, genReport);
			layout.setPadding(new Insets(10));
			carBought.setPrefWidth(200);

		
		return layout;
	}
	public SalesViewController(SalesManager salesManager) {
		this.salesManager = salesManager;
	}
}

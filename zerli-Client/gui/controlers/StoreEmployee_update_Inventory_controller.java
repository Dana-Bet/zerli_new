package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Item_In_Catalog;
import Entities.Message;
import Entities.MessageType;
import Entities.Product_In_Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientUI;

public class StoreEmployee_update_Inventory_controller extends AbstractController implements Initializable {
    public static ArrayList<Product_In_Inventory> Inventories;
    
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
    @FXML
    private TableView<Product_In_Inventory> table;

    @FXML
    private TableColumn<Product_In_Inventory, String> StoreCol;

    @FXML
    private TableColumn<Product_In_Inventory, String> ProducCol;

    @FXML
    private TableColumn<Product_In_Inventory, Integer> QuanCol;

    @FXML
    private TableColumn<Product_In_Inventory, String> ProNameCol;

    @FXML
    private Button BackBtn;

    @FXML
    private Label UpLbl;

    @FXML
    private TextField newQuanText;

    @FXML
    private Button UpdateBtn;

    @FXML
    void Back(ActionEvent event) throws IOException {
    	start(event, "StoreEmployee_update_Catalog", "Update catalog", "");
    }

    @FXML
    void UpdateQuan(ActionEvent event) {
    	this.UpLbl.setText("");
    	Product_In_Inventory product = null;
    	product = table.getSelectionModel().getSelectedItem();
        if(product!=null) {
            String newQuan = null;
            newQuan = this.newQuanText.getText();
            if (newQuan==null || !(Integer.parseInt(newQuan)>0)||newQuan.isEmpty()) {
		    	this.UpLbl.setText("Please insert valid value");
		    	return;
            }
        	ArrayList<String> details = new ArrayList<String>();
        	details.add(newQuan);
        	details.add(product.getStore());
        	details.add(product.getProId());
          	details.add("1");
			ClientUI.chat.accept(new Message(MessageType.UpdateQuanInInventory,details));
		    initialize(location, resources) ;
        
        }
        else {
	    	this.UpLbl.setText("Please select item from table");
	    	return;
        }
        
        
        }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	this.UpLbl.setText("");
		ClientUI.chat.accept(new Message(MessageType.get_Inventories,LoginScreenController.user.getHomeStore()));
		ObservableList<Product_In_Inventory> observableList = FXCollections.observableArrayList(Inventories);
		table.getItems().clear();
		StoreCol.setCellValueFactory(new PropertyValueFactory<>("Store"));
		ProducCol.setCellValueFactory(new PropertyValueFactory<>("ProId"));
		QuanCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
		ProNameCol.setCellValueFactory(new PropertyValueFactory<>("ProName"));
		table.setItems(observableList);	
		
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

}

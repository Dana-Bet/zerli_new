package controlers;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.LineInCartTable;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientUI;

public class ClientOrdersController extends AbstractController implements Initializable {
	public static ArrayList<Order> list; 
	public static ArrayList<String> recipt=null;

    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, Integer> orderNumberCol;

    @FXML
    private TableColumn<Order, String> storeCol;

    @FXML
    private TableColumn<Order, String> greetingCardCol;

    @FXML
    private TableColumn<Order, Float> priceCol;

    @FXML
    private TableColumn<Order, String> DeliveryMethodCol;

    @FXML
    private TableColumn<Order, String> suppTimeCol;

    @FXML
    private TableColumn<Order, String> suppDateCol;

    @FXML
    private TableColumn<Order,Time> OrderTimeCol;
    
    @FXML
    private TableColumn<Order, String> statusCol;

    @FXML
    private Button backBtn;
    
    @FXML
    private Button displayOrder;

    @FXML
    private TextArea ViewRecipt;

    @FXML
    private Button CancelBtn;

    @FXML
    private Button ComplaintBtn;

    @FXML
    void CancelSelectedOrder(ActionEvent event) {

    }

    @FXML
    void DisplaySelectedOrder(ActionEvent event) {
        ObservableList<Order> list;
        list = this.table.getSelectionModel().getSelectedItems();
        if(list!=null) {
            int OrderNum =list.get(0).getOrderNumber();
			ClientUI.chat.accept(new Message(MessageType.getRecipt,OrderNum));
			updateTextRecipt();
        }
    }

    @FXML
    void SendComplaint(ActionEvent event) {

    }
    @FXML
    void backMainPage(ActionEvent event) throws IOException {
    	start(event, "ClientMainPage", "Customer Screen", "");
    }
    
    private void updateTextRecipt() {
    	this.ViewRecipt.clear();
    	for (String s : ClientOrdersController.recipt ) {
    		this.ViewRecipt.appendText(s);
    		this.ViewRecipt.appendText("\n");
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ClientUI.chat.accept(new Message(MessageType.Get_All_Order_by_id,LoginScreenController.user.getId()));
		ObservableList<Order> observableList = FXCollections.observableArrayList(list);
		table.getItems().clear();
		orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
		storeCol.setCellValueFactory(new PropertyValueFactory<>("Store"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
		greetingCardCol.setCellValueFactory(new PropertyValueFactory<>("GreetingCard"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
		DeliveryMethodCol.setCellValueFactory(new PropertyValueFactory<>("SupplimentMethod"));
		suppTimeCol.setCellValueFactory(new PropertyValueFactory<>("SuppTime"));
		suppDateCol.setCellValueFactory(new PropertyValueFactory<>("SuppDate"));
		OrderTimeCol.setCellValueFactory(new PropertyValueFactory<>("OrderTime"));
		table.setItems(observableList);	
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

}

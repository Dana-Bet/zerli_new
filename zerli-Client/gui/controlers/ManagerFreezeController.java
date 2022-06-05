package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import main.ClientUI;

public class ManagerFreezeController extends AbstractController implements  Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label chooseCustomer;

    @FXML
    private ComboBox<String> customerName;

    @FXML
    private Button freezeBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Label upLbl;
    
    @FXML
    private Label conlbl;

    
    public static ArrayList<String> customerList;

    String customer;


    @FXML
    void Freeze(ActionEvent event) {
  		    this.conlbl.setText("");
	    	this.upLbl.setText("");
    		customer = customerName.getValue();
    		if(customer==null || customer.isEmpty()) {
    			this.upLbl.setText("Please select user to frezze.");
    			return;
    		}
    		else {
        		ClientUI.chat.accept(new Message(MessageType.customerFreeze,customer));
        		this.conlbl.setText("Customer's account is frozen.");
    		}

    }
    @FXML
		void back(ActionEvent event) throws IOException {
			start(event,"ManagerMainPageScreen","Branch Manager", LoginScreenController.user.getFirstN());
		}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	this.upLbl.setText("");
  		  ClientUI.chat.accept(new Message(MessageType.getCustomerToFreeze,""));
  		  ObservableList<String> observableList1 = FXCollections.observableArrayList(customerList);
  		  customerName.setItems(observableList1);
  	      this.conlbl.setText("");
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

}

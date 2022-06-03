package controlers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import controlers.AbstractController;
import controlers.LoginScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import main.ClientUI;


public class MainManagerScreenController extends AbstractController implements  Initializable{


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button Revenuebtn;

    @FXML
    private Button AddAccountBtn;

    @FXML
    private Button ordersBtn;

    @FXML
    private Button FreezeAccountBtn;

    @FXML
    private Label HiUserLabel;

    @FXML
    void AddAccount(ActionEvent event) throws IOException {
    	start(event,"ManagerOpenNewAccount","Open New Account",LoginScreenController.user.getFirstN());
    }

    @FXML
    void FreezeAccount(ActionEvent event) throws IOException {
    	start(event,"ManagerFreezeAccount","View Orders Report","");
    }

    @FXML
    void ViewStoreReports(ActionEvent event) throws IOException {
    	start(event,"ManagerViewRevenueReport","View revenue Report","");
    }

    @FXML
    void ViewStoreReportsOrders(ActionEvent event) throws IOException {
    	start(event,"ManagerViewOrdersReport","View Orders Report","");
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		start(event, "LoginUserScreen", "Login","");
    }

    @FXML
    void ordersPage(ActionEvent event) throws IOException {
    	start(event,"ManagerOrderConfirmScreen","Store orders table","");
    }

	@Override
	public void display(String string) {

	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		HiUserLabel.setText("Hi "+LoginScreenController.user.getUserName());
		
	}

}
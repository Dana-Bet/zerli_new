package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import main.ClientUI;


public class ClientMainPageController extends AbstractController implements Initializable {
	public static ArrayList<Integer> newClient ;
	private String id = LoginScreenController.user.getId();
	
    @FXML
    private ImageView cartImage;
    
    @FXML
    private Button CartBtn;

    @FXML
    private Button LogoutBtn;

    @FXML
    private Button CreateOrderBtn;

    @FXML
    private Label NumberOfProductLbl;
    
    @FXML
    private Button MyAccountBtn;

    @FXML
    private Label HiUserLabel;

    @FXML
    private Button MyOrdersBtn;


    @FXML
	public void OpenCartPage(ActionEvent event) throws Exception {
          start(event, "ClientCartScreen", "your cart", "");
	}
    
    @FXML
	public void OpenCreateOrdersPage(ActionEvent event) throws Exception {
		ClientUI.chat.accept(new Message(MessageType.IsNewClient,id));
		if(newClient.get(0)!=0)
		{
			
			try {
				startPopUp(new ActionEvent(), "Client_msg_new_client", "","");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
          start(event, "ClientCreateOrderScreen", "Create Order", "");
	}


    @FXML
    void logout(ActionEvent event) throws IOException {
    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		start(event, "LoginUserScreen", "Login","");
    }

    
    @FXML
    void OpenOrdersTable(ActionEvent event) throws IOException {
    	 start(event, "ClientOrdersScreen", "my orders","");
    }

    @FXML
    void OpenMyAccountPage(ActionEvent event) throws IOException {
       start(event, "ClientAccountPage", "My Zerli Account", "");
    }
    

	@Override
	public void display(String string) {
		HiUserLabel.setText("Hi "+LoginScreenController.user.getUserName());
		
			
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		this.NumberOfProductLbl.setText(ClientCartScreenController.cart.getNumberOfItems().toString());
		CartBtn.setStyle("-fx-background-color: transparent;");
		CartBtn.setGraphic(cartImage);
	}
	    
	    
	    

}

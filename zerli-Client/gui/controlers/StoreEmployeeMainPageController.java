package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.ClientUI;

public class StoreEmployeeMainPageController extends AbstractController implements Initializable {

    @FXML
    private Label HiUserLabel;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button UpdateCatalogBtn;

    @FXML
    void UpdateCatalog(ActionEvent event) throws IOException {
    	start(event, "StoreEmployee_update_Catalog", "update_Catalog","");
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		start(event, "LoginUserScreen", "Login","");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(String string) {
		HiUserLabel.setText("Hi "+LoginScreenController.user.getUserName());
		
	}

}

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
import javafx.scene.control.TextArea;
import main.ClientUI;

public class ManagerSendCancellationEmailController extends AbstractController implements Initializable { 
    private String[] str ; 

    @FXML
    private Button sendBtn;

    @FXML
    private TextArea EmailContentBox;


    @FXML
    void sendEmail(ActionEvent event) throws IOException {
    	stopPopUp(event);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
	
	}

	@Override
	public void display(String string) {
        str = string.split("#");
		StringBuilder textToEmail = new StringBuilder();
		textToEmail.append("Send to : "+str[0]);
		textToEmail.append("\nPhone No : "+str[1]);
		textToEmail.append("\nYour order is canceled and your Zerli account got "+ ManagerOrdersController.ammount.toString() +"$ for your next purchase, see you soon!\n");	
        this.EmailContentBox.setText(textToEmail.toString());
	
	}
}

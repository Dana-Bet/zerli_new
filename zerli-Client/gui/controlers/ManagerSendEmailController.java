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

public class ManagerSendEmailController extends AbstractController implements Initializable { 
    private String[] str ;
    public static ArrayList<String> email_phone;
    @FXML
    private Button sendBtn;

    @FXML
    private TextArea EmailContentBox;

    @FXML
    private Label titleOfWin;

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
		ClientUI.chat.accept(new Message(MessageType.getClientEmailAndPhone,str[0]));
		System.out.println(email_phone.toString());
		if (str[1].equals("1")){
			this.titleOfWin.setText("Cancellation Email:");
		}
		else{
			this.titleOfWin.setText("Confirmed Email:");
		}
		
		StringBuilder textToEmail = new StringBuilder();
		textToEmail.append("Send to : "+email_phone.get(0));
		textToEmail.append("\nPhone No : "+email_phone.get(1));
		if (str[1].equals("1")){
			textToEmail.append("\nYour order is canceled and your Zerli account got "+ ManagerOrdersController.ammount.toString() +"$ for your next purchase, see you soon!\n");	
		}
		else {
	    
			textToEmail.append("\nYour order is confirm , Zerli already miss you, see you soon!\n");	
		}

        this.EmailContentBox.setText(textToEmail.toString());
	}

}

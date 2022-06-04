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

public class CustomerEm_main_page_controller extends AbstractController implements Initializable {

    @FXML
    private Label HiUserLabel;

    @FXML
    private Button LogoutBtn;

    @FXML
    private Button ComplaintTableBtn;

    @FXML
    private Button InsertSurveysBtn;

    @FXML
    private Button UploadServiceBtn;

    @FXML
    void goComplaintTable(ActionEvent event) throws IOException {
        start(event, "CustomerEm_TableComplainScreen", "Table complaints Screen", "");
    }

    @FXML
    void goInsertSurveysPage(ActionEvent event) throws IOException {
        start(event, "CustomerEm_Insert_Survey", "Insert surveys results screen", "");
    }

    @FXML
    void goUpReportPage(ActionEvent event) throws IOException {
        start(event, "Customer_Em_upload_PDF", "Insert surveys results screen", "");
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
    	ClientUI.chat.accept(new Message(MessageType.Disconected,LoginScreenController.user.getUserName()));
		start(event, "LoginUserScreen", "Login","");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(LoginScreenController.user.getRole().equals("CustomerEmployee")) {
			this.UploadServiceBtn.setDisable(true);
		}
		else if (LoginScreenController.user.getRole().equals("ServiceSpecialist")) {
			this.InsertSurveysBtn.setDisable(true);
		}
		
	}

	@Override
	public void display(String string) {
		HiUserLabel.setText("Hi "+LoginScreenController.user.getUserName());
	}

}
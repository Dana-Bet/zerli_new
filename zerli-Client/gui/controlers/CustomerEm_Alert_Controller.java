package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CustomerEm_Alert_Controller extends AbstractController implements Initializable{

    @FXML
    private Button closeBtn;

    @FXML
    private Label msgLbl;

    @FXML
    void closeBtn(ActionEvent event) throws IOException {
    	CustomerEmTableComplaintsScreenController.complaint.setAlert(1);
    	stopPopUp(event);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.msgLbl.setText("Please note this is a one-time alert. The complaint was filed over 24 hours ago. Please reply as soon as possible.");
		
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

}

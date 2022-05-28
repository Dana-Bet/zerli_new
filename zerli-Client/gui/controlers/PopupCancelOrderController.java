package controlers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PopupCancelOrderController extends AbstractController implements Initializable {


    @FXML
    private Button cancelBtn;

    @FXML
    private Button ConfirmBtn;

    @FXML
    private Label msgLbl;

    @FXML
    private Label queLbl;

    @FXML
    void CancelAction(ActionEvent event) {

    }

    @FXML
    void ConfirmCancel(ActionEvent event) {

    }

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
	}

}

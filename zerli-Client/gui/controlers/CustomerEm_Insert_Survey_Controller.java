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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import main.ClientUI;

public class CustomerEm_Insert_Survey_Controller extends AbstractController implements Initializable  {
	private ArrayList<Integer> answers;
    private String SurveyId; 
	private String ClientId;
	
	public static ArrayList<String> Surveys;
	public static ArrayList<String> Clients;

    @FXML
    private Button BackBtn;

    @FXML
    private Button UpLoadBtn;

    @FXML
    private Slider s1;

    @FXML
    private Slider s6;

    @FXML
    private Slider s2;

    @FXML
    private Slider s3;

    @FXML
    private Slider s4;

    @FXML
    private Slider s5;

    @FXML
    private ChoiceBox<String> SurveysIdList;

    @FXML
    private ChoiceBox<String> ClientIdList;

    @FXML
    private Label UpLbl;

    @FXML
    void BackMain(ActionEvent event) throws IOException {
    	start(event, "CustomerEm_main_page", "Customers service employee screen", "");
    }

    @FXML
    void UploadSurveyToDB(ActionEvent event) throws IOException {
		UpLbl.setText("");

    	if (ClientId==null) {
    		UpLbl.setText("Please select client");
    		return;
    	}
    	if(SurveyId==null) {
    		UpLbl.setText("Please select survey");
    		return;
    	}
    	
    	this.answers=new ArrayList<Integer>();	
        answers.add(Integer.valueOf(ClientId));
        answers.add((int)s1.getValue());
        answers.add((int)s2.getValue());
        answers.add((int)s3.getValue());
        answers.add((int)s4.getValue());
        answers.add((int)s5.getValue());
        answers.add((int)s6.getValue());
        answers.add(Integer.valueOf(SurveyId));

        ClientUI.chat.accept(new Message(MessageType.Add_Survey_Result,answers));
        start(event, "CustomerEm_main_page", "Customers service employee screen", "");
        }
    


    @FXML
    void ChooseClient(ActionEvent event) {
           ClientId = ClientIdList.getValue();
    }

    @FXML
    void ChooseSurvey(ActionEvent event) {
           this.SurveyId = SurveysIdList.getValue();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ClientUI.chat.accept(new Message(MessageType.getAllClients,""));
		ClientUI.chat.accept(new Message(MessageType.getSurveysId,""));
		
		this.ClientIdList.getItems().addAll(Clients);
		this.SurveysIdList.getItems().addAll(Surveys);
		
	
		this.ClientId=null;
		this.SurveyId=null;
		
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}
	

}
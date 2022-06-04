package controlers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.MyFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.ClientUI;


public class Customer_Em_upload_PDF_controller extends AbstractController implements  Initializable{

    @FXML
    private Button BackBtn;

    @FXML
    private Button UpLoadBtn;

    @FXML
    void BackMain(ActionEvent event) throws IOException {
    	start(event, "CustomerEm_main_page", "Customers service employee screen", "");
    }

    @FXML
    void UploadReport(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
				Stage stage = new Stage();
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					String path = file.getPath();
					File f = new File(path);
					MyFile msg = new MyFile(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1));
					try {
						File newFile = new File(path);
						byte[] mybytearray = new byte[(int) newFile.length()];
						msg.initArray(mybytearray.length);
						msg.setSize(mybytearray.length);
						FileInputStream fis = new FileInputStream(newFile);
						BufferedInputStream bis = new BufferedInputStream(fis);
						bis.read(msg.getMybytearray(), 0, mybytearray.length);
						Timestamp date = new java.sql.Timestamp(new Date().getTime());
						bis.close();
						ClientUI.chat.accept(new Message(MessageType.send_PDF, msg));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}
    

}
    	
    



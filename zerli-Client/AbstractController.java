package client.controllers;

import java.io.IOException;

import Entities.Message;
import Entities.MessageType;
import Entities.SingletonOrder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.ClientUI;
import main.PopUpMessage;

/**
 * @author Aviel 
 * @author Sahar
 * Native object to be inherited by the controllers.
 * This class contains methods that be used by all the controllers. 
 */
public abstract class AbstractController {

	public void start(ActionEvent event, String fxmlName, String title, String toDisplay) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/" + fxmlName + ".fxml"));
		Parent root = load.load();
		AbstractController aFrame = load.getController();
		aFrame.display(toDisplay);
		Scene scene = new Scene(root);
		primaryStage.setTitle("BiteMe" + " " + title);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				event.consume();
				boolean ans = PopUpMessage.confirmDialog("Do you want to logout and exit from system?", primaryStage);
				if (ans) {
					if (!(fxmlName.equals("LoginScreen"))) {
						ClientUI.chat
								.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
					}
					primaryStage.close();
				}
			}
		});
	}

	/**
	 * This method is to open any fxml screen
	 * @param event, fxmlName, title, toDisplay
	 */
	public void start(MouseEvent event, String fxmlName, String title, String toDisplay) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/fxml/" + fxmlName + ".fxml"));
		Parent root = load.load();
		AbstractController aFrame = load.getController();
		aFrame.display(toDisplay);
		Scene scene = new Scene(root);
		primaryStage.setTitle("BiteMe" + " " + title);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				event.consume();
				boolean ans = PopUpMessage.confirmDialog("Do you want to logout and exit from system?", primaryStage);
				if (ans) {
					if (!(fxmlName.equals("LoginScreen"))) {
						ClientUI.chat
								.accept(new Message(MessageType.Disconected, LoginScreenController.user.getUserName()));
					}
					primaryStage.close();
				}
			}
		});
	}

	/**
	 * Abstract method that will be implemented in any class with any using
	 * @param string
	 */
	public abstract void display(String string);

	/**
	 * This method is for logout from the system.
	 */
	public void logoutForCustomer() {
		SingletonOrder.getInstance().myOrder.clear();
	}
}
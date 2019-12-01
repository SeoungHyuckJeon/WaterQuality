package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private Label lblCurrentPlant;
	
	public void searchAddress() {
		String address;
		
		System.out.println("open address");		

		try {
			AnchorPane searchPage = FXMLLoader.load(Main.class.getResource("/application/SearchAddress.fxml"));
			Scene scene = new Scene(searchPage);
			Stage Stage = new Stage();
			Stage.setScene(scene);
			Stage.show();
			Stage.setResizable(false);
		}	catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

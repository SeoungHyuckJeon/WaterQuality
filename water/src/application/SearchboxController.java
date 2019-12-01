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

public class SearchboxController {
	
	Main main = new Main();
	
	public void ClickedbtnSearch() {
		System.out.println("btn Pressed");
	}
	
	public void PressedSearchBox(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			System.out.println("btn Pressed");
		}
	}
}

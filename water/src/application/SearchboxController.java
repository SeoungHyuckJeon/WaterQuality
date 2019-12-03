package application;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SearchboxController {
	getAPIData getAPIData = new getAPIData();
	Main main = new Main();
	
	@FXML
	private TableView<wwpModel> textfield_SearchBox;
	
	
	public void ClickedbtnSearch() {
		System.out.println("btn Pressed");
	}
	
	public void PressedSearchBox(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			System.out.println("btn Pressed");
		}
	}
}

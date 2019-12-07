package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class DetailController implements Initializable {
	@FXML
	private Label lblDetailPlantName;
	@FXML
	private Label lblDetailaddress;
	@FXML
	private Label lblDetailnumber;
	@FXML
	private Label lblDetaildate;
	@FXML
	private Label lblDetaildiv;
	@FXML
	private Label lblDetailclval;
	@FXML
	private Label lblDetailphval;
	@FXML
	private Label lblDetailtbval;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblDetailPlantName.setText(application.WaterData.fcltyMngNm);
		lblDetailaddress.setText(application.WaterData.fcltyAddr);
		lblDetailnumber.setText(application.WaterData.fcltyMngNo);
		lblDetaildate.setText(application.WaterData.date);
		lblDetaildiv.setText(application.WaterData.liIndDivName);
		lblDetailclval.setText(application.WaterData.clVal);
		lblDetailphval.setText(application.WaterData.phVal);
		lblDetailtbval.setText(application.WaterData.tbVal);
	}
}

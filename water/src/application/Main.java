package application;
	
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	static String date;
	static String hour;
	static String lgldCode;		//������ �ڵ�
	static String ServiceKey = "jKq7bWPMdGPSHRToLGkotTffxNPQFoZ88H%2FjbH%2BSWSz836fHMXAaKVgKnvtxAHLVWZ0%2FqQzXJIViKEW2jOk1Og%3D%3D";
	static String URL_supplylgld = "http://apis.data.go.kr/B500001/rwis/waterQuality/supplyLgldCode/list?serviceKey="
			+ ServiceKey +"&numOfRows=758&pageNo=1";
	static String URL_waterquality = "http://apis.data.go.kr/B500001/rwis/waterQuality/list"
			+ "?serviceKey=" + ServiceKey
			+ "&stDt=" + date
			+ "&stTm=" + hour
			+ "&edDt=" + date
			+ "&edTm=" + hour
			+ "&lgldCode=" + lgldCode
			+ "&liIndDiv=1";	//���� = ��Ȱ���
	//�ð�����ؼ� 15�� ���̸� hour-1 ���� �ð����� �־��ֱ�
	getAPIData getAPIData = new getAPIData();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Waterquality.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getTime() {
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat format2 = new SimpleDateFormat("HH");
    	SimpleDateFormat format3 = new SimpleDateFormat("mm");
    	
    	Date time = new Date();
    	
    	date = format1.format(time);
    	hour = format2.format(time);
    	String minute = format3.format(time);
    	int m = Integer.parseInt(minute);
    	if(m<=5)		//5�� �����̸� ���� �ð��� �����
    	{
    		int h = Integer.parseInt(hour);
    		h--;
    		hour=Integer.toString(h);
    	}
	}
	
	public static void main(String[] args) {
		launch(args);
		getTime();
		
	}
}

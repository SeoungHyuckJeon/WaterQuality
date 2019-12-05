package application;
	
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	static String date;
	static String hour;
	static String fcltyMngNo;		//법정동 코드
	static String ServiceKey = "jKq7bWPMdGPSHRToLGkotTffxNPQFoZ88H%2FjbH%2BSWSz836fHMXAaKVgKnvtxAHLVWZ0%2FqQzXJIViKEW2jOk1Og%3D%3D";
	static String URL_supplylgld = "http://apis.data.go.kr/B500001/rwis/waterQuality/supplyLgldCode/list?serviceKey="
			+ ServiceKey +"&numOfRows=758&pageNo=1";
	static String URL_waterquality = "http://apis.data.go.kr/B500001/rwis/waterQuality/list"
			+ "?serviceKey=" + ServiceKey
			+ "&stDt=" + date
			+ "&stTm=" + hour
			+ "&edDt=" + date
			+ "&edTm=" + hour
			+ "&fcltyMngNo=" + fcltyMngNo
			+ "&liIndDiv=1";	//선택 = 생활용수
	//시간계산해서 15분 전이면 hour-1 값을 시간으로 넣어주기
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
    	if(m<=5)		//5분 이전이면 이전 시간을 기록함
    	{
    		int h = Integer.parseInt(hour);
    		h--;
    		hour=Integer.toString(h);
    	}
	}
	
	public void getPlantData(String url)
	{
        try {
        	// 자신의 static 매소드를 가지고 객체를 생성 : 싱글톤 패턴
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	//다른 클래스의 객체를 가지고 객체를 생성하면 팩토리 패턴
        	DocumentBuilder documentbuilder = factory.newDocumentBuilder();

        	Document doc = documentbuilder.parse(url);
        	
        	NodeList nList = doc.getElementsByTagName("item");
        	
        	Node nNode = nList.item(0);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) nNode;
				if(application.getAPIData.getTagValue("fcltyMngNo", eElement).contains(fcltyMngNo))
				{
					
				}
			}    	
        } catch(Exception e) { 
        	e.printStackTrace(); }
	}
	
	public static void main(String[] args) {
		launch(args);
		getTime();
		
	}
}

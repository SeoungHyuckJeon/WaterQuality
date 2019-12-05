package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private static Label lblCurrentPlant;		//������ �̸�
	@FXML
	private static Label lblPlantAddress;		//������ �ּ�
	@FXML
	private static Label lblSurveyTime;		//���� �ð�
	@FXML
	private static ImageView imgWaterStatus;	//��ü ���
	@FXML
	private static ImageView imgclVal;			//�ܷ����� ���
	@FXML
	private static ImageView imgpHVal;			//pH ���
	@FXML
	private static ImageView imgtbVal;			//Ź�� ���
	
	static Stage Stage = new Stage();
	
	static String date;
	static String hour;
	static String fcltyMngNo=null;		//�ҷ��� ������ �ڵ�
	
	
	static String ServiceKey = "jKq7bWPMdGPSHRToLGkotTffxNPQFoZ88H%2FjbH%2BSWSz836fHMXAaKVgKnvtxAHLVWZ0%2FqQzXJIViKEW2jOk1Og%3D%3D";
	static String URL_supplylgld = "http://apis.data.go.kr/B500001/rwis/waterQuality/supplyLgldCode/list?serviceKey="
			+ ServiceKey +"&numOfRows=758&pageNo=1";
	static String URL_waterquality;
	//�ð�����ؼ� 5�� ���̸� hour-1 ���� �ð����� �־��ֱ�
	
	public void searchAddress() {
		try {
			AnchorPane searchPage = FXMLLoader.load(Main.class.getResource("/application/SearchAddress.fxml"));
			Scene scene = new Scene(searchPage);

			Stage.setScene(scene);
			Stage.show();
			Stage.setResizable(false);
		}	catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getTime() {
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat format2 = new SimpleDateFormat("HH");
    	SimpleDateFormat format3 = new SimpleDateFormat("mm");
    	
    	Date time = new Date();
    	Date yesterday = new Date();
    	yesterday.setTime(time.getTime() - ((long)1000*60*60*24));
    	
    	hour = format2.format(time);
    	String minute = format3.format(time);
		int h = Integer.parseInt(hour);
    	int m = Integer.parseInt(minute);
    	if(m<=5)		//5�� �����̸� ���� �ð��� �����
    	{
    		h--;
    	}

		if(h==-1)		//�ð��� -1�̸� ������¥�� 23�÷� ����
		{
			h=23;
			date = format1.format(yesterday);
		}
		else if(h==0)	//�ð��� 0�̸� ������¥�� 24�÷� ����
		{
			h=24;
			date = format1.format(yesterday);
		}
    	else	date = format1.format(time);
		hour=Integer.toString(h);

	}
	
	public static void getPlantData()
	{
		StringBuilder sb = new StringBuilder(); 		//url ������ ���� ������
		URL_waterquality=sb.append("http://apis.data.go.kr/B500001/rwis/waterQuality/list")
						.append("?serviceKey=" + ServiceKey)
						.append("&stDt=" + date)
						.append("&stTm=" + hour)
						.append("&edDt=" + date)
						.append("&edTm=" + hour)
						.append("&fcltyMngNo=" + fcltyMngNo)
						.append("&liIndDiv=1&numOfRows=1&pageNo=1").toString();	//���� = ��Ȱ���
		System.out.println(date);
		System.out.println(hour);
		System.out.println(URL_waterquality);
		
		String fcltyMngNm = null;
		
        try {
        	// �ڽ��� static �żҵ带 ������ ��ü�� ���� : �̱��� ����
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	//�ٸ� Ŭ������ ��ü�� ������ ��ü�� �����ϸ� ���丮 ����
        	DocumentBuilder documentbuilder = factory.newDocumentBuilder();
        	//XML���� ����
        	Document doc = documentbuilder.parse(URL_waterquality);
        	
        	NodeList nList = doc.getElementsByTagName("item");
        	for(int temp =0; temp<nList.getLength();temp++)
        	{
        		Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					
					fcltyMngNm=application.getAPIData.getTagValue("fcltyMngNm", eElement).toString();
					System.out.println(application.getAPIData.getTagValue("fcltyMngNm", eElement));
				}
        	}   
        } catch(Exception e) { 
        	e.printStackTrace(); }
        
        lblCurrentPlant.setText(fcltyMngNm);
	}
}

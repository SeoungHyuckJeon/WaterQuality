package application;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SearchboxController {
	static getAPIData getAPIData = new getAPIData();
	Main main = new Main();
	
	@FXML
	private TextField textfield_SearchBox = new TextField();
	@FXML
	private TableView<wppModel> tableview_WPP;
	@FXML
	private TableColumn<wppModel, String> column_Address;
	@FXML
	private TableColumn<wppModel, String> column_Plant;
	
	ObservableList<wppModel> wppList = FXCollections.observableArrayList();
	
	public void ClickedbtnSearch() {		//검색 버튼 클리시
		System.out.println("btn Pressed");
		getPlantData(main.URL_supplylgld);
		initialize();
	}
	
	public void PressedSearchBox(KeyEvent event) {		//TextField에서 엔터눌렀을때
		if(event.getCode() == KeyCode.ENTER) {
			getPlantData(main.URL_supplylgld);
			initialize();
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
        	for(int temp =0; temp<nList.getLength();temp++)
        	{
        		Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					if(application.getAPIData.getTagValue("lgldFullAddr", eElement).contains(textfield_SearchBox.getText()))
					{
						wppList.add(new wppModel(new SimpleStringProperty(application.getAPIData.getTagValue("lgldFullAddr", eElement)),
    							new SimpleStringProperty(application.getAPIData.getTagValue("fcltyMngNm", eElement))));
						
//						System.out.println("######################");
						//System.out.println(textfield_SearchBox.getText());
//						System.out.println("정수장  : " + application.getAPIData.getTagValue("fcltyMngNm", eElement));
						//System.out.println("정수장번호 : " + getTagValue("fcltyMngNo", eElement));
//						System.out.println("주소  : " + application.getAPIData.getTagValue("lgldFullAddr", eElement));
					}
				}
        	}     	
        } catch(Exception e) { 
        	e.printStackTrace(); }
	}
	
	public void initialize() {
		column_Address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
		column_Plant.setCellValueFactory(cellData->cellData.getValue().plantProperty());
		tableview_WPP.setItems(wppList);
	}
}

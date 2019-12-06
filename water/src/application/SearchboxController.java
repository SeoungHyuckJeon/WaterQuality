package application;

import java.io.IOException;
import java.util.ArrayList;

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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SearchboxController {
	static getAPIData getAPIData = new getAPIData();
	
	@FXML
	private TextField textfield_SearchBox = new TextField();
	@FXML
	private TableView<wppModel> tableview_WPP;
	@FXML
	private TableColumn<wppModel, String> column_Address;
	@FXML
	private TableColumn<wppModel, String> column_Plant;
	private ArrayList<String> fcltyMngNo = new ArrayList<String>();
	
	ObservableList<wppModel> wppList = FXCollections.observableArrayList();
	@FXML
	public void ClickedbtnSearch() {		//검색 버튼 클리시
		System.out.println("btn Pressed");
		wppList.clear();				//테이블 내용 초기화
		fcltyMngNo.clear();				//배열 초기화
		getPlantDataS(application.MainController.URL_supplylgld);
		initialize();
	}
	@FXML
	public void PressedSearchBox(KeyEvent event) {		//TextField에서 엔터눌렀을때
		if(event.getCode() == KeyCode.ENTER) {		//ENTER입력시
			wppList.clear();			//테이블 내용 초기화
			fcltyMngNo.clear();			//배열 초기화
			getPlantDataS(application.MainController.URL_supplylgld);
			initialize();
		}
	}
	@FXML
	public void getPlantDataS(String url)
	{
        try {
        	// 자신의 static 매소드를 가지고 객체를 생성 : 싱글톤 패턴
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	//다른 클래스의 객체를 가지고 객체를 생성하면 팩토리 패턴
        	DocumentBuilder documentbuilder = factory.newDocumentBuilder();
        	//XML문서 접근
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
						fcltyMngNo.add(application.getAPIData.getTagValue("fcltyMngNo", eElement));
					}
				}
        	}     	
        } catch(Exception e) { 
        	e.printStackTrace(); }
	}
	@FXML
	public void initialize() {			//불러온값을 각각 셀에 추가하고 셀을 테이블에 추가한다.
		column_Address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
		column_Plant.setCellValueFactory(cellData->cellData.getValue().plantProperty());
		tableview_WPP.setItems(wppList);
	}
	@FXML
	public void CellDoubleClick(MouseEvent event) {
		if(event.getClickCount()>1 && tableview_WPP.getSelectionModel().getSelectedIndex()!=-1) {	//셀이 선택되어있고, 더블클릭했을때 이벤트
			application.MainController.fcltyMngNo=fcltyMngNo.get(tableview_WPP.getSelectionModel().getSelectedIndex());	//정수장 번호 넘겨줌
			
			application.MainController.Stage.close();		//검색창 닫기
			application.MainController.getTime();			//현재 시간 불러오기
			application.MainController.getPlantDataM();		//선택한 정수장 수질 불러오기
			
			Stage stg = new Stage();
			try {
			AnchorPane mainPage = FXMLLoader.load(Main.class.getResource("/application/Waterquality.fxml"));
			Scene scene = new Scene(mainPage);
			
			stg.setScene(scene);
			stg.show();
			stg.setResizable(false);
			} catch (Exception e) {

			}
		}
	}
}

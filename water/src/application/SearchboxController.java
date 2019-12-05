package application;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
	
	public void ClickedbtnSearch() {		//�˻� ��ư Ŭ����
		System.out.println("btn Pressed");
		wppList.clear();				//���̺� ���� �ʱ�ȭ
		fcltyMngNo.clear();				//�迭 �ʱ�ȭ
		getPlantData(Main.URL_supplylgld);
		initialize();
	}
	
	public void PressedSearchBox(KeyEvent event) {		//TextField���� ���ʹ�������
		if(event.getCode() == KeyCode.ENTER) {		//ENTER�Է½�
			wppList.clear();			//���̺� ���� �ʱ�ȭ
			fcltyMngNo.clear();			//�迭 �ʱ�ȭ
			getPlantData(Main.URL_supplylgld);
			initialize();
		}
	}
	
	public void getPlantData(String url)
	{
        try {
        	// �ڽ��� static �żҵ带 ������ ��ü�� ���� : �̱��� ����
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	//�ٸ� Ŭ������ ��ü�� ������ ��ü�� �����ϸ� ���丮 ����
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
						fcltyMngNo.add(application.getAPIData.getTagValue("lgldCode", eElement));
					}
				}
        	}     	
        } catch(Exception e) { 
        	e.printStackTrace(); }
	}
	
	public void initialize() {			//�ҷ��°��� ���� ���� �߰��ϰ� ���� ���̺� �߰��Ѵ�.
		column_Address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
		column_Plant.setCellValueFactory(cellData->cellData.getValue().plantProperty());
		tableview_WPP.setItems(wppList);
	}
	
	public void CellDoubleClick(MouseEvent event) {
		if(event.getClickCount()>1 && tableview_WPP.getSelectionModel().getSelectedIndex()!=-1) {	//���� ���õǾ��ְ�, ����Ŭ�������� �̺�Ʈ
			Main.fcltyMngNo=fcltyMngNo.get(tableview_WPP.getSelectionModel().getSelectedIndex());
			System.out.println(Main.fcltyMngNo);
			application.MainController.Stage.close();
		}
	}
}

package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;

public class getAPIData {
	static Main main = new Main();
	
	static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null)
	        return null;
	    return nValue.getNodeValue();
	}
	
	public static void getPlantData(String url)
	{
        try {
        	// �ڽ��� static �żҵ带 ������ ��ü�� ���� : �̱��� ����
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	//�ٸ� Ŭ������ ��ü�� ������ ��ü�� �����ϸ� ���丮 ����
        	DocumentBuilder documentbuilder = factory.newDocumentBuilder();
        	//���ڿ��� InputStream���� ��ȯ
//        	InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
        	Document doc = documentbuilder.parse(url);
        	//sb�� �޸𸮿� ���� ��Ʈ�� element�� ����
//        	Element element = doc.getDocumentElement();
        	
        	NodeList nList = doc.getElementsByTagName("item");
        	for(int temp =0; temp<nList.getLength();temp++)
        	{
        		Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					if(getTagValue("lgldFullAddr", eElement).contains("��"))
					{
						//System.out.println("######################");
						System.out.println(eElement.getTextContent());
						//System.out.println("��  : " + getTagValue("addrName", eElement));
						//System.out.println("������  : " + getTagValue("fcltyMngNm", eElement));
						//System.out.println("�������ȣ : " + getTagValue("fcltyMngNo", eElement));
						//System.out.println("������  : " + getTagValue("lgldCode", eElement));
						//System.out.println("�ּ�  : " + getTagValue("lgldFullAddr", eElement));
						//System.out.println("��ȣ  : " + getTagValue("sujCode", eElement));
						//System.out.println("����ȣ��  : " + getTagValue("upprLgldCode", eElement));
					}
				}
        	}
        }catch(Exception e) {}
	}
	
    public static void main(String [] args) throws IOException {
    	getPlantData(main.URL_supplylgld);
    	
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat format2 = new SimpleDateFormat("HH");
    	SimpleDateFormat format3 = new SimpleDateFormat("mm");
    	
    	Date time = new Date();
    	
    	String time1 = format1.format(time);
    	String time2 = format2.format(time);
    	String time3 = format3.format(time);
    	System.out.println(time1);
    	System.out.println(time2);
    	System.out.println(time3);

    }
    
}
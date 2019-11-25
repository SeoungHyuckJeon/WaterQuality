package application;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class getsupplyLgldCode {
	static String ServiceKey = "jKq7bWPMdGPSHRToLGkotTffxNPQFoZ88H%2FjbH%2BSWSz836fHMXAaKVgKnvtxAHLVWZ0%2FqQzXJIViKEW2jOk1Og%3D%3D";
	static String uurl = "http://apis.data.go.kr/B500001/rwis/waterQuality/supplyLgldCode/list?serviceKey="
			+ ServiceKey +"&numOfRows=758&pageNo=1";
	
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
	
	public static void getPlantData()
	{
        try {
        	// �ڽ��� static �żҵ带 ������ ��ü�� ���� : �̱��� ����
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	//�ٸ� Ŭ������ ��ü�� ������ ��ü�� �����ϸ� ���丮 ����
        	DocumentBuilder documentbuilder = factory.newDocumentBuilder();
        	//���ڿ��� InputStream���� ��ȯ
//        	InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
        	Document doc = documentbuilder.parse(uurl);
        	//sb�� �޸𸮿� ���� ��Ʈ�� element�� ����
        	Element element = doc.getDocumentElement();
        	
        	NodeList nList = doc.getElementsByTagName("item");
        	for(int temp =0; temp<nList.getLength();temp++)
        	{
        		Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					if(getTagValue("lgldFullAddr", eElement).contains("��"))
					{
						System.out.println("######################");
						//System.out.println(eElement.getTextContent());
						//System.out.println("��  : " + getTagValue("addrName", eElement));
						//System.out.println("������  : " + getTagValue("fcltyMngNm", eElement));
						//System.out.println("�������ȣ : " + getTagValue("fcltyMngNo", eElement));
						//System.out.println("������  : " + getTagValue("lgldCode", eElement));
						System.out.println("�ּ�  : " + getTagValue("lgldFullAddr", eElement));
						//System.out.println("��ȣ  : " + getTagValue("sujCode", eElement));
						//System.out.println("����ȣ��  : " + getTagValue("upprLgldCode", eElement));
					}
				}
        	}
        }catch(Exception e) {}
	}
	
    public static void main(String [] args) throws IOException {
    	getPlantData();
    }
    
}
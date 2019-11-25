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
        	// 자신의 static 매소드를 가지고 객체를 생성 : 싱글톤 패턴
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	//다른 클래스의 객체를 가지고 객체를 생성하면 팩토리 패턴
        	DocumentBuilder documentbuilder = factory.newDocumentBuilder();
        	//문자열을 InputStream으로 변환
//        	InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
        	Document doc = documentbuilder.parse(uurl);
        	//sb를 메모리에 놓고 루트를 element에 저장
        	Element element = doc.getDocumentElement();
        	
        	NodeList nList = doc.getElementsByTagName("item");
        	for(int temp =0; temp<nList.getLength();temp++)
        	{
        		Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					if(getTagValue("lgldFullAddr", eElement).contains("시"))
					{
						System.out.println("######################");
						//System.out.println(eElement.getTextContent());
						//System.out.println("동  : " + getTagValue("addrName", eElement));
						//System.out.println("정수장  : " + getTagValue("fcltyMngNm", eElement));
						//System.out.println("정수장번호 : " + getTagValue("fcltyMngNo", eElement));
						//System.out.println("뭐지모름  : " + getTagValue("lgldCode", eElement));
						System.out.println("주소  : " + getTagValue("lgldFullAddr", eElement));
						//System.out.println("번호  : " + getTagValue("sujCode", eElement));
						//System.out.println("뭔번호냐  : " + getTagValue("upprLgldCode", eElement));
					}
				}
        	}
        }catch(Exception e) {}
	}
	
    public static void main(String [] args) throws IOException {
    	getPlantData();
    }
    
}
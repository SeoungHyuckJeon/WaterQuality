package application;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;

public class getsupplyLgldCode {
	static String ServiceKey = "jKq7bWPMdGPSHRToLGkotTffxNPQFoZ88H%2FjbH%2BSWSz836fHMXAaKVgKnvtxAHLVWZ0%2FqQzXJIViKEW2jOk1Og%3D%3D";
	static String uurl = "http://apis.data.go.kr/B500001/rwis/waterQuality/supplyLgldCode/list?serviceKey="
			+ ServiceKey +"&numOfRows=1&pageNo=1";
    public static void main(String [] args) throws IOException {
        URL url = new URL(uurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }

        String line;
        String result="";
        while ((line = rd.readLine()) != null) {
            result = result + line.trim();
        }
        rd.close();
        conn.disconnect();
        System.out.println(result);
    }
}
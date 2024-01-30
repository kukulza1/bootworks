package com.khit.board.jsonData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ApiJson {
    public static void main(String[] args){
    	try {
    		//지진해일 대피소 데이터 테스트
    		String servicekey ="EqSt11n1vZ1NXoCl1SpqpD3UhxBjZ85p1YJvwAqkikG%2F2GakHWmHGwUnwbkNkDeRedWBGEPsdpPYlwKzRbC9Ig%3D%3D";
    		String url = "https://apis.data.go.kr/1741000/TsunamiShelter3/getTsunamiShelter1List";
    		  url+="?serviceKey="+servicekey;
    		  url +="&pageNo=1";
    		  url +="&numOfRows=10";
    		  url +="&type=json";
    		//데이터를 받기위해서 url클래스의 객체 생성
    		URL requestURL = new URL(url);  		
    		System.out.println(url); 
    		//openConnection()을 이용한 연결
    		HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
    		conn.setRequestMethod("GET"); //대문자로 명시함
    		
    		//응답상태코드(200,403,404,500)
    		int status = conn.getResponseCode();
    		System.out.println(status);
    		
    		//버퍼:입출력 속도 향상을 위하여 데이터를 일시적으로 메모리 영역에 모아두는 것
    		//BufferedReader(보조스트림) : 기반스트림(생성자) - inputStreamReader
    		InputStreamReader isr = new InputStreamReader(conn.getInputStream());
    		BufferedReader br =new BufferedReader(isr);
    		
    		String responseText = "";
    		String line; //한행에있는데이터
    		while((line = br.readLine()) != null  ) {
    			responseText += line;
    		}
    		System.out.println(responseText);
    		br.close();
    		conn.disconnect();  		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
	}

}















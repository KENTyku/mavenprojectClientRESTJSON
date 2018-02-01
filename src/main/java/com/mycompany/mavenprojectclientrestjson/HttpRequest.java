/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojectclientrestjson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
//import org.apache.http.HttpEntity;

/**
 *
 * @author user
 */
public class HttpRequest {
   HttpRequest(String jtxt1){
       this.jtxt=jtxt1;
   }
    // HTTP GET request
    public String sendRequest() throws Exception {
// HTTP GET request
//        String url = "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&tagged=smile&site=stackoverflow";
//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        // optional default is GET
//        con.setRequestMethod("GET");
////        con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
//
//        //add request header
//        con.setRequestProperty("User-Agent", "Mozilla/5.0");
////        con.setRequestProperty("Content-Type","text/html; charset=utf-8");
////        con.setRequestProperty("Content-Type", "text/html; charset=windows-1251', true");
//
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream(),"cp866"));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//        }
//        in.close();
//
//        //print result
////        System.out.println(response.toString());
//        
//        return response.toString();

// HTTP POST request
        String url = "https://api.stackexchange.com";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("Host", "api.stackexchange.com");
        con.setRequestProperty("Cache-Control", "no-cache");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Referer", "https://api.stackexchange.com/docs/search");
        con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        
        
                
//        String urlParameters = "search?order=desc&sort=activity&tagged=smile&site=stackoverflow";
        String urlParameters = "{\"order\":\"desc\", \"sort\":\"activity\", \"tagged\":\""+this.jtxt+"\", \"site\":\"stackoverflow\"}";
      byte[] param=urlParameters.getBytes(StandardCharsets.UTF_8);
      int length = param.length;
//        String urlParameters = "{\"tagged\":\"smile\"}";
       
        // Send post request
        con.setDoOutput(true);
        
        //создание объекта для записы данных
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(urlParameters);
        wr.write(param);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL :: " + url);
        System.out.println("Post parameters :: " + urlParameters);
        System.out.println("Response Code :: " + responseCode);
//        con.setDoInput(true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();

        //print result
//        System.out.println(response.toString());
        return response.toString();

    }

}



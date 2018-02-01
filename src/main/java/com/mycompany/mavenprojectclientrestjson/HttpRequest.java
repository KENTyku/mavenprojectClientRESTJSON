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
//import org.apache.http.HttpEntity;

/**
 *
 * @author user
 */
public class HttpRequest {
    public static void metodGet() throws MalformedURLException, IOException{
        URL myUrl = new URL("http://pro-java.ru");
        HttpURLConnection myUrlCon =(HttpURLConnection) myUrl.openConnection();

        // Вывести метод запроса

        System.out.println("Метод запроса: " +
                            myUrlCon.getRequestMethod());

        // Вывести код ответа

        System.out.println("Ответное сообщение: " +
                            myUrlCon.getResponseMessage());
    }
    // HTTP GET request
    public static void sendGet() throws Exception {

//        String url = "http://www.google.com/search?q=mkyong";
//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        // optional default is GET
//        con.setRequestMethod("GET");
//
//        //add request header
//        con.setRequestProperty("User-Agent", "Mozilla/5.0");
//
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        System.out.println(response.toString());

// HTTP POST request
        String url = "https://api.stackexchange.com";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("Host", "api.stackexchange.com");
        con.setRequestProperty("Cache-Control", "no-cache");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Referer", "https://api.stackexchange.com/docs/search");
        con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        
        
                
        String urlParameters = "{\"order\":\"desc\", \"sort\":\"activity\", \"tagged\":\"smile\", \"site\":\"stackoverflow\"}";
//       
//        String urlParameters = "{\"tagged\":\"smile\"}";
       
        // Send post request
        con.setDoOutput(true);
        con.setDoInput(true);
        //создание объекта для записы данных
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL :: " + url);
        System.out.println("Post parameters :: " + urlParameters);
        System.out.println("Response Code :: " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}



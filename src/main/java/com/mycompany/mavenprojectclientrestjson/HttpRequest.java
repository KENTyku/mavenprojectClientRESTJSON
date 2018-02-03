/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojectclientrestjson;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
//import org.apache.http.HttpEntity;

/**
 *класс отправляет запросы POST на веб сервис, используя JSON
 * @author Yuri Tveritin
 */
public class HttpRequest {
    
//   HttpRequest(String jtxt1){
//       this.jtxt=jtxt1;
//   }
    // HTTP GET request
   public static String sendGET() throws Exception { 
    
        String url = "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&tagged=smile&site=stackoverflow";
        URL obj = new URL(url);
//        String url1=new String(url.getBytes("UTF8"),"ISO-8859-1");
//        URL obj = new URL(url1);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        // optional default is GET
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("Accept-Encoding", "gzip");
//         con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
        con.setRequestProperty("Cache-Control", "no-cache");        
        con.setRequestProperty("Connection", "keep-alive");
//        con.setRequestProperty("Cookie", "prov=fde7f77f-10e1-ef69-ab3d-9…ZkecvyaC8RTFypU32MXRUKwSRZ7DF");
        con.setRequestProperty("Host",	"api.stackexchange.com");
        con.setRequestProperty("Pragma", "no-cache");
        con.setRequestProperty("Referer", "https://api.stackexchange.com/docs/search");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 Firefox/58.0");
        con.setRequestProperty("X-Requested-With", "XMLHttpRequestt");
        
//        con.setRequestProperty("Content-Encoding", "gzip"); 
//        
//        con.setRequestProperty("Content-Language", "en-US");        
//        con.setRequestProperty("Content-Type","text/html; charset=utf-8");
//        con.setRequestProperty("Content-Type", "text/html; charset=ISO-8859-1', true");
        con.get
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        System.out.println("Encoding :"+con.getContentEncoding());
        System.out.println("Content :"+con.getContentType());
        InputStream inpstr=con.getInputStream();
        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream(),"cp866 "));
//                utf-8 cp866 Сp1251 KOI8-R
                new InputStreamReader(con.getInputStream()));
//        GZIPInputStream gzip = new GZIPInputStream(con.getInputStream());
//        while ((size = gzip.read(tempBuffer, 0, buffSize)) != -1) {
//                baos.write(tempBuffer, 0, size);
//            }
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();
//new String(parameter.getBytes("ISO-8859-1"),"UTF8")
        //print result
//        System.out.println(response.toString());
        Map <String, List<String>> h=con.getHeaderFields();
        System.out.println("Head==="+h.toString());
//        System.out.println(new String(response.toString().getBytes("ISO-8859-1"),"UTF8"));






        byte[] data = decompressResponse(response.toString().getBytes("UTF-8"));
        System.out.println(new String(data, "UTF8"));
        return response.toString();
   }

   // HTTP POST request
//    public String sendRequest() throws Exception {
//
//        String url = "https://api.stackexchange.com";
//        URL obj = new URL(url);
//        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//
//        //add reuqest header
//        con.setRequestMethod("POST");
//        con.setRequestProperty("User-Agent", "Mozilla/5.0");
//        con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setRequestProperty("Accept", "*/*");
//        con.setRequestProperty("Host", "api.stackexchange.com");
//        con.setRequestProperty("Cache-Control", "no-cache");
//        con.setRequestProperty("Connection", "keep-alive");
//        con.setRequestProperty("Referer", "https://api.stackexchange.com/docs/search");
//        con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
//        
//        
//                
////        String urlParameters = "search?order=desc&sort=activity&tagged=smile&site=stackoverflow";
//        String urlParameters = "{\"order\":\"desc\", \"sort\":\"activity\", \"tagged\":\""+this.jtxt+"\", \"site\":\"stackoverflow\"}";
//      byte[] param=urlParameters.getBytes(StandardCharsets.UTF_8);
//      int length = param.length;
////        String urlParameters = "{\"tagged\":\"smile\"}";
//       
//        // Send post request
//        con.setDoOutput(true);
//        
//        //создание объекта для записы данных
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
////        wr.writeBytes(urlParameters);
//        wr.write(param);
//        wr.flush();
//        wr.close();
//
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'POST' request to URL :: " + url);
//        System.out.println("Post parameters :: " + urlParameters);
//        System.out.println("Response Code :: " + responseCode);
////        con.setDoInput(true);
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
////        System.out.println(response.toString());
//        return response.toString();
//
//    }

   protected static byte[] decompressResponse(byte [] compressed) throws IOException {
        ByteArrayOutputStream baos = null;
        try {
            int size;
            ByteArrayInputStream memstream = new ByteArrayInputStream(compressed);
            GZIPInputStream gzip = new GZIPInputStream(memstream);
            final int buffSize = 8192;
            byte[] tempBuffer = new byte[buffSize];
            baos = new ByteArrayOutputStream();
            while ((size = gzip.read(tempBuffer, 0, buffSize)) != -1) {
                baos.write(tempBuffer, 0, size);
            }
            return baos.toByteArray();
        } finally {
            if (baos != null) {
                baos.close();
            }
        }
    }
}



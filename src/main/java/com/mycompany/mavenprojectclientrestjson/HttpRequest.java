/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojectclientrestjson;


import static com.mycompany.mavenprojectclientrestjson.JsonParser.parseJSON;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 *класс отправляет запросы GET на веб сервис, используя JSON
 * @author Yuri Tveritin
 * @version 1.0
 */
public class HttpRequest {
    

    // HTTP GET request
   public static ArrayList<String> sendGET(String tagget) throws Exception { 
    
        String url = "https://api.stackexchange.com/2.2/search?order=desc&sort="
                + "activity&tagged="+tagget+"&site=stackoverflow";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //устанавливаем заголовки запроса       
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
        con.setRequestProperty("Cache-Control", "no-cache");        
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Host",	"api.stackexchange.com");
        con.setRequestProperty("Pragma", "no-cache");
        con.setRequestProperty("Referer", "https://api.stackexchange.com/docs/search");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 Firefox/58.0");
        con.setRequestProperty("X-Requested-With", "XMLHttpRequestt");      
//       //выводим получивуюся строку запроса
//        System.out.println("\nRequest URL: " + url); 
//        //выводим код ответа сервиса
//        System.out.println("Response Code : " + con.getResponseCode());
//        //выводим залоговки ответа
//        Map <String, List<String>> h=con.getHeaderFields();
//        System.out.println("Head: "+h.toString());
        
       //обрабатываем сжатый ответ сервиса
        InputStream inpstr=con.getInputStream();           

        byte[] data = decompressResponse(inpstr);
        String decompresString=new String(data, "UTF8");
//        System.out.println(decompresString);//для теста выводим в консоль
        con.disconnect();
        inpstr.close();
        return parseJSON(decompresString);
   }
   /*
   *метод, разархивирующий входной поток данных
   */
   protected static byte[] decompressResponse(InputStream is) throws IOException {
        ByteArrayOutputStream baos = null;
        try {
            int size;
            GZIPInputStream gzip = new GZIPInputStream(is);
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



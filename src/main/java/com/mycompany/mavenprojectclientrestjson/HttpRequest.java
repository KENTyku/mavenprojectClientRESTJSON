/*
 * Use and copying for commercial purposes 
 * only with the author's permission 
 */
package com.mycompany.mavenprojectclientrestjson;

import static com.mycompany.mavenprojectclientrestjson.JsonParser.parseJSON;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;

/**
 *класс отправляет запросы по протоколу HTTP на веб сервис, используя JSON
 * @author Yuri Tveritin
 * @version 1.0
 * 
 */
public class HttpRequest {  
        
    /**
     * Устанавливает параметры HHTP запроса 
     *  
     * @return 
     */
    private TreeMap<String, String> getInit(){
        TreeMap<String, String> parameters = new TreeMap<>();
        parameters.put("Accept", "*/*");       
        parameters.put("Accept-Encoding", "gzip, deflate, br");
        parameters.put("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
        parameters.put("Cache-Control", "no-cache");        
        parameters.put("Connection", "keep-alive");
        parameters.put("Host",	"api.stackexchange.com");
        parameters.put("Pragma", "no-cache");
        parameters.put("Referer", "https://api.stackexchange.com/docs/search");
        parameters.put("User-Agent", "Mozilla/5.0 Firefox/58.0");
        parameters.put("X-Requested-With", "XMLHttpRequestt");
        return parameters;
    }
    
    /**
     * URL request
     * 
     * @param tagget тег,передаваемый в GET запрос 
     * @return  строка GET запроса
     */
    public String initURL(String tagget){
        return "https://api.stackexchange.com/2.2/search?order=desc&sort="
                + "activity&tagged="+tagget+"&site=stackoverflow";
    }
    
    /**
     * HTTP GET request
     * 
     * @param url строка запроса
     * @return массив объектов ObjSearch
     * @exception Exception проблемы с http соединением
     */
    public JsonParser.ObjSearch[] sendGET(String url) throws Exception {          
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        

        //устанавливаем заголовки запроса    
        Set<Map.Entry<String, String>> set = getInit().entrySet();
        for (Map.Entry<String, String> item: set){
            con.setRequestProperty(item.getKey(), item.getValue());
        }
       //обрабатываем сжатый ответ сервиса
        InputStream inpstr=con.getInputStream();           

        byte[] data = decompressResponse(inpstr);
        String decompresString=new String(data, "UTF8");
//        System.out.println(decompresString);//для теста выводим в консоль
        con.disconnect();
        inpstr.close();
        return parseJSON(decompresString);
   }
   /**
    * 
    * метод, разархивирующий входной поток данных
    * @param is поток данных
    * @return байтовый массив данных
    */
   private byte[] decompressResponse(InputStream is) throws IOException {
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



/*
 * Use and copying for commercial purposes 
 * only with the author's permission
 * @author Yuri Tveritin
 * @version 1.0
 */
package com.mycompany.mavenprojectclientrestjson;

import java.util.ArrayList;

/**
 * Класс управляет работой клиента по нажатию кнопкой Search
 */
public class Maker {
    private String tagget;
    /**
     * 
     * @param tagget 
     */
    Maker(String tagget){
        this.tagget=tagget;
    }
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    JsonParser.ObjSearch[] work() throws Exception{        
        HttpRequest httpr= new HttpRequest();
        //формируем строку http запроса на основании введенного тега
        String url=httpr.initURL(this.tagget);
        //выполняем сам запрос и получаем ответ в виде массива требуемых данных
        return httpr.sendGET(url);
    }  
    
}

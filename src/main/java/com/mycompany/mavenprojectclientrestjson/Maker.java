/*
 * Use and copying for commercial purposes 
 * only with the author's permission
 */
package com.mycompany.mavenprojectclientrestjson;


/**
 * 
 * @author Yuri Tveritin
 * 
 * @version 1.0
 * 
 * Класс управляет работой клиента по нажатию кнопкой Search
 */
public class Maker {
    private String tagget;
    /**
     * 
     * @param tagget тег для формирования GET запроса
     */
    Maker(String tagget){
        this.tagget=tagget;
    }
    
    /**
     * 
     * @return массив объектов ObjSearch
     * @throws Exception проблемы с http соединением
     */
    JsonParser.ObjSearch[] work() throws Exception{        
        HttpRequest httpr= new HttpRequest();
        //формируем строку http запроса на основании введенного тега
        String url=httpr.initURL(this.tagget);
        //выполняем сам запрос и получаем ответ в виде массива требуемых данных
        return httpr.sendGET(url);
    }  
    
}

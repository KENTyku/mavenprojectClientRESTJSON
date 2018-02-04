/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojectclientrestjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONValue;

/**
 *
 * @author user
 *
 * выполняет парсинг по тегам 
 * "accepted_answer_id" - id ответа 
 * "link": - ссылка на ответ
 * "title" - заголовок ответа
 * по тегам
 * "user_id":
 * "profile_image":  - картинка пользователя
 * "display_name" - имя пользователя
 * 
 * 
 **/
//package org.json; 


public class JsonParser {
    
    public static String parseJSON() {
        String jsonString = "{\"message\": \"Hi\", \"place\":{\"name\":\"World!\"}}";
        System.out.println(jsonString);
        Human list = JSON.parseObject(jsonString, Human.class);
        
        Object message = JSONPath.eval(list, "$.message");
        Object world = JSONPath.eval(list, "$.place.name");
//        JSONObject obj=(JSONObject)JSONValue.parse(jsonString); 
//        JSONArray ar=(JSONArray)JSONValue.parse(jsonString);
//        Object message=obj.
//        System.out.println(obj);
//        System.out.println(list.message);


       
        System.out.println("\n"+message + " " + world);
       return ("\n"+message + " " + world);
    }
    
}

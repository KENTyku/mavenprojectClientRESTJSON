/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojectclientrestjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import java.util.ArrayList;
import java.util.List;
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
    
    public static void parseJSON(String json) {
//        //тестовый парсинг
//        String jsonString = "{\"message\": \"Hi\", \"place\":{\"name\":\"World!\"}}";
//        System.out.println(jsonString);
//        Human list = JSON.parseObject(jsonString, Human.class);        
//        Object message = JSONPath.eval(list, "$.message");
//        Object world = JSONPath.eval(list, "$.place.name");       
//        System.out.println("\n"+message + " " + world);
        
        //рабочий парсинг
        Item item1=JSON.parseObject(json, Item.class);        
        Item items=(Item)JSONPath.eval(item1, "$.items");
        String id=(String)JSONPath.eval(item1, "$.items[1].owner.user_id");
        System.out.println(id);
//        System.out.println("Size array="+items.arrObjSearch.size());
        String quota_max1=(String)JSONPath.eval(item1, "$.quota_max");
        
//        List<Object> arr=JSON.parseArray(json, Item.class);
        
//       List<Object> items2=JSONPath.arrayAdd(item, json, values);
        
        
        System.out.println("TEST="+quota_max1);
//       return ("\n"+message + " " + world);
//         return "ttt";//заглушка метода
    }
    
    //класс автора
    public static class Owner{
        private String user_id;//id автора
        private String profile_image;//ссылка на аватар
        private String display_name;//имя автора

        /**
         * @return the user_id
         */
        public String getUser_id() {
            return user_id;
        }

        /**
         * @param user_id the user_id to set
         */
        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        /**
         * @return the profile_image
         */
        public String getProfile_image() {
            return profile_image;
        }

        /**
         * @param profile_image the profile_image to set
         */
        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        /**
         * @return the display_name
         */
        public String getDisplay_name() {
            return display_name;
        }

        /**
         * @param display_name the display_name to set
         */
        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }
        
    }
    
    //класс описывающий найденные объекты
    
    private static class ObjSearch{
        private Owner  owner;// автор
        private String is_answered;// признак ответа (true)
        private String accepted_answer_id;
        private String link;// ссылка на найденное содержимое (статья или ответ)
        private String title;//заголовок содержимого

        /**
         * @return the owner
         */
        public Owner getOwner() {
            return owner;
        }

        /**
         * @param owner the owner to set
         */
        public void setOwner(Owner owner) {
            this.owner = owner;
        }

        /**
         * @return the is_answered
         */
        public String getIs_answered() {
            return is_answered;
        }

        /**
         * @return the accepted_answer_id
         */
        public String getAccepted_answer_id() {
            return accepted_answer_id;
        }

        /**
         * @return the link
         */
        public String getLink() {
            return link;
        }

        /**
         * @return the title
         */
        public String getTitle() {
            return title;
        }
    }
    
    // класс описывающий объект содержащий массив найденных объектов
    private static class Item {        
        private ArrayList<ObjSearch> arrObjSearch;        
        private String quota_max;
        
        /**
         * @return the quota_max
         */
        public String getQuota_max() {
            return quota_max;
        }

        /**
         * @param quota_max the quota_max to set
         */
        public void setQuota_max(String quota_max) {
            this.quota_max = quota_max;
        }   

        /**
         * @return the arrObjSearch
         */
        public ArrayList<ObjSearch> getArrObjSearch() {
            return arrObjSearch;
        }

        /**
         * @param arrObjSearch the arrObjSearch to set
         */
        public void setArrObjSearch(ArrayList<ObjSearch> arrObjSearch) {
            this.arrObjSearch = arrObjSearch;
        }
                
    }    
}

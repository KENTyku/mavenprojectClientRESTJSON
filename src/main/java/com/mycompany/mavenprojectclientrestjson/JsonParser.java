/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojectclientrestjson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Yuri Tveritin
 * @version 1.0
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

public class JsonParser {
    
    public static ArrayList<String> parseJSON(String json) {
         ArrayList<String> info= new ArrayList();
        //рабочий парсинг 2
        ObjectMapper objectMapper = new ObjectMapper();
        try {
           Item item = objectMapper.readValue(json, Item.class);
           ObjSearch[] arr=item.getItems();
           int i=1;          
           for (ObjSearch obS: arr){               
               info.add("\n"+i);
               info.add("\n"+obS.title);
               info.add("\nlink: "+obS.link);
               info.add("\n"+obS.getOwner().display_name);
               info.add("\nimage author: "+obS.getOwner().profile_image);
               i++;
           }           
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }
    
    //класс автора
    private static class Owner{
        private String reputation;
        private String user_id;//id автора
        private String user_type;
        private String accept_rate;
        private String profile_image;//ссылка на аватар
        private String display_name;//имя автора
        private String link;

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

        /**
         * @return the reputation
         */
        public String getReputation() {
            return reputation;
        }

        /**
         * @param reputation the reputation to set
         */
        public void setReputation(String reputation) {
            this.reputation = reputation;
        }

        /**
         * @return the user_type
         */
        public String getUser_type() {
            return user_type;
        }

        /**
         * @param user_type the user_type to set
         */
        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        /**
         * @return the link
         */
        public String getLink() {
            return link;
        }

        /**
         * @param link the link to set
         */
        public void setLink(String link) {
            this.link = link;
        }

        /**
         * @return the accept_rate
         */
        public String getAccept_rate() {
            return accept_rate;
        }

        /**
         * @param accept_rate the accept_rate to set
         */
        public void setAccept_rate(String accept_rate) {
            this.accept_rate = accept_rate;
        }
        
    }
    
    //класс описывающий найденные объекты        
    private static class ObjSearch{
        private String[]  tags;
        private Owner  owner;// автор
        private String is_answered;// признак ответа (true)
        private String view_count;
        private String closed_date;
        private String accepted_answer_id;
        private String answer_count;
        private String score;
        private String last_activity_date;
        private String creation_date;
        private String last_edit_date;
        private String question_id;
        private String link;// ссылка на найденное содержимое (статья или ответ)
        private String closed_reason;
        private String title;//заголовок содержимого
        private String protected_date;

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

        /**
         * @return the tags
         */
        public String[] getTags() {
            return tags;
        }

        /**
         * @param tags the tags to set
         */
        public void setTags(String[] tags) {
            this.tags=tags;
        }

        /**
         * @param is_answered the is_answered to set
         */
        public void setIs_answered(String is_answered) {
            this.is_answered = is_answered;
        }

        /**
         * @return the view_count
         */
        public String getView_count() {
            return view_count;
        }

        /**
         * @param view_count the view_count to set
         */
        public void setView_count(String view_count) {
            this.view_count = view_count;
        }

        /**
         * @param accepted_answer_id the accepted_answer_id to set
         */
        public void setAccepted_answer_id(String accepted_answer_id) {
            this.accepted_answer_id = accepted_answer_id;
        }

        /**
         * @return the answer_count
         */
        public String getAnswer_count() {
            return answer_count;
        }

        /**
         * @param answer_count the answer_count to set
         */
        public void setAnswer_count(String answer_count) {
            this.answer_count = answer_count;
        }

        /**
         * @return the score
         */
        public String getScore() {
            return score;
        }

        /**
         * @param score the score to set
         */
        public void setScore(String score) {
            this.score = score;
        }

        /**
         * @return the last_activity_date
         */
        public String getLast_activity_date() {
            return last_activity_date;
        }

        /**
         * @param last_activity_date the last_activity_date to set
         */
        public void setLast_activity_date(String last_activity_date) {
            this.last_activity_date = last_activity_date;
        }

        /**
         * @return the creation_date
         */
        public String getCreation_date() {
            return creation_date;
        }

        /**
         * @param creation_date the creation_date to set
         */
        public void setCreation_date(String creation_date) {
            this.creation_date = creation_date;
        }

        /**
         * @return the question_id
         */
        public String getQuestion_id() {
            return question_id;
        }

        /**
         * @param question_id the question_id to set
         */
        public void setQuestion_id(String question_id) {
            this.question_id = question_id;
        }

        /**
         * @param link the link to set
         */
        public void setLink(String link) {
            this.link = link;
        }

        /**
         * @param title the title to set
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return the last_edit_date
         */
        public String getLast_edit_date() {
            return last_edit_date;
        }

        /**
         * @param last_edit_date the last_edit_date to set
         */
        public void setLast_edit_date(String last_edit_date) {
            this.last_edit_date = last_edit_date;
        }

        /**
         * @return the closed_date
         */
        public String getClosed_date() {
            return closed_date;
        }

        /**
         * @param closed_date the closed_date to set
         */
        public void setClosed_date(String closed_date) {
            this.closed_date = closed_date;
        }

        /**
         * @return the closed_reason
         */
        public String getClosed_reason() {
            return closed_reason;
        }

        /**
         * @param closed_reason the closed_reason to set
         */
        public void setClosed_reason(String closed_reason) {
            this.closed_reason = closed_reason;
        }

        /**
         * @return the protected_date
         */
        public String getProtected_date() {
            return protected_date;
        }

        /**
         * @param protected_date the protected_date to set
         */
        public void setProtected_date(String protected_date) {
            this.protected_date = protected_date;
        }
      
    }
    
    // класс описывающий объект содержащий массив найденных объектов
    private static class Item {        
        private ObjSearch[] items;        
        private String has_more;
        private String quota_max;
        private String quota_remaining;
        
        
        
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
         * @return the has_more
         */
        public String getHas_more() {
            return has_more;
        }

        /**
         * @param has_more the has_more to set
         */
        public void setHas_more(String has_more) {
            this.has_more = has_more;
        }

        /**
         * @return the quota_remaining
         */
        public String getQuota_remaining() {
            return quota_remaining;
        }

        /**
         * @param quota_remaining the quota_remaining to set
         */
        public void setQuota_remaining(String quota_remaining) {
            this.quota_remaining = quota_remaining;
        }

        /**
         * @return the items
         */
        public ObjSearch[] getItems() {
            return items;
        }

        /**
         * @param items the items to set
         */
        public void setItems(ObjSearch[] items) {
            this.items = items;
        }

            
    }    
}

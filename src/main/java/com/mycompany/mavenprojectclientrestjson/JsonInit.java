/*
 * Use and copying for commercial purposes 
 * only with the author's permission
 */
package com.mycompany.mavenprojectclientrestjson;

import java.util.TreeMap;

/**
 * 
 * @author Yuri Tveritin
 * @version 1.0
 * 
 *  хранит в отображении  TreeMap пары ключ значение(используется для хранения значений,
 * передаваемых в строку запроса; для хранения значений, передаваемых 
 * в залоговок запроса)
 * 
 * 
 * setStringJSON получает ключ и значение для элемента JSON
 * getJSON возвращает данные в формате JSON
 * в разработке!!!!!!!
 */

public class JsonInit {
    private String tagget;
    private TreeMap<String, String> parameters = new TreeMap<>();
    
    

    /**
     * @param tagget the tagget to set
     */
    public void setTagget(String tagget) {
        this.tagget = tagget;
    }
}

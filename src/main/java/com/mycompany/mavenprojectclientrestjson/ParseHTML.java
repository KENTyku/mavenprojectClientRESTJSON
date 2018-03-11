/*
 * Use and copying for commercial purposes 
 * only with the author's permission
 */

package com.mycompany.mavenprojectclientrestjson;

//import java.util.Map;
//import java.util.Set;
//import java.util.TreeMap;

/**
 * 
 * @author Yuri Tveritin * 
 * @version 1.0
 * 
 * Класс парсит текст из строки
 */
public class ParseHTML {

//    private TreeMap<String, Integer> keywordsList;   

    /**
     * Метод для подсчета упоминаний личностей по ключевым словам на html страницах
     * @param pageUrl
     * @return 
     */

    public static String parseHtml(String pageUrl) {
        String text = "";         
        String content=new Downloader().download(pageUrl);
        //ищем кусок кода с постом
        if(content.contains("<div class=\"post-text\" itemprop=\"text\">")){//если содержит строку
            int indStart=content.indexOf("<div class=\"post-text\" itemprop=\""
                    + "text\">");//устанавливаем начальный индекс для подстроки
            String subString=content.substring(indStart);//выделяем новую 
            int indEnd=subString.indexOf("</div>")+6;//определяем конечный индекс
            subString=subString.substring(0, indEnd);//выделяем полезную подстроку поста  
            //ищем в посте вставки кода для их дальнейшего разбора
            String[] codeArea=subString.split("<pre><code>");
            String[] codeAreaTemp=new String[codeArea.length];//временный массив
            //обрабатываем найденные вставки кода
            for (int i=1;i<codeArea.length;i++){//пропускаем первый кусок i=0
                int indEn=codeArea[i].indexOf("</code></pre>");
                codeAreaTemp[i]=codeArea[i].substring(0, indEn);//полезная подстрока кода                               
                String[] splitCode=codeAreaTemp[i].split("\n");
                
                //разбиваем на подстроки и заменяем перенос \n на <br/> для 
                //вставки в html блок
                String textCode="";
                for (int j=0; j<splitCode.length;j++){                   
                    textCode=textCode+splitCode[j]+"<br/>";
                }
                codeAreaTemp[i]=textCode; 
                //собираем вставку кода
                codeArea[i]=codeAreaTemp[i]+codeArea[i].substring(indEn+13);                
            }
            //собираем строку полностью
            subString="";
            for (int i=0;i<codeArea.length;i++){
                subString=subString+codeArea[i];
            }
            text=subString;
                   
        } 
    return text;
    }    
}

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
        if(content.contains("<td class=\"postcell\">")){
            int indStart=content.indexOf("<td class=\"postcell\">");
            String subString=content.substring(indStart);
            indStart=subString.indexOf("<td class=\"postcell\">");
            int indEnd=subString.indexOf("</td>");
            subString=subString.substring(indStart, indEnd+5);
            String[] split=subString.split("<p>");
            for(int i=1; i<split.length; i++){
//                System.out.println(split[i]);
                int ind=split[i].indexOf("</p>");
//                System.out.println(ind);
                if (ind!=-1){
                    text=text+split[i].substring(0, ind)+"\n";
                }
                                
            }
            
//            text="<html> slkjsldjf lskjdflsjd slkdjflsjdf sldjfljdf"
//                    + "slkdjlskjd slkdjflsdjflsdj lskdjflsdjf slkdfj"
//                    + "sldkjflskjdflsjf lskjdflsdkjf lskdjflskjdf lskjdflsjk"
//                    + "sldkjflskjdflsjf lskjdflsdkjf lskdjflskjdf lskjdflsjk"
//                    + "sldkjflskjdflsjf lskjdflsdkjf lskdjflskjdf lskjdflsjk"
//                    + "sldkjflskjdflsjf lskjdflsdkjf lskdjflskjdf lskjdflsjk"
//                    + "sldkjflskjdflsjf lskjdflsdkjf lskdjflskjdf lskjdflsjk"
//                    + "sldkjflskjdflsjf lskjdflsdkjf lskdjflskjdf lskjdflsjk"
//                    + "sldkjflskjdflsjf lskjdflsdkjf lskdjflskjdf lskjdflsjk"
//                    + "sldkjflskjdflsjf lskjdflsdkjf lskdjflskjdf lskjdflsjk"
//                    + "sldkjflskjdflsjf lskjdflsdkjf lskdjflskjdf lskjdflsjk"
//                    + "sldkjflskjdflsjf lskjdflsdkjf lskdjflskjdf lskjdflsjk"
//                    + "sldkjflskjdflsjf lskjdflsdkjf lskdjflskjdf lskjdflsjk"
//                    
//                    + "</html>";
            
//            System.out.println(text);                    
        } 
    return text;
    }    
}

//                    String[] splitContent = content.split("\n");
                    
//                    for (String line:splitContent) {  
//                        if(line.contains("twitter:description")){
//                            int indStart=line.indexOf("content");
//                            String text=line.substring(indStart);
////                            String text=line.substring(0, 0)
////                            String[] splitLine=line.split(" ");
////                            for(String piece :splitLine){
////                                if(piece.equals(keyword)) rank++;                   
//                            }
//                        }
           
                   
//                }                
//                System.out.println(personId+" "+pageId+" "+rank);  
//                this.personsPageRank.put(personId + " " + pageId, rank);                
//            }
//        }
//        System.out.println("Подсчет рейтинга закончен");
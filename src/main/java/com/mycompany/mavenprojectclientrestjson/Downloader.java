/*
 * Use and copying for commercial purposes 
 * only with the author's permission
 */
package com.mycompany.mavenprojectclientrestjson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 * 
 * @author Yuri Tveritin
 * 
 * @version 1.0
 * 
 * Класс скачивания веб страницы по адресу
 */
public class Downloader {

    private BufferedReader reader;
    private String line = "";
    private String addonLine;
    private URL site;

    /**
     * метод, осуществляющий загрузку данных из Интернета
     * @param url адрес загружаемой страницы
     * @return
     */

    public String download(String url) {
        try {
            this.site = new URL(url);
            this.reader = new BufferedReader(new InputStreamReader(this.site.openStream()));
            while ((this.addonLine = this.reader.readLine()) != null) {
                this.line += this.addonLine + " ";
            }
            this.reader.close();
        } catch (IOException e) {
            System.out.println("страница "+url+" не существует. Ошибка 404");                             
//            JOptionPane.showMessageDialog(frame, "Error","страница "+url+" не существует. Ошибка 404",JOptionPane.WARNING_MESSAGE);
            this.line="";
        } finally {
            try {
                this.reader.close();
            } catch (Exception e) {
            }
        }
        return this.line;
    }

}

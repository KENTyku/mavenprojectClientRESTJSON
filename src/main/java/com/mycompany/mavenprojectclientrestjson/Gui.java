/*
 * Use and copying for commercial purposes 
 * only with the author's permission
 */
package com.mycompany.mavenprojectclientrestjson;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Класс создания формы
 * @author Yuri Tveritin
 * @version 1.0
 */
public class Gui extends JFrame {
    //объявление переменных
    JFrame frame;
    JPanel jpSearch;//панель поиска
    JPanel jpList;//панель под вывод результатов
    JScrollPane jsp_jpList;//прокрутка панели результатов    

    /**
     * Create Form
     */
    public void createGUI(){ 
        //инициализация компонентов
        frame = new JFrame("Поиск статей и ответов");        
        JButton btnSearch=new JButton("Search");       
        JTextField jtfSearch=new JTextField("smile");         
        jpSearch=new JPanel();
        jpList=new JPanel();
        jsp_jpList=new JScrollPane(jpList);

        //установка настроек компонентов
        frame.setBounds(0, 0, 400, 700);//положение и размер созадваемого фрейма
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        frame.setLayout(new BorderLayout());//установка компоновщика по сторонам
        //света
        jpList.setLayout(new BoxLayout(jpList, BoxLayout.Y_AXIS));//компоновщик 
        //размещающий объекты по вертикали на панели резултатов
        //добавление элементов на форму
        frame.add(jpSearch, BorderLayout.NORTH);
        frame.add(jsp_jpList, BorderLayout.CENTER);

        //добавление объектов на панель       
        jpSearch.add(jtfSearch);
        jpSearch.add(btnSearch); 
           
        frame.setVisible(true);
        
        //обработка события нажатия кнопки btnSearch
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jpList.removeAll();
                String tagget=jtfSearch.getText();//читаем из текстового поля
                if (!tagget.equals("")) {//проверка то что поле не пустое
                    Maker makeSearch=new Maker(tagget);//передаем в Maker тег
                    try { 
                        //выполняем обработчик и отображаем в текстовом 
                        //поле результат
                        JsonParser.ObjSearch[] info=makeSearch.work();
                        
                        int count=1;
                        
                        for(JsonParser.ObjSearch infoItem:info){
                            //инициализируем объекты формы для элемента infoItem
                            JLabel jlNum=new JLabel(count+"."); 
                            JLabel jlTitle=new JLabel(infoItem.getTitle());
                            JLabel jlLink=new JLabel(infoItem.getLink());
                            JLabel jlAvatar=new JLabel();
                            JLabel jlNameOwner=new JLabel(infoItem.getOwner().getDisplay_name());
                            
                            //ссылка на аватар
                            String url=infoItem.getOwner().getProfile_image();
                            //Установка иконки на jlAvatar
                            jlAvatar.setIcon(new javax.swing.JLabel() {
                                public javax.swing.Icon getIcon() {
                                    try {
                                        return new javax.swing.ImageIcon(
                                            new java.net.URL(url)
                                        );
                                    } catch (java.net.MalformedURLException e) {
                                    }
                                    return null;
                                }
                            }.getIcon());
                            //добавление объектов на панель
                            jpList.add(jlNum);
                            jpList.add(jlTitle);
                            jpList.add(jlLink);
                            jpList.add(jlAvatar);
                            jpList.add(jlNameOwner);
                            
                            jpList.repaint();//перерисовка панели
                            jsp_jpList.revalidate();//обновление скролинга
                            count++;
                        }        
                    } catch (Exception ex) {
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }        
                }
            }
        });
    
    }    
}
    

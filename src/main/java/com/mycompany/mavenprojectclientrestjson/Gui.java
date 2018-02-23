/*
 * Use and copying for commercial purposes 
 * only with the author's permission
 * @author Yuri Tveritin
 * @version 1.0
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
 *
 * @author user
 */
public class Gui extends JFrame {
    JFrame frame;
    JPanel jpSearch;
    JPanel jpList;
    JScrollPane jsp_jpList;
    JScrollPane jspJTA;
    public void createGUI(){ 
        frame = new JFrame("Поиск статей и ответов");
        frame.setBounds(0, 0, 400, 700);//положение и размер созадваемого фрейма
        JButton btnSearch=new JButton("Search");       
        JTextField jtfSearch=new JTextField("smile");
        JTextArea jTextArea=new JTextArea("Results");
//        jTextArea.setRows(8);
//        jTextArea.setPreferredSize(new Dimension (250,200));
        
        
        jpSearch=new JPanel();//панель поиска
        jpList=new JPanel();//панель под вывод результатов
        jsp_jpList=new JScrollPane(jpList);//прокрутка панели результатов
        jspJTA=new JScrollPane(jTextArea);        
//        jpList.setLayout(new BoxLayout(jpList, BoxLayout.Y_AXIS));//компоновщик 
        //размещающий объекты по вертикали на панели резултатов
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        frame.setLayout(new BorderLayout());
        frame.add(jpSearch, BorderLayout.NORTH);
        frame.add(jsp_jpList, BorderLayout.CENTER);
//        frame.setPreferredSize(new Dimension(250, 200)); 

        jpList.setLayout(new BoxLayout(jpList, BoxLayout.Y_AXIS));
        jpSearch.add(jtfSearch);
        jpSearch.add(btnSearch);
//        jpList.add(jspJTA);        
   
        
        frame.setVisible(true);
        
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jpList.removeAll();
                        String tagget=jtfSearch.getText();//читаем из текстового поля
                if (!tagget.equals("tag name")&!tagget.equals("")) {
//                    List jlabels=new ArrayList(); 
//                    jTextArea.setText(null);
                    Maker makeSearch=new Maker(tagget);//отправляем в обработчик тег
                    try {            
                        JsonParser.ObjSearch[] info=makeSearch.work();//выполняем обработчик
                        //отображаем в текстовом поле результат
                        int i=1;
                        
                        for(JsonParser.ObjSearch infoItem:info){
                            JLabel jlNum=new JLabel(i+")."); 
                            JLabel jlTitle=new JLabel(infoItem.getTitle());
                            JLabel jlLink=new JLabel(infoItem.getLink());
                            JLabel jlAvatar=new JLabel();
                            JLabel jlNameOwner=new JLabel(infoItem.getOwner().getDisplay_name());
                            
                            
                            String url=infoItem.getOwner().getProfile_image();//ссылка на аватар//                                                
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
//                            jlabels.add(jlAvatar);
//                            jpList.add(jlAvatar, BoxLayout.Y_AXIS);
                            jpList.add(jlNum);
                            jpList.add(jlTitle);
                            jpList.add(jlLink);
                            jpList.add(jlAvatar);
                            jpList.add(jlNameOwner);
                            jpList.repaint();
                            jsp_jpList.revalidate();
                            i++;
                        }        
                    } catch (Exception ex) {
                        Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
                    }        
                }
            }
        });
    
    }    
}
    

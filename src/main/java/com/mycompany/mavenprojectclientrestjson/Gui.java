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
    public static void createGUI(){ 
        JFrame frame = new JFrame("Поиск статей и ответов");
        frame.setBounds(0, 0, 400, 700);//положение и размер созадваемого фрейма
        JButton btnSearch=new JButton("Search");       
        JTextField jtfSearch=new JTextField("smile");
        JTextArea jTextArea=new JTextArea("Results");
//        jTextArea.setRows(8);
//        jTextArea.setPreferredSize(new Dimension (250,200));
        
        JLabel jl=new JLabel("TestTest");
        JLabel jl1=new JLabel("TestTest2");
        JPanel jpSearch=new JPanel();//панель поиска
        JPanel jpList=new JPanel();//панель под вывод результатов
        JScrollPane jsp_jpList=new JScrollPane(jpList);//прокрутка панели результатов
        JScrollPane jspJTA=new JScrollPane(jTextArea);        
//        jpList.setLayout(new BoxLayout(jpList, BoxLayout.Y_AXIS));//компоновщик 
        //размещающий объекты по вертикали на панели резултатов
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        frame.setLayout(new BorderLayout());
        frame.add(jpSearch, BorderLayout.NORTH);
        frame.add(jpList, BorderLayout.CENTER);
//        frame.setPreferredSize(new Dimension(250, 200)); 

        jpList.setLayout(new BoxLayout(jpList, BoxLayout.Y_AXIS));
        jpSearch.add(jtfSearch);
        jpSearch.add(btnSearch);
        jpList.add(jspJTA);        
        jpList.add(jl);
        jpList.add(jl1);
        
        frame.setVisible(true);
        
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                        String tagget=jtfSearch.getText();//читаем из текстового поля
                if (!tagget.equals("tag name")&!tagget.equals("")) {
                    List jlabels=new ArrayList(); 
                    jTextArea.setText(null);
                    Maker makeSearch=new Maker(tagget);//отправляем в обработчик тег
                    try {            
                        ArrayList<String> info=makeSearch.work();//выполняем обработчик
                        //отображаем в текстовом поле результат
                        for(String infoline:info){                    
                            jTextArea.append(infoline);
                            JLabel jlabel=new JLabel();
                            jlabel.setIcon(new javax.swing.JLabel() {
                                public javax.swing.Icon getIcon() {
                                    try {
                                        return new javax.swing.ImageIcon(
                                            new java.net.URL("https://www.gravatar.com/avatar/955390e344937bdd0e363ad2ab470545?s=128&d=identicon&r=PG&f=1")
                                        );
                                    } catch (java.net.MalformedURLException e) {
                                    }
                                    return null;
                                }
                            }.getIcon());
                            jlabels.add(jlabel);
                            jpList.repaint();
                            jsp_jpList.revalidate();
                        }        
                    } catch (Exception ex) {
                        Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
                    }        
                }
            }
        });
    
    }    
}
    

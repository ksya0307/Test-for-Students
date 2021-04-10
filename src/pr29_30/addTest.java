package pr29_30;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class addTest extends JFrame {
    private JTextField test_name;
    private JTextField your_q;
    private JTextField count_q;
    private JLabel lbl_name_test;
    private JLabel lbl_q;
    private JLabel lbl_count_q;
    private JButton next_question;
    private JButton addTest;
    private JPanel contentPane;
    private JButton save_data_about_q;
    private JPanel here_for_options;
    private JButton addNameTest;

    public addTest(){
        int x = 20;
     int y = 25;
     int interval = 25;
     int width = 300;
     int height = 25;
     JFrame frame = new JFrame("Добавление теста");
     frame.setBounds(400,400,(x*3)+width,400);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setLayout(new GridLayout(0,1));
     frame.setVisible(true);
     this.setContentPane(this.contentPane);
     this.getContentPane().setLayout((LayoutManager)null);

     lbl_name_test = new JLabel("Напишите название теста");
     lbl_name_test.setBounds(x,y,width,height);
     this.getContentPane().add(this.lbl_name_test);
     frame.add(lbl_name_test);
     y=y+height+interval;

     test_name = new JTextField();
     test_name.setBounds(x,y,width,height);
     this.getContentPane().add(this.test_name);
     frame.add(test_name);
     y=y+height+interval;

     addNameTest = new JButton("Сохранить");
     addNameTest.setBounds(x,y,width,height);
     this.getContentPane().add(this.addNameTest);
     frame.add(addNameTest);
     y=y+height+interval;

     lbl_q = new JLabel("Напишите ваш вопрос");
     lbl_q.setBounds(x,y,width,height);
     this.getContentPane().add(this.lbl_q);
     frame.add(lbl_q);
     y=y+height+interval;

     your_q = new JTextField();
     your_q.setBounds(x,y,width,height);
     this.getContentPane().add(this.your_q);
     frame.add(your_q);
     y=y+height+interval;

     lbl_count_q = new JLabel("Напишите сколько вариантов ответов");
     lbl_count_q.setBounds(x,y,width,height);
     this.getContentPane().add(this.lbl_count_q);
     frame.add(lbl_count_q);
     y=y+height+interval;

     count_q = new JTextField();
     count_q.setBounds(x,y,width,height);
     this.getContentPane().add(this.count_q);
     frame.add(count_q);
     y=y+height+interval;

     save_data_about_q = new JButton("Сформировать поля для вопросов");
     save_data_about_q.setBounds(x,y,width,height);
     this.getContentPane().add(this.save_data_about_q);
     frame.add(save_data_about_q);
     y=y+height+interval;

     here_for_options = new JPanel();

     here_for_options.setLayout(new GridLayout());
     contentPane.add(here_for_options);


        save_data_about_q.addActionListener(e -> {

            int amount_questions;
            here_for_options.removeAll();

            amount_questions = Integer.parseInt(count_q.getText());
            List<JTextField> options = new ArrayList<>();

            for(int i=0;i<amount_questions;i++){
                JTextField option = new JTextField();
                here_for_options.add(option);
                options.add(option);
                frame.add(option);
            }
        });

        addNameTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Test addname  = new Test();
                addname.InsertTest(test_name.getText());
            }
        });
    }
}

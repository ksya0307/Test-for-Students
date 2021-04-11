package pr29_30;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class addTest extends JFrame {
    private JTextField test_name;
    private JTextField your_q;
    private JTextField count_options;
    private JLabel lbl_name_test;
    private JLabel lbl_q;
    private JLabel lbl_option;
    private JButton next_question;
    private JButton addTest;
    private JPanel contentPane;
    private JButton save_data_about_q;
    private JPanel here_for_options;
    private JButton addNameTest;
    private JButton add_question;
    private JTextField amount_q;
    private JLabel amount_lbl;
    private JCheckBox YesOrNo;
    private JLabel warning;
    public int new_id_test;

    public addTest(){
        int x = 20;
     int y = 25;
     int interval = 25;
     int width = 300;
     int height = 25;
     JFrame frame = new JFrame("Добавление теста");
     frame.setBounds(400,400,(x*4)+width,400);
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

     amount_lbl = new JLabel("Сколько будет вопросов?");
        amount_lbl.setBounds(x,y,width,height);
        this.getContentPane().add(this.amount_lbl);
        frame.add(amount_lbl);
        y=y+height+interval;

        amount_q = new JTextField();
        amount_q.setBounds(x,y,width,height);
        this.getContentPane().add(this.amount_q);
        frame.add(amount_q);
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




     add_question = new JButton("Добавить вопрос к тесту");
     add_question.setBounds(x,y,width,height);
     this.getContentPane().add(this.add_question);
     frame.add(add_question);
     y=y+height+interval;
     add_question.setEnabled(false);

    warning = new JLabel("ВНИМАТЕЛЬНО ЗАПОЛНЯЙТЕ СЛЕДУЮЩУЮ ИНФОРМАЦИЮ");
    warning.setBounds(x,y,width,height);
    this.getContentPane().add(this.warning);
    frame.add(warning);
    y=y+height+interval;

     lbl_option = new JLabel("Напишите вариант ответа:");
     lbl_option.setBounds(x,y,width,height);
     this.getContentPane().add(this.lbl_option);
     frame.add(lbl_option);
     y=y+height+interval;

     count_options = new JTextField();
     count_options.setBounds(x,y,width,height);
     this.getContentPane().add(this.count_options);
     frame.add(count_options);
     y=y+height+interval;


     YesOrNo= new JCheckBox("Отметьте, если этот вариант верный");
     YesOrNo.setBounds(x+100,y,width,height);
     this.getContentPane().add(this.YesOrNo);
     frame.add(YesOrNo);
     y=y+height+interval;



     save_data_about_q = new JButton("Добавить еще вариант");
     save_data_about_q.setBounds(x,y,width,height);
     this.getContentPane().add(this.save_data_about_q);
     save_data_about_q.setEnabled(false);
     frame.add(save_data_about_q);
     y=y+height+interval;

     here_for_options = new JPanel();

     here_for_options.setLayout(new GridLayout());
     contentPane.add(here_for_options);


        save_data_about_q.addActionListener(e -> {

            int amount_questions;
            here_for_options.removeAll();

            amount_questions = Integer.parseInt(count_options.getText());
            List<JTextField> options = new ArrayList<>();

            for(int i=0;i<amount_questions;i++){
                JTextField option = new JTextField();
                here_for_options.add(option);
                options.add(option);
                frame.add(option);
            }
        });

        addNameTest.addActionListener(e -> {
            Test addname  = new Test();
            addname.InsertTest(test_name.getText(), Integer.parseInt(amount_q.getText()));
            addNameTest.setEnabled(false);
            new_id_test = addname.getIdTest(test_name.getText());
            add_question.setEnabled(true);

        });
        add_question.addActionListener(e -> {
            Question addQtoTest = new Question();
            addQtoTest.InsertQuestionTest(your_q.getText(),new_id_test);
        });
    }
}

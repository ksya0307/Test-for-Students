package pr29_30;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainMenu extends JFrame
{
    public int id_test;
    private JComboBox tests;
    private JPanel contentPane;
    private JButton getResults;
    private JButton addTest;
    private JButton updateTest;
    public  JLabel textForChoose;
    public int id_user;
    public  AdminMainMenu(int id_user) throws ClassNotFoundException {
        int x = 20;
        int y = 25;
        int interval = 25;
        int width = 300;
        int height = 25;
        JFrame frame = new JFrame("Админка");
        frame.setBounds(400,400,(x*3)+width,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        this.setContentPane(this.contentPane);
        this.getContentPane().setLayout((LayoutManager)null);

        textForChoose = new JLabel("Выберите действие: ");
        textForChoose.setBounds(x, y, width, height);
        this.getContentPane().add(this.textForChoose);
        frame.add(textForChoose);
        y=y+height+interval;
        //Кнопка просмотра результатов
        getResults = new JButton("Просмотр результатов пользователей");
        getResults.setBounds(x,y,width,height);
        this.getContentPane().add(this.getResults);
        frame.add(getResults);

        //Еще не готово, тут открывается форма просмотра результата
        getResults.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckResults resForm = null;
                try {
                    resForm = new CheckResults();
                    setVisible(false);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        y=y+height+interval;
        //Кнопка добавления теста
        addTest = new JButton("Добавление теста");
        addTest.setBounds(x,y,width,height);
        this.getContentPane().add(this.addTest);
        frame.add(addTest);

        //Еще не готово, тут открывается форма добавления теста
        addTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thetest test = null;
                try {
                    id_test = tests.getSelectedIndex()+1;
                    test = new thetest(id_test);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
//                test.id_test = id_test;
                System.out.println(id_test);
            }
        });
        y=y+height+interval;
        //Кнопка изменения теста
        updateTest = new JButton("Изменение теста");
        updateTest.setBounds(x,y,width,height);
        this.getContentPane().add(this.updateTest);
        frame.add(updateTest);

        //Еще не готово, тут открывается форма добавления теста
        updateTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thetest test = null;
                try {
                    id_test = tests.getSelectedIndex()+1;
                    test = new thetest(id_test);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
//                test.id_test = id_test;
                System.out.println(id_test);
            }
        });
        y=y+height+interval;
        //Кнопка удаления теста
        updateTest = new JButton("Удаление теста");
        updateTest.setBounds(x,y,width,height);
        this.getContentPane().add(this.updateTest);
        frame.add(updateTest);

        //Еще не готово, тут открывается форма добавления теста
        updateTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thetest test = null;
                try {
                    id_test = tests.getSelectedIndex()+1;
                    test = new thetest(id_test);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
//                test.id_test = id_test;
                System.out.println(id_test);
            }
        });
    }
}

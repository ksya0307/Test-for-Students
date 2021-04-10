package pr29_30;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CheckResults extends JFrame{
    public int id_test;
    private JComboBox tests;
    private JPanel contentPane;
    private JTable tableResults;
    private JButton buttonBack;
    private JComboBox users;
    private JComboBox solvedTests;
    private JButton buttonSubmit;
    public int id_user;

    public  CheckResults() throws ClassNotFoundException {

        int x = 20;
        int y = 25;
        int interval = 25;
        int widthForm = 600;
        int heightForm = 250;
        int widthCombobox = 300;
        int heightCombobox = 25;
        int widthButton = 150;
        int heightButton = heightCombobox;
        Test test = new Test();
        JFrame frame = new JFrame("Просмотр результатов решения теста");
        frame.setBounds(400,400,widthForm,heightForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        this.setContentPane(this.contentPane);
        this.getContentPane().setLayout((LayoutManager)null);

        //Список пользователей
        users = new JComboBox();
        users.setBounds(x,20,widthCombobox,heightCombobox);

        //Список тестов, пройденных выбранным пользователем
        solvedTests = new JComboBox();
        solvedTests.setBounds(x, 60, widthCombobox, heightCombobox);
        User user = new User();
        //Получение списка пользователей
        Map<Integer,String> usersMap = user.getUsers();
        //Заполнение списком пользователей
        for(Map.Entry<Integer, String> entry : usersMap.entrySet())
        {
            users.addItem(entry.getValue());
        }
        //Получение списка тестов пользователя
        getTestFromUsers(usersMap);

        frame.add(solvedTests);
        frame.add(users);
        users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    getTestFromUsers(usersMap);
                    //frame.add(solvedTests);
                }
                catch (Exception ex)
                {
                }
            }
        });
        //Кнопка Закрытия
        buttonBack = new JButton("Закрыть");
        buttonBack.setBounds(x,heightForm-100,widthButton,heightButton);
        this.getContentPane().add(this.buttonBack);
        frame.add(buttonBack);

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        //Кнопка показа теста пользователя
        buttonSubmit = new JButton("Показать тест");
        buttonSubmit.setBounds(x, 100, widthButton, heightButton);
        this.getContentPane().add(this.buttonSubmit);
        frame.add(buttonSubmit);

        //Идет получение id пользователя и теста и передача их в качестве параметров
        //форме, которая отображает решенный тест
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int iduser= 0;
                int idtest = 0;
                try
                {
                    for(Map.Entry<Integer, String> entry : usersMap.entrySet())
                    {
                        if(entry.getValue() == users.getSelectedItem().toString())
                        {
                            iduser = entry.getKey();
                            System.out.println(iduser + "\t"+ test.getIdTest(solvedTests.getSelectedItem().toString()));
                            ReviewResult reviewResult = new ReviewResult(iduser,
                                    test.getIdTest(solvedTests.getSelectedItem().toString()));
                            break;
                        }
                    }
                }
                catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });

    }

    private void getTestFromUsers(Map<Integer, String> usersMap)
    {
        try
        {
            solvedTests.removeAllItems();
            int iduser = 0;
            for(Map.Entry<Integer, String> entry : usersMap.entrySet())
            {
                if(entry.getValue() == users.getSelectedItem().toString())
                {
                    iduser = entry.getKey();
                    break;
                }
            }
            Test test = new Test();
            Map<Integer,String> mapSolvedTests = test.getSolvedTests(iduser);
            for (Map.Entry<Integer, String> entry : mapSolvedTests.entrySet())
            {
                solvedTests.addItem(entry.getValue());
            }
        }
        catch (Exception ex)
        {
        }
    }
}

package pr29_30;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class choosetest extends JFrame{
    public int id_test;
    private JComboBox tests;
    private JPanel contentPane;
    private JButton getTest;
    public int id_user;
    public  choosetest(int id_user) throws ClassNotFoundException {
        JFrame frame = new JFrame("Авторизация");

        frame.setBounds(830,400,580,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        this.setContentPane(this.contentPane);
        this.getContentPane().setLayout((LayoutManager)null);

        Test showtests = new Test();
        Map<Integer, String> listtest = showtests.getTests(id_user);

        tests = new JComboBox();
        Map<Integer, String> getTests = showtests.getTests(id_user);

        for (Map.Entry<Integer,String> entry: getTests.entrySet())
        {
            tests.addItem(entry.getValue());
        }


        tests.setBounds(50,40,300,25);
        this.getContentPane().add(this.tests);

        getTest = new JButton("Открыть");
        getTest.setBounds(360,40,150,25);
        this.getContentPane().add(this.getTest);
        frame.add(getTest);
        frame.add(tests);

        getTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thetest test = null;

                try {
                    //Получение id теста при помощи метода класса Test
                    int id_test = showtests.getIdTest(tests.getSelectedItem().toString());
                    test = new thetest(id_test, id_user);
                    frame.setVisible(false);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
    }
}

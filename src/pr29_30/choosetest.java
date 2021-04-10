package pr29_30;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
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
        List<String> listtest = showtests.getTests();

        tests = new JComboBox(listtest.toArray());
        tests.setBounds(20,20,300,25);
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

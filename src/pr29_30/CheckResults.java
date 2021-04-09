package pr29_30;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckResults extends JFrame{
    public int id_test;
    private JComboBox tests;
    private JPanel contentPane;
    private JTable tableResults;
    private JButton buttonBack;
    public int id_user;

    public  CheckResults() throws ClassNotFoundException {

        int x = 20;
        int y = 25;
        int interval = 25;
        int width = 600;
        int height = 700;
        JFrame frame = new JFrame("Просмотр результата");
        frame.setBounds(400,400,width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        this.setContentPane(this.contentPane);
        this.getContentPane().setLayout((LayoutManager)null);


        //Кнопка Возврата назад
        buttonBack = new JButton("Назад");
        buttonBack.setBounds(x,height-150,150,25);
        this.getContentPane().add(this.buttonBack);
        frame.add(buttonBack);

        //Еще не готово, тут открывается форма добавления теста
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
}

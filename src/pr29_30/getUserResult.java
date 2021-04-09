package pr29_30;

import javax.swing.*;

public class getUserResult extends JFrame{
    private JTable UserTestsResult;
    private JPanel panel;
    private JFrame frame;
    public int id_user;


    public getUserResult(int id_user) {
        this.id_user = id_user;

        frame = new JFrame("Результаты тестов");

        //
        UserTestsResult = new JTable();

    }



}

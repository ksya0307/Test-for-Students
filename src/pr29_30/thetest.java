package pr29_30;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class thetest extends JFrame{
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JFrame frame;
    public JRadioButton answer;
    public int id_user;
    public int rightanswers;
    public thetest(int id_test, int id_user) throws ClassNotFoundException {
        frame = new JFrame("Тест " + id_test);


        //получить id юзер
        this.id_user = id_user;


        JPanel panel = new JPanel();

        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0,0,700, 600);
        scrollPane.setAutoscrolls(true);
        panel.setBounds(0,0,700, 600);
        panel.setEnabled(false);
        this.getContentPane().setLayout((LayoutManager)null);


        Map<Integer,String> questions = new HashMap<Integer,String>();
        Question q = new Question();
        questions=q.getQuestion(id_test);

        JTextArea[] quests = new JTextArea[questions.size()];

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Test test = new Test();
        JTextArea text = new JTextArea();
        text.setText(test.getTest(id_test));
        panel.add(text);
        int y = 50;
        int i =0;
        Map<Integer,String> answers = new HashMap<Integer,String>();
        ButtonGroup[] btn_grp = new ButtonGroup[questions.size()];
        for (Map.Entry<Integer, String> entry : questions.entrySet())
        {
            quests[i] = new JTextArea();
            quests[i].setEditable(false);
            quests[i].setLineWrap(true);
            quests[i].setWrapStyleWord(true);

            quests[i].setBounds(10, y, 550, 50);
            quests[i].setBackground(new Color(0,0,0,0));

            y+= 60;

            quests[i].setName(entry.getKey().toString());
            quests[i].setText(entry.getValue());
            quests[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(quests[i]);

            answers = q.getAnswers(entry.getKey());
            btn_grp[i] = new ButtonGroup();

            for (Map.Entry<Integer, String> entry_ans : answers.entrySet())
            {
                answer = new JRadioButton();
                answer.setText(entry_ans.getValue());
                answer.setName(entry_ans.getKey().toString());
                answer.setBounds(10, y, 600, 50);
                y+=50;
                answer.setAlignmentX(Component.LEFT_ALIGNMENT);
                btn_grp[i].add(answer);
                panel.add(answer);
            }
            i++;
            y+=20;
        }

        JButton submit = new JButton("Завершить тест");
        JLabel countrightanswers = new JLabel();
        panel.add(submit);
        ActionListener submitClick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightanswers =0;
                for (int i = 0; i < btn_grp.length; i++)
                {
                    for (Enumeration<AbstractButton> buttons = btn_grp[i].getElements(); buttons.hasMoreElements();) {
                        AbstractButton button = buttons.nextElement();
                        Answer ans = new Answer();
                        System.out.println(button.getName()+" name" + " q: "+quests[i].getName());

                        try {
                            if(button.isSelected()){
                                if(ans.isTrue(Integer.parseInt(button.getName()), Integer.parseInt(quests[i].getName()))){
                                    //System.out.println(button.getName()+" name" + " q: "+quests[i].getName() + " answer "+ans.right);
                                    button.setBackground(Color.GREEN);
                                    rightanswers++;
                                }
                                else{
                                    button.setBackground(Color.RED);
                                }
                            }

                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }

                    }
                }
                JOptionPane.showMessageDialog(null,"Количество правильных ответов - "+rightanswers);
                try {
                    InsertResultUser(id_user,id_test,rightanswers);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        };
        submit.addActionListener(submitClick);

        getUserResult getUserResult = new getUserResult(this.id_user);
        frame.add(scrollPane);
        frame.setBounds(400, 300, 700, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    private void InsertResultUser(int id_user,int id_test, int result) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String hostname = "localhost";
        String user = "ksyaVova";
        String pass = "sys";
        String sid = "orcl";

        Connection con = null;
        PreparedStatement st = null;
        String url = "jdbc:oracle:thin:@" + hostname + ":1521:" + sid;

        try {
            String sql = "insert into results(iduser, idtest,result) values ("+this.id_user+", "+id_test+", "+rightanswers+")";

            con = DriverManager.getConnection(url, user, pass);

            st = con.prepareStatement(sql);
            st.executeUpdate();


        } catch (SQLException var33) {
            System.out.println(var33.toString());
        } finally {
            if (con != null) {

                try {
                    con.close();
                } catch (SQLException var30) {
                    System.out.println(var30.toString());
                }

            }

        }
    }
}


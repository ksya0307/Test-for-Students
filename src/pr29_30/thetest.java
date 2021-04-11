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
    private JButton back;
    private JButton checkresult;
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
        this.getContentPane().setLayout(null);

        back = new JButton("Назад");
        back.setBounds(0,0,100,30);
        panel.add(back);




        Map<Integer,String> questions;
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

        Map<Integer,String> answers;

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
                answer = new JRadioButton(entry_ans.getValue());
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

        checkresult = new JButton("Посмотреть результаты");
        checkresult.setBounds(0,0,200,30);
        checkresult.setEnabled(false);
        panel.add(checkresult);

        JButton submit = new JButton("Завершить тест");
        panel.add(submit);
        int finalI = i;
        ActionListener submitClick = e -> {
            rightanswers =0;
            for (int i1 = 0; i1 < btn_grp.length; i1++)
            {
                //Цикл for проходит по элементам buttonGroup и записывает элемент в экземпляр класса AbstractButton
                for (Enumeration<AbstractButton> buttons = btn_grp[i1].getElements(); buttons.hasMoreElements();) {
                    //Получение каждого элемента buttonGroup
                    AbstractButton button = buttons.nextElement();
                    Answer ans = new Answer();
                    try {
                        //Проверка выбран ли данный RadioButton
                        if(button.isSelected()){
                            //Вывод в консоль номера отвера и имя пользователя
                            System.out.println(button.getName() +" id ответа" + " user - " + id_user);
                            Answers insertAnswers = new Answers();
                            //Вставка ответа в базу путем вывоза метода InsertIntoUserAnswers
                            insertAnswers.InsertIntoUserAnswers(id_user,Integer.parseInt(button.getName()));
                            //Проверка ответа направильность
                            if(ans.isTrue(Integer.parseInt(button.getName()))){
                                //Если правильный, то выделяется зеленым
                                button.setBackground(Color.GREEN);
                                //Счетчик правильных ответов
                                rightanswers++;
                            }
                            else{
                                //Неправильный выделяется красным
                                button.setBackground(Color.RED);
                            }
                        }

                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                }
            }
            //Показ количества правильных ответов
            JOptionPane.showMessageDialog(null,"Количество правильных ответов - "+rightanswers);
            checkresult.setEnabled(true);
            try {
                //Вставка результата в базу через метод InsertResultUser
                InsertResultUser(id_user,id_test,rightanswers);
                submit.setEnabled(false);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

        };
        submit.addActionListener(submitClick);



        frame.add(scrollPane);
        frame.setBounds(400, 300, 700, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        back.addActionListener(e -> {
            try {
                choosetest backchoose = new choosetest(id_user);
                frame.setVisible(false);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });

        checkresult.addActionListener(e -> {
            try {
                getUserResult getUserResult = new getUserResult(id_user);
                frame.setVisible(false);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

        });
    }

    private void InsertResultUser(int id_user,int id_test, int result) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            //Подключение к БД и запрос на вставку записи
            con = ORCLConnection.conn();
            String sql = "insert into results(iduser, idtest,result) values ("+this.id_user+", "+id_test+", "+rightanswers+")";

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

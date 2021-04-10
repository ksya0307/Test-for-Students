package pr29_30;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ReviewResult extends JFrame{
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JFrame frame;

    public ReviewResult(int idUser, int idTest) throws ClassNotFoundException {
        frame = new JFrame("Просмотр теста " + idTest);
        JPanel panel = new JPanel();

        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0,0,700, 600);
        scrollPane.setAutoscrolls(true);
        panel.setBounds(0,0,700, 600);
        panel.setEnabled(false);
        this.getContentPane().setLayout((LayoutManager)null);

        //Получение списка вопросов
        Map<Integer,String> questions = new HashMap<Integer,String>();
        Question q = new Question();
        questions=q.getQuestion(idTest);

        JTextArea[] quests = new JTextArea[questions.size()];

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Получение названия теста
        Test test = new Test();
        JTextArea text = new JTextArea();
        text.setText(test.getTest(idTest));
        panel.add(text);
        int y = 50;
        int i =0;

        Map<Integer,String> answers = new HashMap<Integer,String>();
        ButtonGroup[] btn_grp = new ButtonGroup[questions.size()];

        //Составление списка ответов и вопросов
        Answer ans = new Answer();
        List<AnswersQuestions> answersQuestionsList = ans.getAnswersOfUser(idUser, idTest);
        //Отображение вопросов
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

            //рисование Radiobutton
            for (Map.Entry<Integer, String> entry_ans : answers.entrySet())
            {
                JRadioButton answer = new JRadioButton();
                answer.setText(entry_ans.getValue());
                answer.setName(entry_ans.getKey().toString());
                answer.setEnabled(false);
                int j =0;
                /*
                * Пролистываем список и смотрим ответы на вопросы,
                * программа сравнивает ответы с ответами от пользователя,
                * а также отображает правильность ответа
                */
                for (AnswersQuestions answerQuest: answersQuestionsList)
                {
                    if(answer.getName().equals(Integer.toString(answerQuest.idAns)))
                    {
                        answer.setSelected(true);

                        if(ans.isTrue(Integer.parseInt(answer.getName()))){
                            answer.setBackground(Color.GREEN);

                        }
                        else{
                            answer.setBackground(Color.RED);
                        }
                        j++;
                        break;
                    }

                }
                answer.setBounds(10, y, 600, 50);
                y+=50;
                answer.setAlignmentX(Component.LEFT_ALIGNMENT);
                btn_grp[i].add(answer);
                panel.add(answer);
            }
            i++;
            y+=20;
        }

        frame.add(scrollPane);
        frame.setBounds(400, 300, 700, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

}

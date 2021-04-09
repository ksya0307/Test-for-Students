package pr29_30;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class thetest extends JFrame{
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JFrame frame;

    public thetest(int id_test) throws ClassNotFoundException {
        frame = new JFrame("Тест " + id_test);
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
           /* JTextArea question = new JTextArea();
            question.setEnabled(false);
            question.setLineWrap(true);
            question.setWrapStyleWord(true);
            question.setBounds(10, y, 600, 50);

            y+= 110;

            question.setName(entry.getKey().toString());
            question.setText(entry.getValue());
            panel.add(question);*/


            answers = q.getAnswers(entry.getKey());
            btn_grp[i] = new ButtonGroup();

            for (Map.Entry<Integer, String> entry_ans : answers.entrySet())
            {
                JRadioButton answer = new JRadioButton();
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
        panel.add(submit);
        ActionListener submitClick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < btn_grp.length; i++)
                {
                    for (Enumeration<AbstractButton> buttons = btn_grp[i].getElements(); buttons.hasMoreElements();) {
                        AbstractButton button = buttons.nextElement();
                        Answer ans = new Answer();
                        try {
                            if(ans.isTrue(Integer.valueOf(button.getName())))
                            {

                            }
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                        if(button.isSelected())
                        {

                        }

                    }
                }

            }
        };
        submit.addActionListener(submitClick);
        frame.add(scrollPane);
        frame.setBounds(400, 300, 700, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

}

package pr29_30;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class thetest extends JFrame{
    private JPanel contentPane;
    private JScrollPane scrollPane;
    //public Integer id_test;

    public thetest(int id_test) throws ClassNotFoundException {
        JFrame frame = new JFrame("Тест");
        frame.setBounds(830,400,650,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);

        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//        scrollPane.setBounds(630,0,20,800);
        scrollPane.setVisible(true);

        frame.setContentPane(scrollPane);
        contentPane = new JPanel();
        contentPane.setVisible(true);
        //frame.getContentPane().add(this.contentPane);
        scrollPane.add(contentPane);

        this.getContentPane().setLayout((LayoutManager)null);
      //  frame.add(contentPane);


        Map<Integer,String> questions = new HashMap<Integer,String>();
        Map<Integer, String> answers = new HashMap<Integer,String>();

        Question q = new Question();
//        Answers answ = new Answers();

        questions=q.getQuestion(id_test);
        int idq=1;
//        answers = answ.getAnswers(idq);



       // contentPane.add(scrollPane);
//git

        int y=10;
        for (Map.Entry<Integer, String> entry : questions.entrySet()
             ) {
            JTextArea question = new JTextArea();
            question.setText(entry.getValue());
            question.setEditable(false);
            question.setLineWrap(true);
            question.setWrapStyleWord(true);

            question.setBounds(10,y,600,100);
            y=y+200;


            contentPane.add(question);
            frame.add(question);

        }



    }

}

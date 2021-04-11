package pr29_30;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Answer
{
    public int id;
    public int idquestion;
    public int text;
    public String right;

    public boolean isTrue(int id_answer) throws ClassNotFoundException {
        String tr_fls = "";
        Connection con = null;
        Statement st = null;
        try {
            //Запрос на выборку
            String sql = "select right from answers where id="+ id_answer;
            //Подключение к БД
            con = ORCLConnection.conn();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs!=null)
            {
                while(rs.next()){
                    //Получаем значение из базы данных
                    this.right=rs.getString("right");
                }
            }
            else System.out.println("Ошибочка!");
            /*Прверяем, если полученное значение равно Y,
            * то метод возвращает true,
            * иначе – возвращает false*/
            if(this.right.equals("y"))
            {
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException var33) {
            System.out.println(var33.toString());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return false;
    }
    public Integer getQuestion(int id_answer) throws ClassNotFoundException {
        Integer id_quest = null;
        Connection con = null;
        Statement st = null;

        try {
            String sql = "select idquestion from answers where id="+ id_answer;
            con = ORCLConnection.conn();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.print("\n");
            if(rs!=null)
            {
                while(rs.next()){

                    id_quest=rs.getInt("idquestion");

                }

            }
            else System.out.println("Ошибочка!");
            return id_quest;

        } catch (SQLException var33) {
            System.out.println(var33.toString());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return id_quest;
    }

    public List<AnswersQuestions> getAnswersOfUser(int idUser, int idTest)
    {
        List<AnswersQuestions> answers = new ArrayList<AnswersQuestions>();
        Connection con = null;
        Statement st = null;
        try {
            String sql = "select a.idquestion as idQuestion, a.id as idAnswer from \n" +
                    "USERANSWERS u inner join ANSWERS a\n" +
                    "inner join QUESTIONS q\n" +
                    "inner join TESTS t\n" +
                    "on t.id=q.idtest\n" +
                    "on q.id=a.idquestion\n" +
                    "on a.id=u.idanswer WHERE " +
                    "u.iduser = "+idUser+" " +
                    "and t.id="+idTest+"";
            con = ORCLConnection.conn();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.print("\n");
            if(rs!=null)
            {

                while(rs.next()){
                    answers.add(new AnswersQuestions(rs.getInt("idQuestion"),
                            rs.getInt("idAnswer")));
                }
                return answers;
            }
            else System.out.println("Ошибочка!");

        } catch (SQLException | ClassNotFoundException var33) {
            System.out.println(var33.toString());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return null;

    }
}

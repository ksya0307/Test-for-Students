package pr29_30;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Answer
{
    public int id;
    public int idquestion;
    public int text;
    public int right;

    public boolean isTrue(int id_answer) throws ClassNotFoundException {
        String tr_fls = "";
        Connection con = null;
        Statement st = null;
        try {
            String sql = "select right from answers where id="+ id_answer;
            con = ORCLConnection.conn();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.print("\n");
            if(rs!=null)
            {

                while(rs.next()){

                    tr_fls=rs.getString("right");

                }

            }
            else System.out.println("Ошибочка!");

            if(tr_fls.equals("y"))
            {
                return true;
            }
            return false;

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
            System.out.println("Подключаемся к БД");
            con = ORCLConnection.conn();
            System.out.println("Успешно");
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
                System.out.println("Закрытие подключения");

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
        List<Integer> idAnswers = new ArrayList<Integer>();
        Connection con = null;
        Statement st = null;
        try {
            String sql = "select right from answers where id=";
            con = ORCLConnection.conn();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.print("\n");
            if(rs!=null)
            {

                while(rs.next()){

            //        tr_fls=rs.getString("right");

                }

            }
            else System.out.println("Ошибочка!");

            return null;

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


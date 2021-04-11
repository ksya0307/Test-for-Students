package pr29_30;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
    public int id;
    public String text;
    public int rightanswer;
    public int idtest;

    public Map<Integer,String> getQuestion(int idtest) throws ClassNotFoundException {

        Map<Integer,String> questions = new HashMap<Integer,String>();
        Connection con = null;
        Statement st = null;


        try {
            con = ORCLConnection.conn();
            String sql = "select id, text from questions where idtest=" + idtest;



            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.print("\n");

            if (rs != null) {

                while (rs.next()) {

                    questions.put(rs.getInt("id"),rs.getString("text"));

                }
                return questions;


            } else System.out.println("Ошибочка!");


        } catch (SQLException var33) {
            System.out.println(var33.toString() + "ворао");

        } finally {
            if (con != null) {
                try {
                    con.close();
                    return questions;
                } catch (SQLException e) {
                    System.out.println(e.toString() + "sdf");
                }
            }
        }
        return null;
    }

    public Map<Integer,String> getAnswers(int idQuestion) throws ClassNotFoundException {
        Map<Integer,String> answers = new HashMap<Integer,String>();
        Connection con = null;
        Statement st = null;
        try {
            con = ORCLConnection.conn();
            String sql = "select id,text from answers where idquestion=" + idQuestion;
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.print("\n");
            if (rs != null) {

                while (rs.next()) {

                    answers.put(rs.getInt("id"),rs.getString("text"));

                }
                return answers;


            } else System.out.println("Ошибочка!");


        } catch (SQLException var33) {
            System.out.println(var33.toString());
        } finally {
            if (con != null) {
                try {
                    con.close();
                    return answers;
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return null;
    }

    public void InsertQuestionTest(String thequestion,int id_test){
        Connection con = null;
        PreparedStatement st = null;


        try {
            con = ORCLConnection.conn();
            String sql = "insert into questions(text, idtest) values('"+thequestion+"',"+id_test+")";

            st = con.prepareStatement(sql);
            st.executeUpdate();


        } catch (SQLException | ClassNotFoundException var33) {
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

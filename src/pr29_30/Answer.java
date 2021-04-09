package pr29_30;

import java.sql.*;

public class Answer
{
    public int id;
    public int idquestion;
    public int text;
    public int right;

    public boolean isTrue(int id_answer) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String hostname = "pcForOracle";
        String user = "ksyaVova";
        String pass = "root";
        String sid = "orcl";
        String tr_fls = "";
        Connection con = null;
        Statement st = null;
        String url = "jdbc:oracle:thin:@" + hostname + ":1521:" + sid;

        try {
            String sql = "select right from answers where id="+ id_answer;
            con = DriverManager.getConnection(url, user, pass);
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
                    return false;
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return false;
    }
    public Integer getQuestion(int id_answer) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String hostname = "pcForOracle";
        String user = "ksyaVova";
        String pass = "root";
        String sid = "orcl";
        Integer id_quest = null;
        Connection con = null;
        Statement st = null;
        String url = "jdbc:oracle:thin:@" + hostname + ":1521:" + sid;

        try {
            String sql = "select idquestion from answers where id="+ id_answer;
            System.out.println("Подключаемся к БД");
            con = DriverManager.getConnection(url, user, pass);
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
                    return id_quest;
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return id_quest;
    }
}


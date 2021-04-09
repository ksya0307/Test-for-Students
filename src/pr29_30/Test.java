package pr29_30;

import java.sql.*;
import java.util.*;

public class Test {
    public int id;
    public String test;

    public List<String> getTests() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String hostname = "localhost";
        String user = "ksyaVova";
        String pass = "sys";
        String sid = "orcl";
        List<String> tests = new ArrayList<String>();
        Connection con = null;
        Statement st = null;
        String url = "jdbc:oracle:thin:@" + hostname + ":1521:" + sid;

        try {
            String sql = "select id, test from tests";
            System.out.println("Подключаемся к БД");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Успешно");
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.print("\n");
            if(rs!=null){

                while(rs.next()){

                    tests.add(rs.getString("test"));

                }
                return tests;


            }
            else System.out.println("Ошибочка!");


        } catch (SQLException var33) {
            System.out.println(var33.toString());
        } finally {
            if (con != null) {
                System.out.println("Закрытие подключения");

                try {
                    con.close();
                    return tests;
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return null;
    }
    public String getTest(int id_test) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String hostname = "localhost";
        String user = "ksyaVova";
        String pass = "sys";
        String sid = "orcl";
        String text_test = new String();
        Connection con = null;
        Statement st = null;
        String url = "jdbc:oracle:thin:@" + hostname + ":1521:" + sid;

        try {
            String sql = "select test from tests where id="+ id_test;

            con = DriverManager.getConnection(url, user, pass);

            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.print("\n");
            if(rs!=null){

                while(rs.next()){

                    text_test=rs.getString("test");

                }
                return text_test;


            }
            else System.out.println("Ошибочка!");


        } catch (SQLException var33) {
            System.out.println(var33.toString());
        } finally {
            if (con != null) {


                try {
                    con.close();
                    return text_test;
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return null;
    }

}


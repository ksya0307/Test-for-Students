package pr29_30;

import java.sql.*;
import java.util.*;

public class Test {
    public int id;
    public String test;

    public List<String> getTests() throws ClassNotFoundException {
        List<String> tests = new ArrayList<String>();
        Connection con = null;
        Statement st = null;
        try {
            String sql = "select id, test from tests";
            System.out.println("Подключаемся к БД");
            con = ORCLConnection.conn();
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
        String text_test = new String();
        Connection con = null;
        Statement st = null;
        try {
            String sql = "select test from tests where id="+ id_test;
            System.out.println("Подключаемся к БД");
            con = ORCLConnection.conn();
            System.out.println("Успешно");
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
                System.out.println("Закрытие подключения");

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

    public Map<Integer,String> getSolvedTests(Integer idUser) throws ClassNotFoundException {
        Map<Integer,String> tests = new HashMap<>();
        Connection con = null;
        Statement st = null;
        try {
            String sql = "select id, test from tests WHERE id in (SELECT idtest FROM results where iduser="+idUser+")";
            System.out.println("Подключаемся к БД");
            con = ORCLConnection.conn();
            System.out.println("Успешно");
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.print("\n");
            if(rs!=null){

                while(rs.next()){

                    tests.put(rs.getInt("id"), rs.getString("test"));

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

}


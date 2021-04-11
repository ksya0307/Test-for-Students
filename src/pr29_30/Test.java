package pr29_30;

import java.sql.*;
import java.util.*;

public class Test {
    public int id;
    public String test;

    public Map<Integer, String> getTests(int id_user) throws ClassNotFoundException {

        Connection con = null;
        Statement st = null;
        Map<Integer,String> tests = new HashMap<Integer,String>();
        try {
            con = ORCLConnection.conn();
            String sql = "select id, test from TESTS where ID not in (select idtest from results where iduser="+id_user+")";

            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if(rs!=null){

                while(rs.next()){
                    tests.put(rs.getInt("id"),rs.getString("test"));
                }
                return tests;


            }
            else System.out.println("Ошибочка!");


        } catch (SQLException var33) {
            System.out.println(var33.toString());
        } finally {
            if (con != null) {

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
            con = ORCLConnection.conn();
            String sql = "select test from tests where id="+ id_test;

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
            con = ORCLConnection.conn();
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

    public int getIdTest(String name_test){
        Connection con = null;
        Statement st = null;
        try {
            con = ORCLConnection.conn();
            String sql = "select id from tests where test='"+ name_test+"'";

            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if(rs!=null){
                while(rs.next())
                {
                    this.id = rs.getInt("id");
                }

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
        return id;
    }

    public  void InsertTest(String name_test, int amount){

        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ORCLConnection.conn();
            String sql = "insert into tests(test, amount) values('"+name_test+"'," +amount+")";

            st = con.prepareStatement(sql);
            st.executeUpdate();


            con = ORCLConnection.conn();



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
    }
}

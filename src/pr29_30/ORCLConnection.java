package pr29_30;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ORCLConnection {
    public static Connection conn() throws ClassNotFoundException {
        String hostname = "localhost";
        String user = "ksyaVova";
        String pass = "sys";
        String sid = "orcl";
        String url = "jdbc:oracle:thin:@" + hostname + ":1521:" + sid;
        Connection connect = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connect = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }
}
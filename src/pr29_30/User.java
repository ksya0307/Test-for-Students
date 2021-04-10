package pr29_30;
import java.io.PrintStream;
import java.sql.*;

public class User {
    public int id;
    public String name;
    public String login;
    public String password;
    public int role;

    public boolean enter( String login, String password) throws ClassNotFoundException {


        Connection con = null;
        Statement st = null;


        try {
            con = ORCLConnection.conn();
            String sql = "select id,name,login,password,role from users where login='"+login+"'and password='"+password+"'";



            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if(rs!=null){
                while(rs.next()){
                    this.id=rs.getInt("id");
                    this.role=rs.getInt("role");
                    this.name=rs.getString("name");
                    this.login=rs.getString("login");
                    this.password=rs.getString("password");
                    System.out.println(this.id+" "+this.role+" " + login+" "+password);
                    return true;
                }


            }
            else return false;


        } catch (SQLException var33) {
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
        return false;
    }
}

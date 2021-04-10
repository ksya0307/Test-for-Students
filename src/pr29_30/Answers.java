package pr29_30;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
//не пользуюсь
public class Answers {
    int id;
    int idquestion;
    String text;
    public Map<Integer,String> getAnswers(int idquestion) throws ClassNotFoundException {
        Map<Integer,String> answers = new HashMap<Integer,String>();
        Connection con = null;
        Statement st = null;

        try {
            con = ORCLConnection.conn();
            String sql = "select text from answers where idquestion=" + idquestion;



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
}

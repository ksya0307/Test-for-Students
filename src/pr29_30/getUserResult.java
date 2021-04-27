package pr29_30;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class getUserResult extends JFrame{
    private JTable UserTestsResult;
    private JPanel panel;
    private JButton choosetest;
    private JFrame frame;
    public int id_user;


    public getUserResult(int id_user) throws ClassNotFoundException {
        this.id_user = id_user;

        frame = new JFrame("Результаты тестов");
        this.setContentPane(this.panel);
        this.getContentPane().setLayout(null);
        frame.setSize(700,200);
        frame.setVisible(true);
        choosetest = new JButton("К списку тестов");
        choosetest.setBounds(0,0,150,20);
        this.getContentPane().add(this.choosetest);
        frame.add(choosetest);


        UserTestsResult = new JTable();
        UserTestsResult.setBounds(30,40,350,300);
        UserTestsResult.getTableHeader().setResizingAllowed(false);


        Connection con = null;
        Statement st = null;

        try {
            con = ORCLConnection.conn();
            String sql = "select tests.test as "+"Тест"+", results.result as "+"Результат "+" from results inner join tests on results.IDTEST=tests.ID where results.IDUSER="+id_user;
            FillTable(UserTestsResult,sql,con);
            JScrollPane sp = new JScrollPane(UserTestsResult);
            frame.add(sp);
        } finally {
            if (con != null) {

                try {
                    con.close();
                } catch (SQLException var30) {
                    System.out.println(var30.toString());
                }
            }
        }



        choosetest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pr29_30.choosetest backchoose = new choosetest(id_user);
                    frame.setVisible(false);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });
    }

    public void FillTable(JTable table, String Query, Connection con)
    {
        try
        {
            PreparedStatement stat = con.prepareStatement(Query);
            ResultSet rs = stat.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            stat.close();
            con.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

}

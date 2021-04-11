package pr29_30;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class tests extends JFrame{
    private JPanel contentPane;
    private JTextField passw;
    private JButton auth_btn;
    private JTextField login;
    private JLabel l_login;
    private JLabel l_passw;


    public tests(){
        JFrame frame = new JFrame("Авторизация");
        frame.setBounds(830,400,300,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        this.setContentPane(this.contentPane);

        this.getContentPane().setLayout((LayoutManager)null);
        login = new JTextField(15);

        passw = new JTextField(15);

        auth_btn=new JButton("Войти");

        l_login = new JLabel("Логин");

        l_passw = new JLabel("Пароль");

        l_login.setBounds(120 ,10,100,20);
        login.setBounds(40,40,200,30);

        l_passw.setBounds(115,80,100,20);
        passw.setBounds(40,110,200,30);
        auth_btn.setBounds(40,150,200,40);
        this.getContentPane().add(this.auth_btn);
        this.getContentPane().add(this.login);
        this.getContentPane().add(this.passw);
        this.getContentPane().add(this.l_login);
        this.getContentPane().add(this.l_passw);

        auth_btn.addActionListener(e -> {
            User user = new User();
            try {
                //Вызов метода класса User на проверку правильности ввода пароля
                if(user.enter(login.getText().toString(),passw.getText().toString())){
                    //Успешный ввод логина и пароля
                    JOptionPane.showMessageDialog(null,"ОК!");
                    //Проверка роли,
                    //если роль равна 1 – то появится админ панель
                    if(user.role ==1){
                        AdminMainMenu mainMenu = new AdminMainMenu(user.id);
                    }
                    else if(user.role==2){ //если роль равна 2 – то появится форма выбора теста
                        choosetest showpage = new choosetest(user.id);
                    }
                    frame.setVisible(false);
                }
                else{ //на случай, если пароль или логин введены неверно
                    JOptionPane.showMessageDialog(null,"Ошибка!");
                }
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
        frame.add(login);
        frame.add(passw);
        frame.add(auth_btn);
        frame.add(l_passw);
        frame.add(l_login);
    }


    public static void main(String[] args) {
        tests dialog = new tests();

    }
}

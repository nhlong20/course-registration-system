package GUI;

import dao.AccountDAO;
import pojo.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/2/2021 - 2:14 AM
 * @Description
 */
public class LoginGUI extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginSubmitButton;
    private JPanel loginPanel;

    public LoginGUI() {
        super("Hệ thống đăng ký khoá học");
        this.setContentPane(loginPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(1920/2-200, 1080/2-200);
        this.pack();

        loginSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                Account account = AccountDAO.getAccount(username, password);
                if(account == null){
                        JOptionPane.showMessageDialog(null,"Tên đăng nhập hoặc mật khẩu không đúng",
                                "Login error", JOptionPane.ERROR_MESSAGE);
                        return;
                }
                System.out.print(account.getAccType());
            }
        });
    }


}

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
public class LoginGUI extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginSubmitButton;
    private JPanel loginPanel;
    public LoginGUI() {
        super("Hệ thống đăng ký khoá học");
        this.setContentPane(loginPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(1920 / 2 - 200, 1080 / 2 - 200);
        this.pack();
        loginSubmitButton.addActionListener(this);
        this.setVisible(true);
    }

    private String validateInput(String username, String password){
        if (username == null || username.trim().length() == 0 || password.trim().length() == 0) {
            return "Tài khoản hoặc mật khẩu không được bỏ trống";
        }
        return null;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        // Check invalid input
        String error_check = validateInput(username,password);
        if(error_check != null){
            errorHandler("Login error", error_check);
            return;
        }

        // Check account is exist or not
        Account account = AccountDAO.getAccount(username, password);
        if (account == null) {
            errorHandler("Login error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return;
        }

        String accountType = account.getAccType();
        if(accountType.toLowerCase().equals("moderator")){
            MainApp.invokeGUI(MainApp.ViewControl.MODERATOR);
        } else if (accountType.toLowerCase().equals("student")){
            MainApp.invokeGUI(MainApp.ViewControl.STUDENT);
        }
        MainApp.setCurrentAccount(account);
        this.dispose();
    }
    public void errorHandler(String type, String err_msg){
        JOptionPane.showMessageDialog(null, err_msg,
                type, JOptionPane.ERROR_MESSAGE);
    }
}

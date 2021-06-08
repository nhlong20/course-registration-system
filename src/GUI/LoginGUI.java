package GUI;

import dao.AccountDAO;
import main.MainApp;
import pojo.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * GUI
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/2/2021 - 2:14 AM
 * @Description
 */
public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginSubmitButton;
    private JPanel loginPanel;
    public LoginGUI() {
        super("Hệ thống đăng ký khoá học");
        this.setContentPane(loginPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        loginSubmitButton.addActionListener(e -> onSubmit());
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    onSubmit();
                }
            }
        });
    }

    private String validateInput(String username, String password){
        if (username == null || username.trim().length() == 0 || password.trim().length() == 0) {
            return "Tài khoản hoặc mật khẩu không được bỏ trống";
        }
        return null;
    }
    private void onSubmit(){
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        // Check invalid input
        String error_check = validateInput(username,password);
        if(error_check != null){
            errorHandler("Lỗi đăng nhập", error_check);
            return;
        }

        // Check account is exist or not
        Account account = AccountDAO.getAccount(username, password);
        if (account == null) {
            errorHandler("Lỗi đăng nhập", "Tên đăng nhập hoặc mật khẩu không đúng");
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

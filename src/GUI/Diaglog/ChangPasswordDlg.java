package GUI.Diaglog;

import dao.AccountDAO;
import main.MainApp;
import pojo.Account;

import javax.swing.*;
import java.awt.event.*;

public class ChangPasswordDlg extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField oldPwdField;
    private JPasswordField newPwdField;
    private JPasswordField confirmPwdField;
    private Account currentAcc;
    private Boolean changedPasswordFlag;

    public ChangPasswordDlg(Account currentAccount) {
        this.setTitle("Tạo mới lớp học");
        this.currentAcc = currentAccount;
        this.changedPasswordFlag = false;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.addEventListener();

        this.setModal(true);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
    public boolean isSucess(){
        return this.changedPasswordFlag;
    }
    private void addEventListener(){
        buttonOK.addActionListener(e->onOK());

        buttonCancel.addActionListener(e->onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    private void onOK() {
        // add your code here
        String oldPwd = String.valueOf(oldPwdField.getPassword());
        String newPwd = String.valueOf(newPwdField.getPassword());
        String confirmPwd = String.valueOf(confirmPwdField.getPassword());
        if(!newPwd.equals(confirmPwd)){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập nhập mật khẩu mới và xác nhận giống nhau");
            return;
        }
        if(!currentAcc.getPasswd().equals(oldPwd)){
            JOptionPane.showMessageDialog(this, "Mật khẫu hiện tại không chính xác");
            return;
        }

        // Handle change password
        currentAcc.setPasswd(newPwd);
        if(!AccountDAO.update(currentAcc)){
            currentAcc.setPasswd(oldPwd);
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình cập nhật mật khẩu mới");
            return;
        }
        this.changedPasswordFlag = true;
        dispose();
    }


    private void onCancel() {
        dispose();
    }
}

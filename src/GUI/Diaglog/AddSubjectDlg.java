package GUI.Diaglog;

import GUI.Tabs.ClazzTabMod;
import GUI.Tabs.SubjectTabMod;
import dao.ClazzDAO;
import dao.SubjectDAO;
import pojo.Clazz;
import pojo.Subject;

import javax.swing.*;
import java.awt.event.*;

public class AddSubjectDlg extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField subjectCodeTextFeild;
    private JTextField subjectNameTextField;
    private JTextField subjectCreditTextField;

    public AddSubjectDlg() {
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
    private void addEventListener(){
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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
        String code = subjectCodeTextFeild.getText();
        String name = subjectNameTextField.getText();
        int credits = Integer.parseInt(subjectCreditTextField.getText());

        Subject subject = new Subject(code,name,credits);
        if(SubjectDAO.add(subject)){
            JOptionPane.showMessageDialog(null, "Thêm mới thành công",
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
            SubjectTabMod.mTableManager.addRow(subject);
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}

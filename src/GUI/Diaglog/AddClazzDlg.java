package GUI.Diaglog;

import GUI.Tabs.ClazzTabMod;
import GUI.Tabs.StudentTabMod;
import dao.ClazzDAO;
import pojo.Clazz;
import pojo.Student;

import javax.swing.*;
import java.awt.event.*;

public class AddClazzDlg extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField classCodeTextFeild;
    private JTextField yearTextField;

    public AddClazzDlg() {
        this.setTitle("Tạo mới lớp học");
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
        buttonOK.addActionListener(e-> onOK());

        buttonCancel.addActionListener(e -> onCancel());

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
        String classCode = classCodeTextFeild.getText();
        int classYear = Integer.parseInt(yearTextField.getText());
        Clazz newClass = new Clazz(classCode,classYear);

        if(ClazzDAO.add(newClass)){
            ClazzTabMod.mTableManager.addRow(newClass);
            StudentTabMod.initClassData();
            JOptionPane.showMessageDialog(null, "Thêm mới thành công","Thành công", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    private void onCancel() {
        dispose();
    }
}

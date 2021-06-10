package GUI.Diaglog;

import GUI.Tabs.SubjectTabMod;
import dao.SubjectDAO;
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
    String command;
    int selectedRow;
    public AddSubjectDlg() {
        this.command = "Add";
        setTitle("Thêm mới");
        buttonOK.setText("Thêm");
        initDlgData();
    }
    public AddSubjectDlg(int seletedRow, Subject subject) {
        this.command = "update";
        this.selectedRow = seletedRow;
        subjectCodeTextFeild.setText(subject.getSubjectId());
        subjectNameTextField.setText(subject.getSubjectName());
        subjectCreditTextField.setText(String.valueOf(subject.getCredits()));
        setTitle("Cập nhật");
        buttonOK.setText("Cập nhật");
        initDlgData();
    }
    private void initDlgData(){
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
                omCommand(command);
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
    private void omCommand(String command){
        String code = subjectCodeTextFeild.getText();
        String name = subjectNameTextField.getText();
        int credits = Integer.parseInt(subjectCreditTextField.getText());
        Subject subject = new Subject(code,name,credits);

       if(command.toLowerCase().equals("add")){
           if(SubjectDAO.add(subject)){
               JOptionPane.showMessageDialog(null, "Thêm mới thành công",
                       "Thành công", JOptionPane.INFORMATION_MESSAGE);
               dispose();
               // Add Row to UI
               SubjectTabMod.mTableManager.addRow(subject);
               return;
           }
       }
        if(command.toLowerCase().equals("update")){

            if(SubjectDAO.update(subject)){
                JOptionPane.showMessageDialog(null, "Cập nhật thành công",
                        "Thành công", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                SubjectTabMod.mTableManager.updateRow(selectedRow, subject);
                return;
            }
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}

package GUI;

import javax.swing.*;
import java.awt.event.*;

public class AddModertatorDlg extends JDialog {
    private JPanel contentPane;
    private JButton createModBtn;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton button1;
    private JComboBox comboBox1;
    private JTextField textField5;

    public AddModertatorDlg() {
        this.setContentPane(contentPane);
        this.getRootPane().setDefaultButton(createModBtn);
        this.setModal(true);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        createModBtn.addActionListener(new ActionListener() {
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
        // add your code here
        this.dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        this.dispose();
    }

    public static void main(String[] args) {
        AddModertatorDlg dialog = new AddModertatorDlg();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

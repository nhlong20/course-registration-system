package GUI.Diaglog;

import GUI.ModeratorGUI;
import GUI.TableManager.ModeratorTableManager;
import com.toedter.calendar.JDateChooser;
import dao.ModeratorDAO;
import org.dom4j.rule.Mode;
import pojo.Moderator;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddModertatorDlg extends JDialog implements ActionListener{
    private JPanel contentPane;
    private JButton createModBtn;
    private JButton buttonCancel;
    private JTextField nameTextField;
    private JTextField idTextField;
    private JTextField addressTextField;
    private JComboBox genderComboBox;
    private JTextField phoneTextField;
    private JPanel dobDatePanel;
    Calendar calendar;
    private JDateChooser dateChooser;
    public AddModertatorDlg() {
        this.setContentPane(contentPane);
        this.getRootPane().setDefaultButton(createModBtn);

        genderComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Nam", "Nữ"}));
        calendar = Calendar.getInstance();
        dateChooser = new JDateChooser(calendar.getTime());
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dobDatePanel.add(dateChooser);

        this.setModal(true);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);

        createModBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCreateModerator();
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
        setVisible(true);
    }

    private void onCreateModerator() {
        // add your code here
        Moderator moderator = new Moderator();
        moderator.setFullname(nameTextField.getText());
        moderator.setModeratorId(idTextField.getText());
        moderator.setPhone(phoneTextField.getText());
        moderator.setModAddress(addressTextField.getText());
        moderator.setGender(String.valueOf(genderComboBox.getSelectedItem()));
        // Format Date again befor save to DB
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-yy");
        String date = simpleDateFormat.format(dateChooser.getDate());
        moderator.setDob(Date.valueOf(date));

        if(ModeratorDAO.addModerator(moderator)){
            ModeratorGUI.moderatorTableManager.addRow(moderator);
            JOptionPane.showMessageDialog(null, "Thêm mới thành công",
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }

    }

    private void onCancel() {
        // add your code here if necessary
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.print("Clicdsdsdsked nè");
    }
}

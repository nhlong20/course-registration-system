package GUI.Diaglog;

import GUI.Tabs.ModeratorTabMod;
import com.toedter.calendar.JDateChooser;
import dao.ModeratorDAO;
import pojo.Moderator;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddModertatorDlg extends JDialog {
    private JPanel contentPane;
    private JButton OkBtn;
    private JButton buttonCancel;
    private JTextField nameTextField;
    private JTextField idTextField;
    private JTextField addressTextField;
    private JComboBox genderComboBox;
    private JTextField phoneTextField;
    private JPanel dobDatePanel;
    Calendar calendar;
    private JDateChooser dateChooser;
    private Moderator moderator;

    public AddModertatorDlg() {
        this.setTitle("Thêm giáo vụ mới");
        this.initComponents();
        this.moderator = null;
        dobDatePanel.add(dateChooser);
        this.addEventListener();
        this.setModal(true);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public AddModertatorDlg(Moderator moderator) {
        this.moderator = moderator;
        idTextField.setEditable(false);
        this.setTitle("Cập nhật thông tin giáo vụ");
        this.initComponents();
        this.initModData();
        this.addEventListener();
        this.setModal(true);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initModData() {
        idTextField.setText(moderator.getModeratorId());
        nameTextField.setText(moderator.getFullname());
        dateChooser.setDate(moderator.getDob());
        dobDatePanel.add(dateChooser);
        phoneTextField.setText(moderator.getPhone());
        addressTextField.setText(moderator.getModAddress());
        genderComboBox.setSelectedItem(moderator.getGender());
    }

    private void initComponents() {
        this.setContentPane(contentPane);
        this.getRootPane().setDefaultButton(OkBtn);
        genderComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Nam", "Nữ"}));
        calendar = Calendar.getInstance();
        dateChooser = new JDateChooser(calendar.getTime());
        dateChooser.setDateFormatString("dd/MM/yyyy");
    }

    private void addEventListener() {
        OkBtn.addActionListener(e -> {
            if (moderator == null) onAdd();
            else onUpdate();
        });

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onAdd() {
        Moderator moderator = new Moderator();
        moderator.setFullname(nameTextField.getText());
        moderator.setModeratorId(idTextField.getText());
        moderator.setPhone(phoneTextField.getText());
        moderator.setModAddress(addressTextField.getText());
        moderator.setGender(String.valueOf(genderComboBox.getSelectedItem()));
        // Format Date again befor save to DB
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(dateChooser.getDate());
        moderator.setDob(Date.valueOf(date));

        if (ModeratorDAO.add(moderator)) {
            ModeratorTabMod.mTableManager.addRow(moderator);
            JOptionPane.showMessageDialog(null, "Thêm mới thành công",
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }

    private void onUpdate() {
        moderator.setFullname(nameTextField.getText());
        moderator.setPhone(phoneTextField.getText());
        moderator.setModAddress(addressTextField.getText());
        moderator.setGender(String.valueOf(genderComboBox.getSelectedItem()));
        // Format Date again befor save to DB
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(dateChooser.getDate());
        moderator.setDob(Date.valueOf(date));

        if (ModeratorDAO.update(moderator)) {
            JOptionPane.showMessageDialog(null, "Cập nhật thành công",
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }

    private void onCancel() {
        this.dispose();
    }
}

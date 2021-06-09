package GUI.Diaglog;

import GUI.Tabs.SemesterTabMod;
import GUI.Tabs.StudentTabMod;
import com.toedter.calendar.JDateChooser;
import dao.ClazzDAO;
import dao.SemesterDAO;
import dao.StudentDAO;
import pojo.Clazz;
import pojo.Semester;
import pojo.Student;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddStudentDlg extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField studentIdTextField;
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JPanel dobPanel;
    private JComboBox genderComboBox;
    private JComboBox clazzComboBox;

    Calendar calendar;
    private JDateChooser dateChooser;

    public AddStudentDlg() {
        this.initComponents();
        this.addEventListener();
        this.setModal(true);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
    private void initComponents(){
        this.setTitle("Thêm sinh viên mới");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        List<Clazz> clazzes = ClazzDAO.getAll();
        List<String> clazzNames = new ArrayList<>();
        for(Clazz clazz : clazzes){
            clazzNames.add(clazz.getClassCode());
        }
        clazzComboBox.setModel(new DefaultComboBoxModel<>(clazzNames.toArray()));

        genderComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Nam", "Nữ"}));
        calendar = Calendar.getInstance();
        dateChooser = new JDateChooser(calendar.getTime());
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dobPanel.add(dateChooser);
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
        String studentId = studentIdTextField.getText();
        String name = nameTextField.getText();
        String gender = String.valueOf(genderComboBox.getSelectedItem());
        String address = addressTextField.getText();
        String classCode = String.valueOf(clazzComboBox.getSelectedItem());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = Date.valueOf(simpleDateFormat.format(dateChooser.getDate()));

        Clazz clazz = ClazzDAO.get(classCode);
        if(clazz==null){
            JOptionPane.showMessageDialog(this, "Lớp học không tồn tại, vui lòng thử lại");
            return;
        }
        Student student = new Student(studentId,name,gender,dob,address,clazz);
        if(StudentDAO.add(student)){
            // Update UI if current shown Class is the same
            if(StudentTabMod.currentShownClass.equals(classCode)){
                StudentTabMod.mTableManager.addRow(student);
            }
            JOptionPane.showMessageDialog(this, "Thêm mới thành công");
            dispose();
        }
    }

    private void onCancel() {
        dispose();
    }
}

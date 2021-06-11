package GUI;

import GUI.Diaglog.ChangPasswordDlg;
import GUI.Tabs.CourseRegResultTabStu;
import GUI.Tabs.RegCourseTabStu;
import com.toedter.calendar.JDateChooser;
import dao.*;
import main.MainApp;
import pojo.Account;
import pojo.CourseRegistrationSession;
import pojo.Semester;
import pojo.Student;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GUI
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/4/2021 - 9:41 PM
 * @Description
 */
public class StudentGUI extends JFrame {
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JButton logoutBtn;
    private JButton changePassBtn;
    private JButton updateInfoBtn;
    private JTextField studentIdTextField;
    private JLabel userFullnameLabel;
    private JTextField nameTextField;
    private JComboBox genderComboBox;
    private JTextField addressTextField;

    private JLabel regTimeLabel;
    private JTextField searchCourseTextField;
    private JButton searchCourseBtn;
    private JButton regCourseBtn;
    private JTable courseTable;

    private JTable resRegTable;
    private JButton cancelCourseBtn;

    private JPanel studentDobPanel;
    private JTextField clazzTextField;
    private JLabel registerTimeLabel;
    private JLabel currentSemLabel;

    Calendar calendar;
    private JDateChooser dateChooser;

    private Account currentAccount;
    private Student currentUser;

    public StudentGUI() {
        super("Hệ thống đăng ký khoá học");
        this.initComponentData();
        this.linkRegCourseHandler();
        this.linkCourseRegResultHandler();
        this.addEventListener();
        this.initComponents();
    }

    private void linkRegCourseHandler() {
        RegCourseTabStu regCourseTabStu = new RegCourseTabStu(regTimeLabel, searchCourseTextField, searchCourseBtn, regCourseBtn, courseTable);
        regCourseTabStu.initUIData();
        regCourseTabStu.addModActionlistener();
    }
    private void linkCourseRegResultHandler() {
        CourseRegResultTabStu courseRegResultTabStu = new CourseRegResultTabStu(resRegTable, cancelCourseBtn, currentUser);
        courseRegResultTabStu.initUIData();
        courseRegResultTabStu.addModActionlistener();
    }


    private void addEventListener() {
        updateInfoBtn.addActionListener(e -> onUpdateInfo());
        changePassBtn.addActionListener(e -> onChangePassword());
        logoutBtn.addActionListener(e -> onLogout());
    }


    private void initComponents() {
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponentData() {
        // TODO - remove comment of the following line
        currentAccount = AccountDAO.getAccount("18120449", "18120449");
//        currentAccount = MainApp.getCurrentAccount();
        currentUser = StudentDAO.getByStudentId(currentAccount.getUsername());
        Semester semester = SemesterDAO.getCurrent();
        if (semester != null)
            currentSemLabel.setText(semester.getSemName() + " - " + semester.getSemYear());
        // Get and set current session
        setRegTimeLabel(registerTimeLabel);

        userFullnameLabel.setText(currentUser.getFullname());
        userFullnameLabel.setForeground(Color.red);
        studentIdTextField.setText(currentUser.getStudentId());
        nameTextField.setText(currentUser.getFullname());
        genderComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Nam", "Nữ"}));
        genderComboBox.setSelectedItem(currentUser.getGender());

        addressTextField.setText(currentUser.getStuAddress());
        clazzTextField.setText(currentUser.getClazz().getClassCode());
        // Set DOB
        calendar = Calendar.getInstance();
        dateChooser = new JDateChooser(calendar.getTime());
        dateChooser.setDate(currentUser.getDob());
        dateChooser.setDateFormatString("dd/MM/yyyy");
        studentDobPanel.add(dateChooser);
    }

    public static void setRegTimeLabel(JLabel registerTimeLabel) {
        CourseRegistrationSession curSession = CourseRegistrationSessionDAO.getCurrentSession();
        if(curSession!=null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String startDate = simpleDateFormat.format(curSession.getStartDate());
            String endDate = simpleDateFormat.format(curSession.getEndDate());
            String registerTime = "Từ " + startDate + " đến " + endDate;
            registerTimeLabel.setText(registerTime);
            registerTimeLabel.setForeground(Color.blue);
        }
    }

    private void onChangePassword() {
        ChangPasswordDlg changPasswordDlg = new ChangPasswordDlg(currentAccount);
        if (changPasswordDlg.isSucess()) {
            JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu thành công, vui lòng đăng nhập để tiếp tục");
            onLogout();
        }
    }

    private void onLogout() {
        MainApp.setCurrentAccount(null);
        dispose();
        MainApp.invokeGUI(MainApp.ViewControl.LOGIN);
    }

    private void onUpdateInfo() {
        // Get DOB
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = Date.valueOf(simpleDateFormat.format(dateChooser.getDate()));

        currentUser.setFullname(nameTextField.getText());
        currentUser.setStuAddress(addressTextField.getText());
        currentUser.setDob(dob);
        currentUser.setGender(String.valueOf(genderComboBox.getSelectedItem()));

        if (StudentDAO.update(currentUser)) {
            JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thành công");
        }
    }
}

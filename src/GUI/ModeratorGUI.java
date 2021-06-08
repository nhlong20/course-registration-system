package GUI;


import GUI.Diaglog.AddModertatorDlg;
import GUI.TableManager.ModeratorTableManager;
import GUI.Tabs.*;
import com.toedter.calendar.JDateChooser;
import dao.AccountDAO;
import dao.ClazzDAO;
import dao.ModeratorDAO;
import dao.SemesterDAO;
import main.MainApp;
import pojo.Account;
import pojo.Moderator;
import pojo.Semester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

/**
 * GUI
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/4/2021 - 9:40 PM
 * @Description
 */
public class ModeratorGUI extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane mainTab;
    private JTextField searchModTextField;
    private JTable modTable;
    private JButton searchModBtn;
    private JButton deleteModBtn;
    private JButton addModBtn;
    private JLabel accountName;

    private JTextField searchSubjectTextField;
    private JTable subjectTable;
    private JButton updateSubjectBtn;
    private JButton searchSubjectBtn;
    private JButton deleteSubjectBtn;
    private JButton addSubjectBtn;

    private JButton setAsCurrentSemesterBtn;
    private JTextField searchSemesterTextField;
    private JTable semesterTable;
    private JButton searchSemesterBtn;
    private JButton deleteSemesterBtn;
    private JButton addSemesterBtn;

    private JButton addClassBtn;
    private JButton deleteClassBtn;
    private JTable classTable;

    private JTextField searchStudentTextField;
    private JButton searchStudentBtn;
    private JComboBox classBomboBox;
    private JTable studentTable;
    private JButton deleteStudentBtn;
    private JButton addStudentBtn;
    private JButton updateStudentbtn;
    private JButton resetStudentPwdBtn;

    private JButton openSessionBtn;
    private JButton endSessionBtn;
    private JTable sessionTable;

    private JTable courseTable;
    private JButton addCourseBtn;
    private JButton deleteCourseBtn;
    private JButton listRegistrationBtn;

    private JTabbedPane settingTab;
    private JComboBox userGenderComboBox;
    private JButton updateUserInfoBtn;
    private JButton searchCourseBtn;
    private JLabel curentSemester;

    Calendar calendar;
    private JDateChooser dateChooser;
    private JTextField userIdTextField;
    private JTextField userFullnameTextField;
    private JPanel userDOBPanel;
    private JTextField userPhoneTextField;
    private JTextField userAddressTextField;

    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JPasswordField oldPasswordField;
    private JButton changePasswordBtn;

    private JButton logoutBtn;
    private JLabel currentSemesterLabel;
    private Account currentAcc;

    public static String MODERATOR_WINDOW_TITLE_TEXT = "Hệ thống đăng ký khoá học";

    public ModeratorGUI() {
        super(MODERATOR_WINDOW_TITLE_TEXT);
        this.initData();
        this.linkModeratorTabHandler();
        this.linkSubjectTabHandler();
        this.linkSemesterTabHandler();
        this.linkClazzTabHandler();
        this.linkCourseRegistrationSessionHandler();
        this.linkStudentHandler();
        this.linkCourseHandler();

        this.editInfoHandler();
        changePasswordBtn.addActionListener(e -> changePasswordHanlder());
        logoutBtn.addActionListener(e -> logoutHandler());

        this.initUIProperty();
    }
    private void initData(){
        currentAcc = AccountDAO.getAccount("MOD002", "giaovu");
        Moderator currentUser = ModeratorDAO.getModerator(currentAcc.getUsername());
        accountName.setText(currentUser.getFullname());

        userIdTextField.setText(currentUser.getModeratorId());
        userFullnameTextField.setText(currentUser.getFullname());
        userAddressTextField.setText(currentUser.getModAddress());
        userPhoneTextField.setText(currentUser.getPhone());

        // Set DOB
        calendar = Calendar.getInstance();
        dateChooser = new JDateChooser(calendar.getTime());
        dateChooser.setDate(currentUser.getDob());
        dateChooser.setDateFormatString("dd/MM/yyyy");
        userDOBPanel.add(dateChooser);

    }
    private void initUIProperty() {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(1920 / 2 - 200, 1080 / 2 - 200);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void linkModeratorTabHandler() {
        ModeratorTabMod moderatorTabMod = ModeratorTabMod.getInstance(searchModTextField, modTable, searchModBtn, deleteModBtn, addModBtn);
        moderatorTabMod.initUIModData();
        moderatorTabMod.addModActionlistener();
    }

    private void linkSubjectTabHandler() {
        SubjectTabMod subjectTabMod = SubjectTabMod.getInstance(searchSubjectTextField, subjectTable, searchSubjectBtn, deleteSubjectBtn, addSubjectBtn, updateSubjectBtn);
        subjectTabMod.initUIData();
        subjectTabMod.addModActionlistener();
    }

    private void linkSemesterTabHandler() {
        SemesterTabMod semesterTabMod = SemesterTabMod.getInstance(searchSemesterTextField, semesterTable, searchSemesterBtn, deleteSemesterBtn, addSemesterBtn, setAsCurrentSemesterBtn, currentSemesterLabel);
        semesterTabMod.initUIData();
        semesterTabMod.addModActionlistener();
    }

    private void linkClazzTabHandler() {
        ClazzTabMod clazzTabMod = ClazzTabMod.getInstance(classTable, deleteClassBtn, addClassBtn);
        clazzTabMod.initUIData();
        clazzTabMod.addModActionlistener();
    }
    private void linkStudentHandler() {
        StudentTabMod studentTabMod = StudentTabMod.getInstance(searchStudentTextField, studentTable, searchStudentBtn, classBomboBox, addStudentBtn, updateStudentbtn, resetStudentPwdBtn,deleteStudentBtn);
        studentTabMod.initUIData();
        studentTabMod.addModActionlistener();
    }

    private void linkCourseRegistrationSessionHandler() {
        CourseRegistrationSessionTabMod courseRegistrationSessionTabMod = CourseRegistrationSessionTabMod.getInstance(sessionTable, openSessionBtn, endSessionBtn);
        courseRegistrationSessionTabMod.initUIData();
        courseRegistrationSessionTabMod.addModActionlistener();
    }

    private void linkCourseHandler() {

    }

    private void editInfoHandler() {

    }

    private void changePasswordHanlder() {
        String oldPwd = String.valueOf(oldPasswordField.getPassword());
        String newPwd = String.valueOf(newPasswordField.getPassword());
        String confirmPwd = String.valueOf(confirmPasswordField.getPassword());
        if(!newPwd.equals(confirmPwd)){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập nhập mật khẩu mới và xác nhận giống nhau");
            return;
        }
        if(!currentAcc.getPasswd().equals(oldPwd)){
            JOptionPane.showMessageDialog(this, "Mật khẫu hiện tại không chính xác");
            return;
        }

        // Handle change pasword
        currentAcc.setPasswd(newPwd);
        if(!AccountDAO.update(currentAcc)){
            currentAcc.setPasswd(oldPwd);
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình cập nhật mật khẩu mới");
            return;
        }
        JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu thành công, vui lòng đăng nhập lại");

        // Logout user after change password
        logoutHandler();
    }

    private void logoutHandler() {
        MainApp.setCurrentAccount(null);
        dispose();
        MainApp.invokeGUI(MainApp.ViewControl.LOGIN);
    }
}

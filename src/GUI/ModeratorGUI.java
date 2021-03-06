package GUI;


import GUI.TableManager.ModeratorTableManager;
import GUI.Tabs.*;
import com.toedter.calendar.JDateChooser;
import dao.AccountDAO;
import dao.ModeratorDAO;
import main.MainApp;
import org.jboss.jandex.Main;
import pojo.Account;
import pojo.Moderator;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
    private JTextField searchModTextField;
    private JTable modTable;
    private JButton searchModBtn;
    private JButton deleteModBtn;
    private JButton addModBtn;
    private JLabel accountName;
    private JButton updateModBtn;
    private JButton resetModPassBtn;

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
    private JButton addStudentBtn;
    private JButton updateStudentbtn;
    private JButton resetStudentPwdBtn;

    private JButton openSessionBtn;
    private JTable sessionTable;

    private JTable courseTable;
    private JButton addCourseBtn;
    private JButton deleteCourseBtn;
    private JButton listRegistrationBtn;
    private JLabel currentSemLabel;
    private JTextField searchCourseTextField;
    private JButton searchCourseBtn;

    private JComboBox userGenderComboBox;
    private JButton updateUserInfoBtn;

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
    private JLabel currentSemLabel2;
    private Account currentAcc;
    Moderator currentUser;

    public static String MODERATOR_WINDOW_TITLE_TEXT = "H??? th???ng ????ng k?? kho?? h???c";

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

        this.addEventListener();
        this.initUIProperty();
    }

    private void addEventListener() {
        updateUserInfoBtn.addActionListener(e -> editInfoHandler());
        changePasswordBtn.addActionListener(e -> changePasswordHanlder());
        logoutBtn.addActionListener(e -> logoutHandler());
    }

    private void initData() {
        currentAcc = MainApp.getCurrentAccount();
        currentUser = ModeratorDAO.get(currentAcc.getUsername());
        accountName.setText(currentUser.getFullname());

        userIdTextField.setText(currentUser.getModeratorId());
        userFullnameTextField.setText(currentUser.getFullname());
        userPhoneTextField.setText(currentUser.getPhone());
        userAddressTextField.setText(currentUser.getModAddress());
        userGenderComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Nam", "N???"}));
        userGenderComboBox.setSelectedItem(currentUser.getGender());
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
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void linkModeratorTabHandler() {
        ModeratorTabMod moderatorTabMod = new ModeratorTabMod(searchModTextField, modTable, searchModBtn, deleteModBtn, addModBtn, updateModBtn, resetModPassBtn);
        moderatorTabMod.initUIModData();
        moderatorTabMod.addModActionlistener();
    }

    private void linkSubjectTabHandler() {
        SubjectTabMod subjectTabMod = new SubjectTabMod(searchSubjectTextField, subjectTable, searchSubjectBtn, deleteSubjectBtn, addSubjectBtn, updateSubjectBtn);
        subjectTabMod.initUIData();
        subjectTabMod.addModActionlistener();
    }

    private void linkSemesterTabHandler() {
        SemesterTabMod semesterTabMod = new SemesterTabMod(searchSemesterTextField, semesterTable, searchSemesterBtn, deleteSemesterBtn, addSemesterBtn, setAsCurrentSemesterBtn, currentSemesterLabel);
        semesterTabMod.initUIData();
        semesterTabMod.addModActionlistener();
    }

    private void linkClazzTabHandler() {
        ClazzTabMod clazzTabMod = new ClazzTabMod(classTable, deleteClassBtn, addClassBtn);
        clazzTabMod.initUIData();
        clazzTabMod.addModActionlistener();
    }

    private void linkStudentHandler() {
        StudentTabMod studentTabMod = new StudentTabMod(searchStudentTextField, studentTable, searchStudentBtn, classBomboBox, addStudentBtn, updateStudentbtn, resetStudentPwdBtn);
        studentTabMod.initUIData();
        studentTabMod.addModActionlistener();
    }

    private void linkCourseRegistrationSessionHandler() {
        CourseRegistrationSessionTabMod courseRegistrationSessionTabMod = new CourseRegistrationSessionTabMod(sessionTable, openSessionBtn, currentSemLabel2);
        courseRegistrationSessionTabMod.initUIData();
        courseRegistrationSessionTabMod.addModActionlistener();
    }

    private void linkCourseHandler() {
        CourseTabMod courseTabMod = new CourseTabMod(searchCourseTextField, courseTable, searchCourseBtn, currentSemLabel, listRegistrationBtn, addCourseBtn, deleteCourseBtn);
        courseTabMod.initUIData();
        courseTabMod.addModActionlistener();

    }

    private void editInfoHandler() {
        // Get DOB
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = Date.valueOf(simpleDateFormat.format(dateChooser.getDate()));

        currentUser.setFullname(userFullnameTextField.getText());
        currentUser.setModAddress(userAddressTextField.getText());
        currentUser.setPhone(userPhoneTextField.getText());
        currentUser.setGender(String.valueOf(userGenderComboBox.getSelectedItem()));
        currentUser.setDob(dob);
        if (ModeratorDAO.update(currentUser)) {
            JOptionPane.showMessageDialog(this, "C???p nh???t d??? li???u th??nh c??ng");
            ModeratorTableManager.refresh();
            accountName.setText(currentUser.getFullname());
            return;
        }
    }

    private void changePasswordHanlder() {
        String oldPwd = String.valueOf(oldPasswordField.getPassword());
        String newPwd = String.valueOf(newPasswordField.getPassword());
        String confirmPwd = String.valueOf(confirmPasswordField.getPassword());
        if (!newPwd.equals(confirmPwd)) {
            JOptionPane.showMessageDialog(this, "Vui l??ng nh???p nh???p m???t kh???u m???i v?? x??c nh???n gi???ng nhau");
            return;
        }
        if (!currentAcc.getPasswd().equals(oldPwd)) {
            JOptionPane.showMessageDialog(this, "M???t kh???u hi???n t???i kh??ng ch??nh x??c");
            return;
        }

        // Handle change pasword
        currentAcc.setPasswd(newPwd);
        if (!AccountDAO.update(currentAcc)) {
            currentAcc.setPasswd(oldPwd);
            JOptionPane.showMessageDialog(this, "C?? l???i x???y ra trong qu?? tr??nh c???p nh???t m???t kh???u m???i");
            return;
        }
        JOptionPane.showMessageDialog(this, "C???p nh???t m???t kh???u th??nh c??ng, vui l??ng ????ng nh???p l???i");

        // Logout user after change password
        logoutHandler();
    }

    private void logoutHandler() {
        MainApp.setCurrentAccount(null);
        dispose();
        MainApp.invokeGUI(MainApp.ViewControl.LOGIN);
    }
}

package GUI;



import GUI.Diaglog.AddModertatorDlg;
import GUI.TableManager.ModeratorTableManager;
import GUI.Tabs.*;
import dao.ClazzDAO;
import dao.ModeratorDAO;
import pojo.Moderator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    private JTextField searchSemesterTextField;
    private JTable semesterTable;
    private JButton searchSemesterBtn;
    private JButton deleteSemesterBtn;
    private JButton addSemesterBtn;

    private JButton addClassBtn;
    private JButton deleteClassBtn;
    private JTable classTable;

    private JComboBox classBomboBox;
    private JTable studentTable;
    private JTextField textField10;
    private JButton searchBtn;
    private JButton openSessionBtn;
    private JButton endSessionBtn;
    private JTable sessionTable;
    private JButton deleteStudentBtn;
    private JButton addStudentBtn;
    private JButton updateStudentbtn;
    private JTable courseTable;
    private JButton addCourseBtn;
    private JButton deleteCourseBtn;
    private JButton listRegistrationBtn;
    private JTabbedPane settingTab;
    private JButton changePasswordBtn;
    private JComboBox comboBox2;
    private JButton updateUserInfoBtn;
    private JButton logoutBtn;
    private JButton selectDOBBtn;
    private JButton searchCourseBtn;
    private JButton đặtLàmHọcKỳButton;
    private JLabel curentSemester;
    private JTextField oldPasswordTextField;
    private JTextField newPasswordTextField;
    private JTextField confirmPasswordTextField;


    public static String MODERATOR_WINDOW_TITLE_TEXT = "Hệ thống đăng ký khoá học";

    public ModeratorGUI() {
        super(MODERATOR_WINDOW_TITLE_TEXT);
        this.linkModeratorTabHandler();
        this.linkSubjectTabHandler();
        this.linkSemesterTabHandler();
        this.linkClazzTabHandler();
        this.linkCourseRegistrationSessionHandler();

        this.initUIProperty();

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
    private void linkModeratorTabHandler(){
        ModeratorTabMod moderatorTabMod = ModeratorTabMod.getInstance(searchModTextField, modTable, searchModBtn, deleteModBtn, addModBtn, accountName);
        moderatorTabMod.initUIModData();
        moderatorTabMod.addModActionlistener();
    }
    private void linkSubjectTabHandler(){
        SubjectTabMod subjectTabMod = SubjectTabMod.getInstance(searchSubjectTextField, subjectTable, searchSubjectBtn,deleteSubjectBtn, addSubjectBtn, updateSubjectBtn);
        subjectTabMod.initUIData();
        subjectTabMod.addModActionlistener();
    }
    private void linkSemesterTabHandler(){
        SemesterTabMod semesterTabMod = SemesterTabMod.getInstance(searchSemesterTextField, semesterTable, searchSemesterBtn,deleteSemesterBtn, addSemesterBtn);
        semesterTabMod.initUIData();
        semesterTabMod.addModActionlistener();
    }
    private void linkClazzTabHandler(){
        ClazzTabMod clazzTabMod = ClazzTabMod.getInstance(classTable,deleteClassBtn, addClassBtn);
        clazzTabMod.initUIData();
        clazzTabMod.addModActionlistener();
    }
    private void linkCourseRegistrationSessionHandler(){
        CourseRegistrationSessionTabMod courseRegistrationSessionTabMod = CourseRegistrationSessionTabMod.getInstance(sessionTable, openSessionBtn, endSessionBtn);
        courseRegistrationSessionTabMod.initUIData();
        courseRegistrationSessionTabMod.addModActionlistener();
    }
}

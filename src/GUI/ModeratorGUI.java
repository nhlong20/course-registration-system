package GUI;

import javax.swing.*;

/**
 * GUI
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/4/2021 - 9:40 PM
 * @Description
 */
public class ModeratorGUI extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane studentTable;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton updateInfoBtn;
    private JTable modTable;
    private JButton searchModBtn;
    private JButton deleteModBtn;
    private JButton addModBtn;
    private JTextField textField8;
    private JTable subjectTable;
    private JButton searchSubjectBtn;
    private JButton deleteCourseTable;
    private JButton addCourseTable;
    private JTextField textField9;
    private JTable semesterTable;
    private JButton tìmKiếmButton;
    private JButton xoáButton2;
    private JButton thêmButton2;
    private JButton addClassBtn;
    private JButton deleteClassBtn;
    private JTable classTable;
    private JComboBox comboBox1;
    private JTable table4;
    private JTextField textField10;
    private JButton searchBtn;
    private JButton openSessionBtn;
    private JButton endSessionBtn;
    private JTable sessionTable;
    private JButton deleteStudentBtn;
    private JButton addStudentBtn;
    private JButton updateStudentbtn;
    private JButton changePasswordBtn;
    private JButton button1;
    private JButton logoutBtn;
    private JRadioButton namRadioButton;
    private JRadioButton nữRadioButton;
    private JTable courseTable;
    private JButton addCourseBtn;
    private JButton deleteCourseBtn;
    private JButton listRegistrationBtn;

    public ModeratorGUI(){
        super("Hệ thống đăng ký khoá học");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(1920 / 2 - 200, 1080 / 2 - 200);
        this.pack();
        this.setVisible(true);
    }
}

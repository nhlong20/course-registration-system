package GUI;

import GUI.Diaglog.AddModertatorDlg;
import GUI.TableManager.ModeratorTableManager;
import dao.ModeratorDAO;
import main.MainApp;
import pojo.Account;
import pojo.Moderator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField textField1;
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
    private JLabel accountName;
    public static ModeratorTableManager moderatorTableManager;

    public ModeratorGUI() {
        super("Hệ thống đăng ký khoá học");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(1920 / 2 - 200, 1080 / 2 - 200);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        this.setVisible(true);

//        Account currentAccount = MainApp.getCurrentAccount();
//        Moderator currentUser = ModeratorDAO.getModerator(currentAccount.getUsername());
        Moderator currentUser = ModeratorDAO.getModerator("MOD001");
        accountName.setText(currentUser.getFullname());

        moderatorTableManager = new ModeratorTableManager(modTable);
        moderatorTableManager.loadTableData();

        addModBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddMod();
            }
        });

        searchModBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    private void onAddMod() {
        AddModertatorDlg addModertatorDlg = new AddModertatorDlg();
    }
}

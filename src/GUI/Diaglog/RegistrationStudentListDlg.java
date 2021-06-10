package GUI.Diaglog;

import GUI.TableManager.RegStudentListTableManager;
import pojo.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationStudentListDlg extends JDialog {
    public static String SEARCH_PLACEHOLDER_TEXT = "Tìm kiếm bằng MSSV hoặc tên sinh viên ";
    private Course course;
    private JPanel contentPane;
    private JTextField searchCourseRegTextField;
    private JButton searchCourseRegBtn;
    private RegStudentListTableManager mTableManager;
    private JTable courseRegTable;
    private JLabel courseRegLabel;

    public RegistrationStudentListDlg() {
    }
    public RegistrationStudentListDlg(Course selectedCourse){
        this.setTitle("Danh sách sinh viên đăng ký học phần");
        this.course = selectedCourse;
        this.initComponents();
        this.addEventListener();
        setContentPane(contentPane);
        this.setModal(true);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        getContentPane().requestFocusInWindow();
        setVisible(true);
    }
    public void initComponents() {
        mTableManager = new RegStudentListTableManager(courseRegTable);
        mTableManager.loadTableData();
        searchCourseRegTextField.setText(SEARCH_PLACEHOLDER_TEXT);
        courseRegLabel.setText(course.getCourseId() +" - "+ course.getSubject().getSubjectName());
        courseRegLabel.setForeground(Color.red);
    }
    private  void addEventListener(){
        searchCourseRegBtn.addActionListener(e -> onSearch());

        searchCourseRegTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onSearch();
                }
            }
        });
        searchCourseRegTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (searchCourseRegTextField.getText().equals(SEARCH_PLACEHOLDER_TEXT)) {
                    searchCourseRegTextField.setText("");
                    searchCourseRegTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (searchCourseRegTextField.getText().isEmpty()) {
                    searchCourseRegTextField.setForeground(Color.GRAY);
                    searchCourseRegTextField.setText(SEARCH_PLACEHOLDER_TEXT);
                }
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onSearch(){

        String userQuery = searchCourseRegTextField.getText();
        System.out.println(userQuery);
        if (userQuery.equals(SEARCH_PLACEHOLDER_TEXT)) userQuery = "";
        mTableManager.filterData(userQuery);
    }
}

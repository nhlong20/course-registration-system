package GUI.Tabs;

import GUI.StudentGUI;
import GUI.TableManager.CourseTableManager;
import dao.*;
import main.MainApp;
import pojo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

/**
 * GUI.Tabs
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/10/2021 - 3:53 PM
 * @Description
 */
public class RegCourseTabStu {
    public static String SEARCH_PLACEHOLDER_TEXT = "Tìm kiếm bằng ID hoặc tên học phần";
    public static CourseTableManager mTableManager;
    private JLabel regTimeLabel;
    private JTextField searchCourseTextField;
    private JButton searchCourseBtn;
    private JButton regCourseBtn;
    private JTable courseTable;

    public RegCourseTabStu() {
    }

    public RegCourseTabStu(JLabel regTimeLabel, JTextField searchCourseTextField, JButton searchCourseBtn, JButton regCourseBtn, JTable courseTable) {
        this.regTimeLabel = regTimeLabel;
        this.searchCourseBtn = searchCourseBtn;
        this.searchCourseTextField = searchCourseTextField;
        this.regCourseBtn = regCourseBtn;
        this.courseTable = courseTable;
    }

    public void initUIData() {
        mTableManager = new CourseTableManager(courseTable);
        searchCourseTextField.setText(SEARCH_PLACEHOLDER_TEXT);
        Semester semester = SemesterDAO.getCurrent();
        if (semester == null) return;
        mTableManager.loadTableData(semester.getSemesterId());
        StudentGUI.setRegTimeLabel(regTimeLabel);
    }

    public void addModActionlistener() {
        searchCourseBtn.addActionListener(e -> onSearch());
        regCourseBtn.addActionListener(e -> onRegist());
        searchCourseTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onSearch();
                }
            }
        });
        searchCourseTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (searchCourseTextField.getText().equals(SEARCH_PLACEHOLDER_TEXT)) {
                    searchCourseTextField.setText("");
                    searchCourseTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (searchCourseTextField.getText().isEmpty()) {
                    searchCourseTextField.setForeground(Color.GRAY);
                    searchCourseTextField.setText(SEARCH_PLACEHOLDER_TEXT);
                }
            }
        });
    }

    private void onRegist() {
        CourseRegistrationSession curSession = CourseRegistrationSessionDAO.getCurrentSession();
        if(curSession == null){
            JOptionPane.showMessageDialog(null, "Đã hết thời gian đăng ký học phần", "Đăng ký thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (courseTable.getSelectedRowCount() == 1) {
            Object courseId = courseTable.getValueAt(courseTable.getSelectedRow(), 1);
            Course course = CourseDAO.get(String.valueOf(courseId));
            Account account = MainApp.getCurrentAccount();
            Student student = StudentDAO.getByStudentId(account.getUsername());
            CourseStudent courseStudent = new CourseStudent();
            courseStudent.setStudent(student);
            courseStudent.setCourse(course);

            if(CourseStudentDAO.add(courseStudent)){
                JOptionPane.showMessageDialog(null, "Dăng ký khoá học " + course.getSubject().getSubjectName() +" thành công" );
                CourseRegResultTabStu.refreshUIData();
            }
            return;
        }
        if (courseTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng để xoá");
        }
    }

    private void onSearch() {
        String userQuery = searchCourseTextField.getText();
        if (userQuery.equals(SEARCH_PLACEHOLDER_TEXT)) userQuery = "";
        mTableManager.filterData(userQuery);
    }


}

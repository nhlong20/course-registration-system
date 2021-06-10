package GUI.Tabs;

import GUI.Diaglog.AddCourseDlg;
import GUI.Diaglog.RegistrationStudentListDlg;
import GUI.TableManager.CourseTableManager;
import dao.CourseDAO;
import dao.SemesterDAO;
import pojo.Course;
import pojo.Semester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * GUI.Tabs
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/10/2021 - 3:53 PM
 * @Description
 */
public class RegCourseTabStu {
    private static RegCourseTabStu instance;

    private JLabel regTimeLabel;
    private JTextField searchCourseTextField;
    private JButton searchCourseBtn;
    private JButton regCourseBtn;
    private JTable courseTable;
    public static CourseTableManager mTableManager;
    public static String SEARCH_PLACEHOLDER_TEXT = "Tìm kiếm bằng ID hoặc tên học phần";

    public RegCourseTabStu() {
    }

    public RegCourseTabStu(JLabel regTimeLabel, JTextField searchCourseTextField, JButton searchCourseBtn, JButton regCourseBtn, JTable courseTable) {
        this.regTimeLabel = regTimeLabel;
        this.searchCourseBtn = searchCourseBtn;
        this.searchCourseTextField = searchCourseTextField;
        this.regCourseBtn = regCourseBtn;
        this.courseTable = courseTable;
    }


    public static RegCourseTabStu getInstance(JLabel regTimeLabel, JTextField searchCourseTextField, JButton searchCourseBtn, JButton regCourseBtn, JTable courseTable) {
        if (instance == null) {
            synchronized (SubjectTabMod.class) {
                if (instance == null) {
                    instance = new RegCourseTabStu(regTimeLabel, searchCourseTextField, searchCourseBtn, regCourseBtn, courseTable);
                }
            }
        }
        return instance;
    }

    public void initUIData() {
        mTableManager = new CourseTableManager(courseTable);
        mTableManager.loadTableData();
        searchCourseTextField.setText(SEARCH_PLACEHOLDER_TEXT);
        Semester semester = SemesterDAO.getCurrent();
        if (semester == null) return;
        regTimeLabel.setText(semester.getSemName() + " - " + semester.getSemYear());
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
        if (courseTable.getSelectedRowCount() == 1) {
            Object courseId = courseTable.getValueAt(courseTable.getSelectedRow(), 1);
            System.out.println(courseId);
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

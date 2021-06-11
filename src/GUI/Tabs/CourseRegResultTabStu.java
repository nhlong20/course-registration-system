package GUI.Tabs;

import GUI.TableManager.CourseRegResultTableManager;
import dao.CourseDAO;
import dao.CourseStudentDAO;
import dao.SemesterDAO;
import pojo.Course;
import pojo.CourseStudent;
import pojo.Semester;
import pojo.Student;

import javax.swing.*;

/**
 * GUI.Tabs
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/10/2021 - 11:04 PM
 * @Description
 */
public class CourseRegResultTabStu {
    public static CourseRegResultTableManager mTableManager;
    private JTable mTable;
    private JButton mCancel;
    private static Student currentUser;

    public CourseRegResultTabStu() {
    }

    public CourseRegResultTabStu(JTable resRegTable, JButton cancelCourseBtn, Student currentUser) {
        this.mCancel = cancelCourseBtn;
        this.mTable = resRegTable;
        this.currentUser = currentUser;
    }

    public void initUIData() {
        mTableManager = new CourseRegResultTableManager(mTable);
        Semester curSem = SemesterDAO.getCurrent();
        if(currentUser == null || curSem == null) return;
        mTableManager.loadTableData(currentUser.getStudentId(), curSem.getSemesterId());
    }

    public void addModActionlistener() {
        mCancel.addActionListener(e -> onRemove());
    }

    private void onRemove() {
        if (mTable.getSelectedRowCount() == 1) {
            Object courseId = mTable.getValueAt(mTable.getSelectedRow(), 1);
            Course course = CourseDAO.get(String.valueOf(courseId));

            CourseStudent courseStudent = CourseStudentDAO.get(currentUser.getStudentId(), String.valueOf(courseId));

            if(CourseStudentDAO.delete(courseStudent)){
                JOptionPane.showMessageDialog(null, "Xoá thành công" );
                mTableManager.removeRow(mTable.getSelectedRow());
            }
            return;
        }
        if (mTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng để xoá");
        }
    }
    public static void refreshUIData(){
        Semester curSem = SemesterDAO.getCurrent();
        mTableManager.refresh(currentUser.getStudentId(), curSem.getSemesterId());
    }
}

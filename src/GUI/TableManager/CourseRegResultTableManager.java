package GUI.TableManager;

import dao.CourseStudentDAO;
import pojo.Course;
import pojo.Shift;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

/**
 * GUI.TableManager
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/10/2021 - 11:11 PM
 * @Description
 */
public class CourseRegResultTableManager {
    JTable mTable;
    DefaultTableModel mModel;
    TableRowSorter<DefaultTableModel> sorter;
    private static String[] columnSemesterNames = {"STT", "Mã môn", "Tên môn", "Số tín chỉ", "GV lý thuyết", "Phòng học", "Ngày học", "Ca học", "Số slot tối đa"};

    public CourseRegResultTableManager(JTable table){
        mTable = table;
        mModel = new DefaultTableModel(columnSemesterNames, 0);
        sorter = new TableRowSorter<DefaultTableModel>(mModel);
        mTable.setRowSorter(sorter);
        mTable.setModel(mModel);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)mTable.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
        JTableHeader header = mTable.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        mTable.getColumnModel().getColumn(0).setMaxWidth(100);
    }
    public void loadTableData(String studentId, int curSemId){
        List<Course> courses = CourseStudentDAO.getAllCourses(studentId, curSemId);
        CourseTableManager.loadCourseToTable(courses, mModel);
    }
    public void addRow(Course course){

        Object[] row = new Object[9];
        row[0] = mTable.getRowCount()+1;
        row[1] = course.getCourseId();
        row[2] = course.getSubject().getSubjectName();
        row[3] = course.getSubject().getCredits();
        row[4] = course.getTeacher().getFullname();
        row[5] = course.getRoom();
        row[6] = course.getDayOfWeek();
        row[7] = course.getShift().getShiftId();
        row[8] = course.getMaximumSlots();
        mModel.addRow(row);
    }
    public void removeRow(int row){
        mModel.removeRow(row);
    }
    public void filterData(String query){
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)"+ query,1,2,4));
        }
    }
    public void refresh(String studentId, int curSemId){
        mModel.setRowCount(0);
        loadTableData(studentId, curSemId);
    }
}

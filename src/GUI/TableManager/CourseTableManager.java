package GUI.TableManager;

import dao.CourseDAO;
import pojo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;

/**
 * GUI.TableManager
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/7/2021 - 9:37 PM
 * @Description
 */
public class CourseTableManager {
    JTable mTable;
    DefaultTableModel mModel;
    TableRowSorter<DefaultTableModel> sorter;
    private static String[] columnSemesterNames = {"STT", "Mã môn", "Tên môn", "Số tín chỉ", "GV lý thuyết", "Phòng học", "Ngày học", "Ca học", "Số slot tối đa"};

    public CourseTableManager(JTable table){
        mTable = table;
        mModel = new DefaultTableModel(columnSemesterNames, 0);
        sorter = new TableRowSorter<DefaultTableModel>(mModel);
        mTable.setRowSorter(sorter);
        mTable.setModel(mModel);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)mTable.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
    }
    public void loadTableData(int curSemId){
        List<Course> courses = CourseDAO.getAll(curSemId);
        for(int i = 0; i < courses.size(); i++){
            String courseId = courses.get(i).getCourseId();
            String name = courses.get(i).getSubject().getSubjectName();
            int credits = courses.get(i).getSubject().getCredits();
            String teacherName = courses.get(i).getTeacher().getFullname();
            String room = courses.get(i).getRoom();
            String dayOfWeek = courses.get(i).getDayOfWeek();
            Shift shift = courses.get(i).getShift();
            int shiftTime = shift.getShiftId() ;
            int maxSlots = courses.get(i).getMaximumSlots();
            mModel.addRow(new Object[]{i + 1, courseId,name,credits,teacherName,room,dayOfWeek,shiftTime,maxSlots});
        }
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
    public void refresh(int curSemId){
        mModel.setRowCount(0);
        loadTableData(curSemId);
    }
}

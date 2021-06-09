package GUI.TableManager;

import dao.CourseDAO;
import dao.SemesterDAO;
import pojo.Course;

import javax.swing.*;
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
    }
    public void loadTableData(){
        List<Course> courses = CourseDAO.getAll();
        for(int i = 0; i < courses.size(); i++){
            String courseId = courses.get(i).getCourseId();
            String name = courses.get(i).getSubject().getSubjectName();
            int credits = courses.get(i).getSubject().getCredits();
            String teacherName = courses.get(i).getTeacher().getFullname();
            String room = courses.get(i).getRoom();
            String dayOfWeek = courses.get(i).getDayOfWeek();
            int shift = courses.get(i).getShift().getShiftId();
            int maxSlots = courses.get(i).getMaximumSlots();
            mModel.addRow(new Object[]{i + 1, courseId,name,credits,teacherName,room,dayOfWeek,shift,maxSlots});
        }
    }
    public void addRow(Course course){
        Object[] row = new Object[6];
        row[0] = mTable.getRowCount()+1;
        mModel.addRow(row);
    }
    public void removeRow(int row){
        mModel.removeRow(row);
    }
    public void filterData(String query){
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)"+ query,1,3));
        }
    }
    public void refresh(){
        mModel.setRowCount(0);
        loadTableData();
    }
}

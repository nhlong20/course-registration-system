package GUI.TableManager;

import dao.CourseRegistrationSessionDAO;
import dao.CourseStudentDAO;
import pojo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * GUI.TableManager
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/10/2021 - 12:59 AM
 * @Description
 */
public class RegStudentListTableManager {
    JTable mTable;
    DefaultTableModel mModel;
    TableRowSorter<DefaultTableModel> sorter;
    private static String[] columnRegStudentNames = {"STT", "MSSV", "Họ tên", "Mã học phần", "Tên môn học", "GV lý thuyết", "Ngày học","Ca học", "Ngày đăng ký"};

    public RegStudentListTableManager(JTable table){
        mTable = table;
        mModel = new DefaultTableModel(columnRegStudentNames, 0);
        sorter = new TableRowSorter<DefaultTableModel>(mModel);
        mTable.setRowSorter(sorter);
        mTable.setModel(mModel);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)mTable.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
        JTableHeader header = mTable.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        mTable.getColumnModel().getColumn(0).setMaxWidth(100);
        mTable.getColumnModel().getColumn(2).setMinWidth(180);
    }
    public void loadTableData(Course course){
        List<CourseStudent> courseStudents = CourseStudentDAO.get(course.getCourseId());
        SimpleDateFormat sDF1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sDF2 = new SimpleDateFormat("HH:mm");
        for(int i = 0; i <courseStudents.size() ; i++){
            String studentId = courseStudents.get(i).getStudent().getStudentId();
            String studentName = courseStudents.get(i).getStudent().getFullname();
            String courseId = course.getCourseId();
            String subjectName = course.getSubject().getSubjectName();
            String teacherName = course.getTeacher().getFullname();
            String dayOfWeek = course.getDayOfWeek();
            Shift shift = course.getShift();
            String shiftTime = sDF2.format(shift.getStartAt()) + " - " + sDF2.format(shift.getEndAt());
            String registerDate = sDF1.format(courseStudents.get(i).getCreatedAt());

             mModel.addRow(new Object[]{i+1,studentId,studentName,courseId,subjectName,teacherName,dayOfWeek,shiftTime,registerDate});
        }
    }
    public void filterData(String query){
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)"+ query,1,2));
        }
    }
}

package GUI.TableManager;

import dao.CourseRegistrationSessionDAO;
import dao.CourseStudentDAO;
import pojo.Course;
import pojo.CourseRegistrationSession;
import pojo.CourseStudent;
import pojo.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
    }
    public void loadTableData(Course course){
        List<CourseStudent> courseStudents = CourseStudentDAO.get(course.getCourseId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for(int i = 0; i <courseStudents.size() ; i++){
            String studentId = courseStudents.get(i).getStudent().getStudentId();
            String studentName = courseStudents.get(i).getStudent().getFullname();
            String courseId = course.getCourseId();
            String subjectName = course.getSubject().getSubjectName();
            String teacherName = course.getTeacher().getFullname();
            String dayOfWeek = course.getDayOfWeek();
            int shift = course.getShift().getShiftId();

            String registerDate = simpleDateFormat.format(courseStudents.get(i).getCreatedAt());
             mModel.addRow(new Object[]{i+1,studentId,studentName,courseId,subjectName,teacherName,dayOfWeek,shift,registerDate});
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

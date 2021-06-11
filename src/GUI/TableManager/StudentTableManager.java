package GUI.TableManager;

import dao.StudentDAO;
import pojo.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.util.List;

/**
 * GUI.TableManager
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/7/2021 - 9:38 PM
 * @Description
 */
public class StudentTableManager {
    JTable mTable;
    DefaultTableModel mModel;
    TableRowSorter<DefaultTableModel> sorter;
    private static String[] columnSemesterNames = {"STT", "ID", "MSSV", "Họ và tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Lớp"};

    public StudentTableManager(JTable table) {
        mTable = table;
        mModel = new DefaultTableModel(columnSemesterNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        sorter = new TableRowSorter<DefaultTableModel>(mModel);
        mTable.setRowSorter(sorter);
        mTable.setModel(mModel);
        TableColumn colToDelete = mTable.getColumnModel().getColumn(1);
        mTable.removeColumn(colToDelete);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)mTable.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
        mTable.getColumnModel().getColumn(0).setMaxWidth(100);
    }

    public void loadTableData(String classCode) {
        mModel.setRowCount(0);
        List<Student> students = StudentDAO.getAll(classCode);
        for (int i = 0; i < students.size(); i++) {
            int id = students.get(i).getId();
            String studentId = students.get(i).getStudentId();
            String studentName = students.get(i).getFullname();
            String studentGender = students.get(i).getGender();
            String studentDOB = String.valueOf(students.get(i).getDob());
            String studentAddress = students.get(i).getStuAddress();
            String studentClass = students.get(i).getClazz().getClassCode();
            mModel.addRow(new Object[]{i + 1, id, studentId, studentName, studentGender, studentDOB, studentAddress, studentClass});
        }
    }

    public void addRow(Student newStudent) {
        Object[] row = new Object[8];
        row[0] = mTable.getRowCount() + 1;
        row[1] = newStudent.getId();
        row[2] = newStudent.getStudentId();
        row[3] = newStudent.getFullname();
        row[4] = newStudent.getGender();
        row[5] = String.valueOf(newStudent.getDob());
        row[6] = newStudent.getStuAddress();
        row[7] = newStudent.getClazz().getClassCode();
        mModel.addRow(row);
    }

    public void removeRow(int row) {
        mModel.removeRow(row);
    }

    public void filterData(String query) {
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 2, 3));
        }
    }

    public void refresh(String classCode) {
        loadTableData(classCode);
    }
}

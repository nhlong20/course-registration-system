package GUI.TableManager;

import dao.SemesterDAO;

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
    private static String[] columnSemesterNames = {"STT", "Mã học kì", "Tên học kỳ", "Năm học", "Ngày bắt đầu", "Ngày kết thúc"};

    public CourseTableManager(JTable table){
        mTable = table;
        mModel = new DefaultTableModel(columnSemesterNames, 0);
        sorter = new TableRowSorter<DefaultTableModel>(mModel);
        mTable.setRowSorter(sorter);
        mTable.setModel(mModel);
    }
    public void loadTableData(){
//        List<Semester> semesters = SemesterDAO.getAll();
//        int semSize = semesters.size();
//        for(int i = semSize - 1; i >=0; i--){
//            int semesterId = semesters.get(i).getSemesterId();
//            String semName = semesters.get(i).getSemName();
//            int semYear = semesters.get(i).getSemYear();
//            String startDate = String.valueOf(semesters.get(i).getStartdate());
//            String enđate = String.valueOf(semesters.get(i).getEnddate());
//            mModel.addRow(new Object[]{semSize - i, semesterId,semName,semYear, startDate, enđate});
//        }
    }
//    public void addRow(Semester newSemester){
//        Object[] row = new Object[6];
//        row[0] = mTable.getRowCount()+1;
//        row[1] = newSemester.getSemesterId();
//        row[2] = newSemester.getSemName();
//        row[3] = newSemester.getSemYear();
//        row[4] = String.valueOf(newSemester.getStartdate());
//        row[5] = String.valueOf(newSemester.getEnddate());
//        mModel.addRow(row);
//    }
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
}

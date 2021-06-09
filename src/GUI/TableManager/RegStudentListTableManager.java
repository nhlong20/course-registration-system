package GUI.TableManager;

import dao.CourseRegistrationSessionDAO;
import pojo.CourseRegistrationSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
    private static String[] columnRegStudentNames = {"STT", "MSSV", "Họ tên", "Mã môn học", "Tên môn học", "GV lý thuyết", "Ngày học", "Ngày đăng ký"};

    public RegStudentListTableManager(JTable table){
        mTable = table;
        mModel = new DefaultTableModel(columnRegStudentNames, 0);
        sorter = new TableRowSorter<DefaultTableModel>(mModel);
        mTable.setRowSorter(sorter);
        mTable.setModel(mModel);
    }
    public void loadTableData(){
        List<CourseRegistrationSession> redSessions = CourseRegistrationSessionDAO.getAll();
        int semSize = redSessions.size();
        for(int i = semSize - 1; i >=0; i--){
            String startDate = String.valueOf(redSessions.get(i).getStartDate());
            String endDate = String.valueOf(redSessions.get(i).getEndDate());
            // TODO - Repplace Placeholder code
            mModel.addRow(new Object[]{semSize - i,startDate, endDate, 0, "Đã kết thúc"});
        }
    }
    public void addRow(CourseRegistrationSession newSession){
        Object[] row = new Object[5];
        row[0] = mTable.getRowCount()+1;
        mModel.addRow(row);
    }
    public void filterData(String query){
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)"+ query,1,2,4));
        }
    }
    public void removeRow(int row){
        mModel.removeRow(row);
    }
}

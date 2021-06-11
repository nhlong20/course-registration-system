package GUI.TableManager;

import dao.SemesterDAO;
import pojo.Semester;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

/**
 * GUI.TableManager
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/7/2021 - 8:56 PM
 * @Description
 */
public class SemesterTableManager {
    JTable mTable;
    DefaultTableModel mModel;
    TableRowSorter<DefaultTableModel> sorter;
    private static String[] columnSemesterNames = {"STT", "Mã học kì", "Tên học kỳ", "Năm học", "Ngày bắt đầu", "Ngày kết thúc"};

    public SemesterTableManager(JTable table){
        mTable = table;
        mModel = new DefaultTableModel(columnSemesterNames, 0){
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
        JTableHeader header = mTable.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
    }
    public void loadTableData(){
        List<Semester> semesters = SemesterDAO.getAll();
        for(int i = 0; i < semesters.size(); i++){
            int semesterId = semesters.get(i).getSemesterId();
            String semName = semesters.get(i).getSemName();
            int semYear = semesters.get(i).getSemYear();
            String startDate = String.valueOf(semesters.get(i).getStartdate());
            String endDate = String.valueOf(semesters.get(i).getEnddate());
            mModel.addRow(new Object[]{i+1, semesterId,semName,semYear, startDate, endDate});
        }

    }
    public void addRow(Semester newSemester){
        Object[] row = new Object[6];
        row[0] = mTable.getRowCount()+1;
        row[1] = newSemester.getSemesterId();
        row[2] = newSemester.getSemName();
        row[3] = newSemester.getSemYear();
        row[4] = String.valueOf(newSemester.getStartdate());
        row[5] = String.valueOf(newSemester.getEnddate());
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

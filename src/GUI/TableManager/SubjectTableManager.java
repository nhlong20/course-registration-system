package GUI.TableManager;

import dao.SubjectDAO;
import pojo.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;

/**
 * GUI.TableManager
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/7/2021 - 7:52 PM
 * @Description
 */
public class SubjectTableManager {
    JTable mTable;
    DefaultTableModel model;
    TableRowSorter<DefaultTableModel> sorter;
    private static String[] columnSubjectNames = {"STT", "Mã môn học", "Tên môn học", "Số tín chỉ"};

    public SubjectTableManager(JTable table){
        mTable = table;
        model = new DefaultTableModel(columnSubjectNames, 0);
        sorter = new TableRowSorter<DefaultTableModel>(model);
        mTable.setRowSorter(sorter);
        mTable.setModel(model);
    }
    public void loadTableData(){
        List<Subject> subjects = SubjectDAO.getAll();
        for(int i = 0; i < subjects.size(); i++){
            String subjectId = subjects.get(i).getSubjectId();
            String subjectName = subjects.get(i).getSubjectName();
            int subjectCredits = subjects.get(i).getCredits();
            model.addRow(new Object[]{i+1, subjectId,subjectName,subjectCredits});
        }
    }
    public void addRow(Subject newSubject){
        Object[] row = new Object[4];
        row[0] = mTable.getRowCount()+1;
        row[1] = newSubject.getSubjectId();
        row[2] = newSubject.getSubjectName();
        row[3] = newSubject.getCredits();
        model.addRow(row);
    }
    public void removeRow(int row){
        model.removeRow(row);
    }
    public void filterData(String query){
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)"+ query,1,2));
        }
    }
}

package GUI.TableManager;

import dao.SubjectDAO;
import pojo.Subject;

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
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)mTable.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
        JTableHeader header = mTable.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        mTable.getColumnModel().getColumn(0).setMaxWidth(100);
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
    public void updateRow(int row, Subject subject){
        model.setValueAt(subject.getSubjectId(), row, 1);
        model.setValueAt(subject.getSubjectName(), row, 2);
        model.setValueAt(subject.getCredits(), row, 3);

    }
}

package GUI.TableManager;

import dao.ClazzDAO;
import pojo.Clazz;

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
public class ClazzTableManager {
    JTable mTable;
    DefaultTableModel mModel;
    TableRowSorter<DefaultTableModel> sorter;
    private static String[] columnClazzNames = {"STT", "Mã lớp", "Khoá", "Tổng sinh viên", "Số nam", "Số nữ"};

    public ClazzTableManager(JTable table){
        mTable = table;
        mModel = new DefaultTableModel(columnClazzNames, 0);
        sorter = new TableRowSorter<DefaultTableModel>(mModel);
        mTable.setRowSorter(sorter);
        mTable.setModel(mModel);
    }
    public void loadTableData(){
        List<Clazz> clazzes = ClazzDAO.getAll();
        int semSize = clazzes.size();
        for(int i = semSize - 1; i >=0; i--){
            String classCode = clazzes.get(i).getClassCode();
            int clazzYear = clazzes.get(i).getClassYear();
            int totalStudent = 0;
            int totalMale = 0;
            int totalFemale = 0;
            mModel.addRow(new Object[]{semSize - i,classCode,clazzYear, totalStudent, totalMale, totalFemale});
        }
    }
    public void addRow(Clazz newClazz){
        Object[] row = new Object[3];
        row[0] = mTable.getRowCount()+1;
        row[1] = newClazz.getClassCode();
        row[2] = newClazz.getClassYear();
        mModel.addRow(row);
    }
    public void removeRow(int row){
        mModel.removeRow(row);
    }
}

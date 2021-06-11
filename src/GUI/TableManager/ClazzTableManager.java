package GUI.TableManager;

import dao.ClazzDAO;
import dao.StudentDAO;
import pojo.Clazz;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)mTable.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
        mTable.getColumnModel().getColumn(0).setMaxWidth(100);
    }
    public void loadTableData(){
        List<Clazz> clazzes = ClazzDAO.getAll();
        for(int i = 0; i < clazzes.size(); i++){
            String classCode = clazzes.get(i).getClassCode();
            int clazzYear = clazzes.get(i).getClassYear();
            int totalStudent = clazzes.get(i).getStudents().size();
            int totalMale = StudentDAO.countStudentWithGender(classCode, 1); // 1 is Male
            int totalFemale = StudentDAO.countStudentWithGender(classCode, 0);
            mModel.addRow(new Object[]{i + 1,classCode,clazzYear, totalStudent, totalMale, totalFemale});
        }
    }
    public void addRow(Clazz newClazz){
        Object[] row = new Object[6];
        row[0] = mTable.getRowCount()+1;
        row[1] = newClazz.getClassCode();
        row[2] = newClazz.getClassYear();
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        mModel.addRow(row);
    }
    public void removeRow(int row){
        mModel.removeRow(row);
    }
    public void refresh(){
        mModel.setRowCount(0);
        loadTableData();
    }
}

package GUI.TableManager;

import dao.ClazzDAO;
import dao.CourseRegistrationSessionDAO;
import pojo.Clazz;
import pojo.CourseRegistrationSession;

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

public class CourseRegistrationSessionTableManager {
    JTable mTable;
    DefaultTableModel mModel;
    TableRowSorter<DefaultTableModel> sorter;
    private static String[] columnRegSessionNames = {"STT", "Ngày bắt đầu", "Ngày kết thúc", "Mã học kỳ", "Trạng thái"};

    public CourseRegistrationSessionTableManager(JTable table){
        mTable = table;
        mModel = new DefaultTableModel(columnRegSessionNames, 0);
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
        row[1] = newSession.getStartDate();
        row[2] = newSession.getEndDate();
        row[3] = newSession.getSemesterId();
        row[4] = "Đang mở";
        mModel.addRow(row);
    }
    public void removeRow(int row){
        mModel.removeRow(row);
    }
}

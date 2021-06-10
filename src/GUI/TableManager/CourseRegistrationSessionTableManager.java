package GUI.TableManager;

import dao.CourseRegistrationSessionDAO;
import pojo.CourseRegistrationSession;
import pojo.Semester;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.Date;
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
    private static String[] columnRegSessionNames = {"STT", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"};
    private static String[] sessionStatus = {"Đã kết thúc", "Đang mở", "Chưa bắt đầu"};

    public CourseRegistrationSessionTableManager(JTable table){
        mTable = table;
        mModel = new DefaultTableModel(columnRegSessionNames, 0);
        sorter = new TableRowSorter<DefaultTableModel>(mModel);
        mTable.setRowSorter(sorter);
        mTable.setModel(mModel);
    }
    public void loadTableData(Semester curSem){
        List<CourseRegistrationSession> regSessions = CourseRegistrationSessionDAO.getAll(curSem.getSemesterId());
        String curSessionStatus = "";
        Date curD = new Date();
        for(int i = 0; i <regSessions.size() ; i++){

            Date startD = regSessions.get(i).getStartDate();
            Date endD = regSessions.get(i).getEndDate();

            if(curD.before(startD)) curSessionStatus = sessionStatus[2];
            else if(curD.after(endD)) curSessionStatus = sessionStatus[0];
            else curSessionStatus = sessionStatus[1];

            String startDate = String.valueOf(startD);
            String endDate = String.valueOf(endD);

            mModel.addRow(new Object[]{i,startDate, endDate, curSessionStatus});
        }
    }
    public void addRow(CourseRegistrationSession newSession){
        Date curD = new Date();
        Object[] row = new Object[4];
        row[0] = mTable.getRowCount()+1;
        row[1] = newSession.getStartDate();
        row[2] = newSession.getEndDate();
        String curSessionStatus = "";
        if(curD.before(newSession.getStartDate())) curSessionStatus = sessionStatus[2];
        else if(curD.after(newSession.getEndDate())) curSessionStatus = sessionStatus[0];
        else curSessionStatus = sessionStatus[1];

        row[3] = curSessionStatus;
        mModel.addRow(row);
    }
    public void removeRow(int row){
        mModel.removeRow(row);
    }
    public void refresh(Semester curSem){
        mModel.setRowCount(0);
        loadTableData(curSem);
    }
}

package GUI.TableManager;

import dao.ModeratorDAO;
import pojo.Moderator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;

/**
 * GUI.TableManager
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/5/2021 - 5:45 PM
 * @Description
 */
public class ModeratorTableManager {
    JTable modTable;
    DefaultTableModel model;
    TableRowSorter<DefaultTableModel> sorter;
    private static String[] columnModeratorNames = {"STT", "Mã giáo vụ", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ"};

    public ModeratorTableManager(JTable table){
        modTable = table;
        model = new DefaultTableModel(columnModeratorNames, 0);
        sorter = new TableRowSorter<DefaultTableModel>(model);
        modTable.setRowSorter(sorter);
        modTable.setModel(model);
    }
    public void loadTableData(){
        List<Moderator> moderators = ModeratorDAO.getModeratorList();
        for(int i = 0; i < moderators.size(); i++){
            String modId = moderators.get(i).getModeratorId();
            String fullname = moderators.get(i).getFullname();
            String gender = moderators.get(i).getGender();
            String dob = String.valueOf(moderators.get(i).getDob());
            String phone = moderators.get(i).getPhone();
            String address = moderators.get(i).getModAddress();
            model.addRow(new Object[]{i+1, modId,fullname,gender,dob,phone,address});
        }
    }
    public void addRow(Moderator newModerator){
        Object[] row = new Object[7];
        row[0] = modTable.getRowCount()+1;
        row[1] = newModerator.getModeratorId();
        row[2] = newModerator.getFullname();
        row[3] = newModerator.getGender();
        row[4]= String.valueOf(newModerator.getDob());
        row[5]= newModerator.getPhone();
        row[6]= newModerator.getModAddress();
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

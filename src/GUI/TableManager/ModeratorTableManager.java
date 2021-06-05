package GUI.TableManager;

import dao.ModeratorDAO;
import pojo.Moderator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private static String[] columnModeratorNames = {"STT", "Mã giáo vụ", "Họ và tên", "Giới tính", "Ngày sinh", "Địa chỉ"};
    DefaultTableModel model;
    public ModeratorTableManager(JTable table){
        modTable = table;
        model = new DefaultTableModel(columnModeratorNames, 0);
        modTable.setModel(model);
    }
    public void loadTableData(){
        List<Moderator> moderators = ModeratorDAO.getModeratorList();
        for(int i = 0; i < moderators.size(); i++){
            String modId = moderators.get(i).getModeratorId();
            String fullname = moderators.get(i).getFullname();
            String gender = moderators.get(i).getGender();
            String dob = String.valueOf(moderators.get(i).getDob());
            String address = moderators.get(i).getModAddress();
            model.addRow(new Object[]{i+1, modId,fullname,gender,dob,address});
        }
    }

}

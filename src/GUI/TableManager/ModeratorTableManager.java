package GUI.TableManager;

import dao.ModeratorDAO;
import pojo.Moderator;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
    private static JTable modTable;
    private static DefaultTableModel mModel;
    private static TableRowSorter<DefaultTableModel> sorter;
    private static String[] columnModeratorNames = {"STT", "Mã giáo vụ", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ"};

    public ModeratorTableManager(JTable table) {
        modTable = table;
        mModel = new DefaultTableModel(columnModeratorNames, 0);
        sorter = new TableRowSorter<DefaultTableModel>(mModel);
        modTable.setRowSorter(sorter);
        modTable.setModel(mModel);

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)modTable.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
        modTable.getColumnModel().getColumn(0).setMaxWidth(100);
    }

    public static void loadTableData() {
        List<Moderator> moderators = ModeratorDAO.getAll();
        for (int i = 0; i < moderators.size(); i++) {
            String modId = moderators.get(i).getModeratorId();
            String fullname = moderators.get(i).getFullname();
            String gender = moderators.get(i).getGender();
            String dob = String.valueOf(moderators.get(i).getDob());
            String phone = moderators.get(i).getPhone();
            String address = moderators.get(i).getModAddress();
            mModel.addRow(new Object[]{i + 1, modId, fullname, gender, dob, phone, address});
        }
    }

    public static void addRow(Moderator newModerator) {
        Object[] row = new Object[7];
        row[0] = modTable.getRowCount() + 1;
        row[1] = newModerator.getModeratorId();
        row[2] = newModerator.getFullname();
        row[3] = newModerator.getGender();
        row[4] = String.valueOf(newModerator.getDob());
        row[5] = newModerator.getPhone();
        row[6] = newModerator.getModAddress();
        mModel.addRow(row);
    }

    public static void removeRow(int row) {
        mModel.removeRow(row);
    }

    public static void filterData(String query) {
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 1, 2));
        }
    }

    public static void refresh() {
        mModel.setRowCount(0);
        loadTableData();
    }
}

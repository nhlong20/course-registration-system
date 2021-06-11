package GUI.Tabs;

import GUI.Diaglog.AddClazzDlg;
import GUI.TableManager.ClazzTableManager;
import dao.ClazzDAO;

import javax.swing.*;
import java.awt.event.*;

/**
 * GUI.Tabs
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/7/2021 - 10:28 PM
 * @Description
 */
public class ClazzTabMod {
    public static ClazzTableManager mTableManager;
    private JTable mTable;
    private JButton mDeleteBtn;
    private JButton mAddBtn;

    public ClazzTabMod() {
    }

    public ClazzTabMod(JTable table, JButton deleteBtn, JButton addBtn) {
        this.mTable = table;
        this.mDeleteBtn = deleteBtn;
        this.mAddBtn = addBtn;
    }

    public void initUIData() {
        mTableManager = new ClazzTableManager(mTable);
        mTableManager.loadTableData();
    }

    public void addModActionlistener() {
        mAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });

        mDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDelete();
            }
        });
    }

    private void onAdd() {
        AddClazzDlg addClazzDlg = new AddClazzDlg();
    }

    private void onDelete() {
        if (mTable.getSelectedRowCount() == 1) {
            Object curId = mTable.getValueAt(mTable.getSelectedRow(), 1);

            // Remove Row from DB
            if(!ClazzDAO.delete(String.valueOf(curId.toString()))){
                JOptionPane.showMessageDialog(null, "Xoá thất bại", "Thất bại", JOptionPane.ERROR_MESSAGE);
                return;
            };
            // Remove Row from UI
            mTableManager.removeRow(mTable.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Xoá thành công","Thành công", JOptionPane.INFORMATION_MESSAGE);
            StudentTabMod.initClassData();
            return;
        }
        if (mTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng để xoá");
        }
    }
}

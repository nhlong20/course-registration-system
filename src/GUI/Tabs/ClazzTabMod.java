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
    private static ClazzTabMod instance;

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


    public static ClazzTabMod getInstance(JTable table, JButton deleteBtn, JButton addBtn) {
        if (instance == null) {
            synchronized (SubjectTabMod.class) {
                if (instance == null) {
                    instance = new ClazzTabMod(table, deleteBtn, addBtn);
                }
            }
        }
        return instance;
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
        if(mTable.getSelectedRowCount() == 1){
            Object curId =  mTable.getValueAt(mTable.getSelectedRow(),1);

            // Remove Row from DB
            ClazzDAO.delete(String.valueOf(curId.toString()));
            // Remove Row from UI
            mTableManager.removeRow(mTable.getSelectedRow());
            return;
        }
        if(mTable.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng để xoá");
        }
    }
}

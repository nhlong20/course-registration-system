package GUI.Tabs;

import GUI.Diaglog.AddModertatorDlg;
import GUI.TableManager.ModeratorTableManager;
import dao.AccountDAO;
import dao.ModeratorDAO;
import dao.StudentDAO;
import pojo.Account;
import pojo.Moderator;
import pojo.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI.Tabs
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/7/2021 - 6:38 PM
 * @Description
 */
public class ModeratorTabMod {
    public static String SEARCH_PLACEHOLDER_TEXT = "Tìm kiếm bằng ID hoặc tên";
    public static ModeratorTableManager mTableManager;
    private JTextField mSearchTextField;
    private JTable mTable;
    private JButton mSearchBtn;
    private JButton mDeleteBtn;
    private JButton mAddBtn;
    private JButton mUpdateBtn;
    private JButton mResetBtn;

    public ModeratorTabMod() {
    }

    public ModeratorTabMod(JTextField searchModTextField, JTable modTable, JButton searchModBtn, JButton deleteModBtn, JButton addModBtn, JButton updateModBtn, JButton resetModBtn) {
        this.mSearchTextField = searchModTextField;
        this.mTable = modTable;
        this.mSearchBtn = searchModBtn;
        this.mDeleteBtn = deleteModBtn;
        this.mAddBtn = addModBtn;
        this.mUpdateBtn = updateModBtn;
        this.mResetBtn = resetModBtn;
    }

    public void addModActionlistener() {
        mAddBtn.addActionListener(e -> onAdd());
        mSearchBtn.addActionListener(e -> onSearch());
        mDeleteBtn.addActionListener(e -> onDelete());
        mUpdateBtn.addActionListener(e -> onUpdate());
        mResetBtn.addActionListener(e -> onResetPassword());
        mSearchTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (mSearchTextField.getText().equals(SEARCH_PLACEHOLDER_TEXT)) {
                    mSearchTextField.setText("");
                    mSearchTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (mSearchTextField.getText().isEmpty()) {
                    mSearchTextField.setForeground(Color.GRAY);
                    mSearchTextField.setText(SEARCH_PLACEHOLDER_TEXT);
                }
            }
        });
        mSearchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onSearch();
                }
            }
        });
    }


    public void initUIModData() {
        mTableManager = new ModeratorTableManager(mTable);
        mTableManager.loadTableData();
        mSearchTextField.setText(SEARCH_PLACEHOLDER_TEXT);
    }

    private void onSearch() {
        String userQuery = mSearchTextField.getText();
        if (userQuery.equals(SEARCH_PLACEHOLDER_TEXT)) userQuery = "";
        mTableManager.filterData(userQuery);
    }

    private void onAdd() {
        AddModertatorDlg addModertatorDlg = new AddModertatorDlg();
    }

    private void onDelete() {
        if (mTable.getSelectedRowCount() == 1) {
            Object curId = mTable.getValueAt(mTable.getSelectedRow(), 1);
            // Remove Row from DB
            if(!ModeratorDAO.delete(String.valueOf(curId))){
                JOptionPane.showMessageDialog(null, "Xoá người dùng thất bại","Thất bại", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "Xoá người dùng thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            // Remove Row from UI
            mTableManager.removeRow(mTable.getSelectedRow());
            return;
        }
        if (mTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng để xoá");
        }
    }

    private void onUpdate() {
        if (mTable.getSelectedRowCount() == 1) {
            Object curId = mTable.getValueAt(mTable.getSelectedRow(), 1);
            Moderator moderator = ModeratorDAO.get(String.valueOf(curId));
            // Update
            AddModertatorDlg addModertatorDlg = new AddModertatorDlg(moderator);
            // Refresh whether update successful or not
            mTableManager.refresh();
            return;
        }
        if (mTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chỉ chọn 1 hàng");
        }
    }

    private void onResetPassword() {
        if (mTable.getSelectedRowCount() == 1) {
            String moderatorId = String.valueOf(mTable.getModel().getValueAt(mTable.getSelectedRow(), 1));
            Account account = AccountDAO.getAccount(moderatorId);
            if (account == null) {
                JOptionPane.showMessageDialog(null, "Tài khoản ứng với Mã số này này không tồn tại");
                return;
            }
            account.setPasswd(moderatorId);
            if (AccountDAO.update(account)) {
                JOptionPane.showMessageDialog(null, "Reset mật khẩu thành công");
            }
            return;
        }
        if (mTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chỉ chọn 1 hàng");
        }
    }
}

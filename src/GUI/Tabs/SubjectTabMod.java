package GUI.Tabs;

import GUI.Diaglog.AddSubjectDlg;
import GUI.TableManager.ModeratorTableManager;
import GUI.TableManager.SubjectTableManager;
import dao.ModeratorDAO;
import dao.SubjectDAO;
import pojo.Moderator;
import pojo.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI.Tabs
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/7/2021 - 6:29 PM
 * @Description
 */
public class SubjectTabMod {
    private static SubjectTabMod instance;

    public static SubjectTableManager mTableManager;
    public static String SEARCH_PLACEHOLDER_TEXT = "Tìm kiếm bằng ID hoặc tên";
    private JTextField mSearchTextField;
    private JTable mTable;
    private JButton mSearchBtn;
    private JButton mDeleteBtn;
    private JButton mAddBtn;

    public SubjectTabMod() {
    }

    public SubjectTabMod(JTextField searchTextField, JTable table, JButton searchBtn, JButton deleteBtn, JButton addBtn) {
        this.mSearchTextField = searchTextField;
        this.mTable = table;
        this.mSearchBtn = searchBtn;
        this.mDeleteBtn = deleteBtn;
        this.mAddBtn = addBtn;
    }


    public static SubjectTabMod getInstance(JTextField searchTextField, JTable table, JButton searchBtn, JButton deleteBtn, JButton addBtn) {
        if (instance == null) {
            synchronized (SubjectTabMod.class) {
                if (instance == null) {
                    instance = new SubjectTabMod(searchTextField, table, searchBtn, deleteBtn, addBtn);
                }
            }
        }
        return instance;
    }

    public void initUIData() {
        mTableManager = new SubjectTableManager(mTable);
        mTableManager.loadTableData();
        mSearchTextField.setText(SEARCH_PLACEHOLDER_TEXT);
    }

    public void addModActionlistener() {
        mAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });

        mSearchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSearch();
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
        mDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDelete();
            }
        });
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
    }


    private void onSearch() {
        String userQuery = mSearchTextField.getText();
        if(userQuery.equals(SEARCH_PLACEHOLDER_TEXT)) userQuery = "";
        mTableManager.filterData(userQuery);
    }

    private void onAdd() {
        AddSubjectDlg addSubjectDlg = new AddSubjectDlg();
    }

    private void onDelete() {
        if(mTable.getSelectedRowCount() == 1){
            // Remove Row from UI
            Object curId =  mTable.getValueAt(mTable.getSelectedRow(),1);
            mTableManager.removeRow(mTable.getSelectedRow());
            // Remove Row from DB
            SubjectDAO.delete(String.valueOf(curId));
            return;
        }
        if(mTable.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng để xoá");
        }
    }
}

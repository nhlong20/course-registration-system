package GUI.Tabs;

import GUI.Diaglog.AddModertatorDlg;
import GUI.TableManager.ModeratorTableManager;
import dao.ModeratorDAO;
import pojo.Moderator;

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
    private static ModeratorTabMod instance;

    public static ModeratorTableManager mTableManager;
    public static String SEARCH_PLACEHOLDER_TEXT = "Tìm kiếm bằng ID hoặc tên";
    private JTextField mSearchTextField;
    private JTable mTable;
    private JButton mSearchBtn;
    private JButton mDeleteBtn;
    private JButton mAddBtn;

    public ModeratorTabMod(){}

    public ModeratorTabMod(JTextField searchModTextField, JTable modTable, JButton searchModBtn, JButton deleteModBtn, JButton addModBtn) {
        this.mSearchTextField = searchModTextField;
        this.mTable = modTable;
        this.mSearchBtn = searchModBtn;
        this.mDeleteBtn = deleteModBtn;
        this.mAddBtn = addModBtn;
    }


    public static ModeratorTabMod getInstance(JTextField searchModTextField, JTable modTable, JButton searchModBtn, JButton deleteModBtn, JButton addModBtn){
        if(instance == null){
            synchronized(ModeratorTabMod.class){
                if(instance == null){
                    instance = new ModeratorTabMod(searchModTextField, modTable, searchModBtn, deleteModBtn, addModBtn);
                }
            }
        }
        return instance;
    }
    public void addModActionlistener(){
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
                if(mSearchTextField.getText().equals(SEARCH_PLACEHOLDER_TEXT)){
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
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
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
        if(userQuery.equals(SEARCH_PLACEHOLDER_TEXT)) userQuery = "";
        mTableManager.filterData(userQuery);
    }
    private void onAdd() {
        AddModertatorDlg addModertatorDlg = new AddModertatorDlg();
    }


    private void onDelete(){
        if(mTable.getSelectedRowCount() == 1){
            // Remove Row from UI
            Object curId =  mTable.getValueAt(mTable.getSelectedRow(),1);
            mTableManager.removeRow(mTable.getSelectedRow());
            // Remove Row from DB
            ModeratorDAO.deleteModerator(String.valueOf(curId));
            return;
        }
        if(mTable.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng để xoá");
        }
    }

}

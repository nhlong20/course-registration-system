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

    public static ModeratorTableManager moderatorTableManager;
    public static String SEARCH_PLACEHOLDER_TEXT = "Tìm kiếm bằng ID hoặc tên";
    private JTextField searchModTextField;
    private JTable modTable;
    private JButton searchModBtn;
    private JButton deleteModBtn;
    private JButton addModBtn;
    private JLabel accountName;

    public ModeratorTabMod(){}

    public ModeratorTabMod(JTextField searchModTextField, JTable modTable, JButton searchModBtn, JButton deleteModBtn, JButton addModBtn, JLabel accountName) {
        this.searchModTextField = searchModTextField;
        this.modTable = modTable;
        this.searchModBtn = searchModBtn;
        this.deleteModBtn = deleteModBtn;
        this.addModBtn = addModBtn;
        this.accountName = accountName;
    }


    public static ModeratorTabMod getInstance(JTextField searchModTextField, JTable modTable, JButton searchModBtn, JButton deleteModBtn, JButton addModBtn, JLabel accountName){
        if(instance == null){
            synchronized(ModeratorTabMod.class){
                if(instance == null){
                    instance = new ModeratorTabMod(searchModTextField, modTable, searchModBtn, deleteModBtn, addModBtn, accountName);
                }
            }
        }
        return instance;
    }
    public void addModActionlistener(){
        addModBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddMod();
            }
        });

        searchModBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        searchModBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSearchMod();
            }
        });
        deleteModBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDeleteMod();
            }
        });
        searchModTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(searchModTextField.getText().equals(SEARCH_PLACEHOLDER_TEXT)){
                    searchModTextField.setText("");
                    searchModTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (searchModTextField.getText().isEmpty()) {
                    searchModTextField.setForeground(Color.GRAY);
                    searchModTextField.setText(SEARCH_PLACEHOLDER_TEXT);
                }
            }
        });
        searchModTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    onSearchMod();
                }
            }
        });
    }
    public void initUIModData() {
        //        Account currentAccount = MainApp.getCurrentAccount();
//        Moderator currentUser = ModeratorDAO.getModerator(currentAccount.getUsername());
        Moderator currentUser = ModeratorDAO.getModerator("MOD002"); // temp

        accountName.setText(currentUser.getFullname());
        moderatorTableManager = new ModeratorTableManager(modTable);
        moderatorTableManager.loadTableData();
        searchModTextField.setText(SEARCH_PLACEHOLDER_TEXT);
    }

    private void onAddMod() {
        AddModertatorDlg addModertatorDlg = new AddModertatorDlg();
    }

    private void onSearchMod() {
        String userQuery = searchModTextField.getText();
        if(userQuery.equals(SEARCH_PLACEHOLDER_TEXT)) userQuery = "";
        moderatorTableManager.filterData(userQuery);
    }
    private void onDeleteMod(){
        if(modTable.getSelectedRowCount() == 1){
            // Remove Row from UI
            Object modID =  modTable.getValueAt(modTable.getSelectedRow(),1);
            moderatorTableManager.removeRow(modTable.getSelectedRow());
            // Remove Row from DB
            ModeratorDAO.deleteModerator(String.valueOf(modID));
            return;
        }
        if(modTable.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng để xoá");
        }
    }

}

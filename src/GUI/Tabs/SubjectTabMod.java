package GUI.Tabs;

import GUI.Diaglog.AddSubjectDlg;
import GUI.TableManager.SubjectTableManager;
import dao.ClazzDAO;
import dao.SubjectDAO;
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
    public static SubjectTableManager mTableManager;
    public static String SEARCH_PLACEHOLDER_TEXT = "Tìm kiếm bằng ID hoặc tên";
    private JTextField mSearchTextField;
    private JTable mTable;
    private JButton mSearchBtn;
    private JButton mDeleteBtn;
    private JButton mAddBtn;
    private JButton mUpdateBtn;
    public SubjectTabMod() {
    }

    public SubjectTabMod(JTextField searchTextField, JTable table, JButton searchBtn, JButton deleteBtn, JButton addBtn, JButton updateBtn) {
        this.mSearchTextField = searchTextField;
        this.mTable = table;
        this.mSearchBtn = searchBtn;
        this.mDeleteBtn = deleteBtn;
        this.mAddBtn = addBtn;
        this.mUpdateBtn = updateBtn;
    }

    public void initUIData() {
        mTableManager = new SubjectTableManager(mTable);
        mTableManager.loadTableData();
        mSearchTextField.setText(SEARCH_PLACEHOLDER_TEXT);
    }

    public void addModActionlistener() {
        mAddBtn.addActionListener(e-> onAdd());

        mSearchBtn.addActionListener(e -> onSearch());
        mUpdateBtn.addActionListener(e->onUpdate());
        mDeleteBtn.addActionListener(e -> onDelete());
        mSearchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onSearch();
                }
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
    private void onUpdate(){
        System.out.println(SubjectDAO.get("OOP").getCourses().size());
        if(true) return;

        if(mTable.getSelectedRowCount() == 1){
            String code =  String.valueOf(mTable.getValueAt(mTable.getSelectedRow(),1));
            String name =  String.valueOf(mTable.getValueAt(mTable.getSelectedRow(),2));
            String credits =  String.valueOf(mTable.getValueAt(mTable.getSelectedRow(),3));
            Subject subject = new Subject(code,name, Integer.parseInt(credits));
            AddSubjectDlg addSubjectDlg = new AddSubjectDlg(mTable.getSelectedRow(), subject);
            return;
        }
        if(mTable.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng");
        }
    }

    private void onDelete() {

        if(mTable.getSelectedRowCount() == 1){
            Object curId =  mTable.getValueAt(mTable.getSelectedRow(),1);
            // Remove Row from DB
            if(!SubjectDAO.delete(curId.toString())){
                JOptionPane.showMessageDialog(null, "Xoá thất bại");
                return;
            }
            JOptionPane.showMessageDialog(null, "Xoá thành công");
            // Remove Row from UI
            mTableManager.removeRow(mTable.getSelectedRow());
            return;
        }
        if(mTable.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng");
        }
    }
}

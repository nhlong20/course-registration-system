package GUI.Tabs;

import GUI.Diaglog.AddStudentDlg;
import GUI.TableManager.CourseTableManager;
import GUI.TableManager.StudentTableManager;
import dao.AccountDAO;
import dao.ClazzDAO;
import dao.StudentDAO;
import pojo.Account;
import pojo.Clazz;
import pojo.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI.Tabs
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/9/2021 - 3:11 PM
 * @Description
 */
public class CourseTabMod {
    private static CourseTabMod instance;

    private JTextField mSearchTextField;
    private JTable mTable;
    private JButton mSearchBtn;
    private JLabel mSemLabel;
    private JButton mAddBtn;
    private JButton mListBtn;
    private JButton mDeleteBtn;
    public static CourseTableManager mTableManager;
    public static String SEARCH_PLACEHOLDER_TEXT = "Tìm kiếm bằng ID hoặc tên học phần";

    public CourseTabMod() {
    }
    public CourseTabMod(JTextField searchTextField, JTable table, JButton searchBtn, JLabel currentSemLabel, JButton listBtn, JButton addBtn, JButton deleteBtn) {
        this.mSearchTextField = searchTextField;
        this.mTable = table;
        this.mSearchBtn = searchBtn;
        this.mSemLabel = currentSemLabel;
        this.mListBtn = listBtn;
        this.mAddBtn = addBtn;
        this.mDeleteBtn = deleteBtn;
    }


    public static CourseTabMod getInstance(JTextField searchTextField, JTable table, JButton searchBtn, JLabel currentSemLabel, JButton listBtn, JButton addBtn, JButton deleteBtn) {
        if (instance == null) {
            synchronized (SubjectTabMod.class) {
                if (instance == null) {
                    instance = new CourseTabMod(searchTextField, table, searchBtn, currentSemLabel, listBtn, addBtn, deleteBtn);
                }
            }
        }
        return instance;
    }
    public void initUIData() {
        mTableManager = new CourseTableManager(mTable);
        mTableManager.loadTableData();
        mSearchTextField.setText(SEARCH_PLACEHOLDER_TEXT);
    }

    public void addModActionlistener() {
        mSearchBtn.addActionListener(e->onSearch());
        mAddBtn.addActionListener(e-> onAdd());
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
        AddStudentDlg addStudentDlg = new AddStudentDlg();
    }

    private void onDelete() {
//        if(mTable.getSelectedRowCount() == 1){
//            Object studentId =  mTable.getValueAt(mTable.getSelectedRow(),2);
//            // Remove Row from DB
//            StudentDAO.delete(String.valueOf(studentId));
//            // Remove Row from UI
//            mTableManager.removeRow(mTable.getSelectedRow());
//            // Pop up successful message
//            JOptionPane.showMessageDialog(null, "Xoá dữ liệu thành công");
//            return;
//        }
//        if(mTable.getRowCount() == 0){
//            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
//        } else{
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng để xoá");
//        }
    }
}

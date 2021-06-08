package GUI.Tabs;

import GUI.Diaglog.AddStudentDlg;
import GUI.TableManager.StudentTableManager;
import dao.ClazzDAO;
import dao.StudentDAO;
import pojo.Clazz;

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
 * @Date 6/9/2021 - 1:05 AM
 * @Description
 */
public class StudentTabMod {
    private static StudentTabMod instance;
    private JTextField mSearchTextField;
    private JTable mTable;
    private JButton mSearchBtn;
    private JComboBox mComboBox;
    private JButton mAddBtn;
    private JButton mUpdateBtn;
    private JButton mResetBtn;
    private JButton mDeleteBtn;
    public static StudentTableManager mTableManager;
    public static String SEARCH_PLACEHOLDER_TEXT = "Tìm kiếm bằng ID hoặc tên sinh viên";

    public StudentTabMod() {
    }

    public StudentTabMod(JTextField searchTextField, JTable table, JButton searchBtn, JComboBox comboBox, JButton addBtn, JButton updateBtn, JButton resetBtn, JButton deleteBtn) {
        this.mSearchTextField = searchTextField;
        this.mTable = table;
        this.mSearchBtn = searchBtn;
        this.mComboBox = comboBox;
        this.mAddBtn = addBtn;
        this.mUpdateBtn = updateBtn;
        this.mResetBtn = resetBtn;
        this.mDeleteBtn = deleteBtn;
    }


    public static StudentTabMod getInstance(JTextField searchTextField, JTable table, JButton searchBtn, JComboBox comboBox, JButton addBtn, JButton updateBtn, JButton resetBtn, JButton deleteBtn) {
        if (instance == null) {
            synchronized (SubjectTabMod.class) {
                if (instance == null) {
                    instance = new StudentTabMod(searchTextField, table, searchBtn, comboBox, addBtn, updateBtn, resetBtn, deleteBtn);
                }
            }
        }
        return instance;
    }

    public void initUIData() {
        mTableManager = new StudentTableManager(mTable);
        mSearchTextField.setText(SEARCH_PLACEHOLDER_TEXT);
        List<Clazz> clazzes = ClazzDAO.getAll();
        List<String> clazzNames = new ArrayList<>();
        for(Clazz clazz : clazzes){
            clazzNames.add(clazz.getClassCode());
        }
        mComboBox.setModel(new DefaultComboBoxModel<>(clazzNames.toArray()));
        mTableManager.loadTableData(String.valueOf(mComboBox.getSelectedItem()));
    }

    public void addModActionlistener() {
        mComboBox.addActionListener(e ->onChange());
        mSearchBtn.addActionListener(e->onSearch());
        mAddBtn.addActionListener(e-> onAdd());
        mUpdateBtn.addActionListener(e -> onUpdate());
        mResetBtn.addActionListener(e -> onReset());
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
    private void onChange(){
        mTableManager.loadTableData(String.valueOf(mComboBox.getSelectedItem()));
    }

    private void onSearch() {
        String userQuery = mSearchTextField.getText();
        if(userQuery.equals(SEARCH_PLACEHOLDER_TEXT)) userQuery = "";
        mTableManager.filterData(userQuery);
    }

    private void onAdd() {
        AddStudentDlg addStudentDlg = new AddStudentDlg();
    }
    private void onReset(){
        // TODO - Reset student password to default
    }
    private void onUpdate() {
        AddStudentDlg addStudentDlg = new AddStudentDlg();
    }

    private void onDelete() {
        if(mTable.getSelectedRowCount() == 1){
            Object studentId =  mTable.getValueAt(mTable.getSelectedRow(),2);
            // Remove Row from DB
            StudentDAO.delete(String.valueOf(studentId));
            // Remove Row from UI
            mTableManager.removeRow(mTable.getSelectedRow());
            // Pop up successful message
            JOptionPane.showMessageDialog(null, "Xoá dữ liệu thành công");
            return;
        }
        if(mTable.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng để xoá");
        }
    }
}

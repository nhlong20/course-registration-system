package GUI.Tabs;

import GUI.Diaglog.AddStudentDlg;
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
import java.util.stream.Collectors;

/**
 * GUI.Tabs
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/9/2021 - 1:05 AM
 * @Description
 */
public class StudentTabMod {
    public static String SEARCH_PLACEHOLDER_TEXT = "Tìm kiếm bằng ID hoặc tên sinh viên";
    public static StudentTableManager mTableManager;
    public static String currentShownClass;
    private JTextField mSearchTextField;
    private JTable mTable;
    private JButton mSearchBtn;
    private static JComboBox mComboBox;
    private JButton mAddBtn;
    private JButton mUpdateBtn;
    private JButton mResetBtn;

    public StudentTabMod() {
    }

    public StudentTabMod(JTextField searchTextField, JTable table, JButton searchBtn, JComboBox comboBox, JButton addBtn, JButton updateBtn, JButton resetBtn) {
        this.mSearchTextField = searchTextField;
        this.mTable = table;
        this.mSearchBtn = searchBtn;
        this.mComboBox = comboBox;
        this.mAddBtn = addBtn;
        this.mUpdateBtn = updateBtn;
        this.mResetBtn = resetBtn;
    }

    public void initUIData() {
        mTableManager = new StudentTableManager(mTable);
        mSearchTextField.setText(SEARCH_PLACEHOLDER_TEXT);
        currentShownClass = String.valueOf(mComboBox.getSelectedItem());
        initClassData();
        mTableManager.loadTableData(String.valueOf(mComboBox.getSelectedItem()));
    }

    public static void initClassData() {
        List<Clazz> clazzes = ClazzDAO.getAll();
        List<String> clazzCode = clazzes.stream().map(p -> p.getClassCode()).collect(Collectors.toList());
        mComboBox.setModel(new DefaultComboBoxModel<>(clazzCode.toArray()));
    }

    public void addModActionlistener() {
        mComboBox.addActionListener(e -> onChange());
        mSearchBtn.addActionListener(e -> onSearch());
        mAddBtn.addActionListener(e -> onAdd());
        mUpdateBtn.addActionListener(e -> onUpdate());
        mResetBtn.addActionListener(e -> onReset());
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

    private void onChange() {
        currentShownClass = String.valueOf(mComboBox.getSelectedItem());
        mTableManager.loadTableData(currentShownClass);
    }

    private void onSearch() {
        String userQuery = mSearchTextField.getText();
        if (userQuery.equals(SEARCH_PLACEHOLDER_TEXT)) userQuery = "";
        mTableManager.filterData(userQuery);
    }

    private void onAdd() {
        AddStudentDlg addStudentDlg = new AddStudentDlg();
    }

    private void onReset() {


        if (mTable.getSelectedRowCount() == 1) {
            String studentId = String.valueOf(mTable.getModel().getValueAt(mTable.getSelectedRow(), 2));
            Student student = StudentDAO.getByStudentId(studentId);
            Account account = AccountDAO.getAccount(student.getStudentId());
            if (account == null) {
                JOptionPane.showMessageDialog(null, "Tài khoản ứng với MSSV này không tồn tại");
                return;
            }
            account.setPasswd(student.getStudentId());
            if (AccountDAO.update(account)) {
                JOptionPane.showMessageDialog(null, "Reset mật khẩu thành công");
            }
            return;
        }
        if (mTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void onUpdate() {
        currentShownClass = String.valueOf(mComboBox.getSelectedItem());
        if (mTable.getSelectedRowCount() == 1) {
            String studentId = String.valueOf(mTable.getModel().getValueAt(mTable.getSelectedRow(), 2));
            Student student = StudentDAO.getByStudentId(studentId);
            AddStudentDlg addStudentDlg = new AddStudentDlg(student);
            return;
        }
        if (mTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng");
        }
    }
}

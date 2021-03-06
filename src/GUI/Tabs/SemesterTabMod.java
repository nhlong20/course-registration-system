package GUI.Tabs;

import GUI.Diaglog.AddSemesterDlg;
import GUI.TableManager.SemesterTableManager;
import dao.SemesterDAO;
import pojo.Semester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * GUI.Tabs
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/7/2021 - 9:08 PM
 * @Description
 */
public class SemesterTabMod {
    public static String SEARCH_PLACEHOLDER_TEXT = "Tìm kiếm bằng ID hoặc năm học";
    public static SemesterTableManager mTableManager;
    private JTextField mSearchTextField;
    private JTable mTable;
    private JButton mSearchBtn;
    private JButton mDeleteBtn;
    private JButton mAddBtn;
    private JButton mSetAsCurrentSemBtn;
    private JLabel mCurrentSemesterLabel;
    public static Semester currentSem;

    public SemesterTabMod() {
    }

    public SemesterTabMod(JTextField searchTextField, JTable table, JButton searchBtn, JButton deleteBtn, JButton addBtn, JButton updateBtn, JLabel currentSemesterLabel) {
        this.mSearchTextField = searchTextField;
        this.mTable = table;
        this.mSearchBtn = searchBtn;
        this.mDeleteBtn = deleteBtn;
        this.mAddBtn = addBtn;
        this.mSetAsCurrentSemBtn = updateBtn;
        this.mCurrentSemesterLabel = currentSemesterLabel;
    }

    public void initUIData() {
        mTableManager = new SemesterTableManager(mTable);
        mTableManager.loadTableData();
        mSearchTextField.setText(SEARCH_PLACEHOLDER_TEXT);
        currentSem = SemesterDAO.getCurrent();
        if(currentSem ==null) return;
        mCurrentSemesterLabel.setText(currentSem.getSemName() + " - " + currentSem.getSemYear());
    }

    public void addModActionlistener() {
        mAddBtn.addActionListener(e-> onAdd());

        mSearchBtn.addActionListener(e->onSearch());
        mDeleteBtn.addActionListener(e -> onDelete());
        mSetAsCurrentSemBtn.addActionListener(e -> onUpdate());
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
        AddSemesterDlg addSemesterDlg = new AddSemesterDlg();
    }

    private void onUpdate() {
        if(mTable.getSelectedRowCount() == 1){
            Object curId =  mTable.getModel().getValueAt(mTable.getSelectedRow(),1);
            // Update current Semester
            SemesterDAO.setAsCurrent(Integer.parseInt(curId.toString()));
            // Update UI
            currentSem = SemesterDAO.getCurrent();
            if(currentSem == null) System.out.println("XXX");
            mCurrentSemesterLabel.setText(currentSem.getSemName() + " - " + currentSem.getSemYear());
            mTableManager.refresh();
            // Refresh course registration tab
            CourseRegistrationSessionTabMod.refreshUIData();
            CourseTabMod.refreshUIData();
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công");
            return;
        }
        if(mTable.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
        } else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng để xoá");
        }
    }

    private void onDelete() {
        if(mTable.getSelectedRowCount() == 1){
            Object curId =  mTable.getModel().getValueAt(mTable.getSelectedRow(), 1);
            // Remove Row from DB
            if(!SemesterDAO.delete(Integer.parseInt(curId.toString()))){
                JOptionPane.showMessageDialog(null, "Xoá dữ liệu thành công", "Thất bại khi xoá học kì", JOptionPane.ERROR_MESSAGE);
                return;
            };
            // Remove Row from UI
            mTableManager.removeRow(mTable.getSelectedRow());
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

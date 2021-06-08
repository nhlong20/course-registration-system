package GUI.Tabs;

import GUI.Diaglog.AddCourseRegistrationSessionDlg;
import GUI.TableManager.CourseRegistrationSessionTableManager;
import dao.ClazzDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI.Tabs
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/8/2021 - 4:05 PM
 * @Description
 */
public class CourseRegistrationSessionTabMod {
    private static CourseRegistrationSessionTabMod instance;

    public static CourseRegistrationSessionTableManager mTableManager;
    private JTable mTable;
    private JButton mOpenBtn;
    private JButton mCloseBtn;

    public CourseRegistrationSessionTabMod() {
    }

    public CourseRegistrationSessionTabMod(JTable table, JButton openBtn, JButton closeBtn) {
        this.mTable = table;
        this.mOpenBtn = openBtn;
        this.mCloseBtn = closeBtn;
    }

    public static CourseRegistrationSessionTabMod getInstance(JTable table, JButton openBtn, JButton closeBtn) {
        if (instance == null) {
            synchronized (SubjectTabMod.class) {
                if (instance == null) {
                    instance = new CourseRegistrationSessionTabMod(table, openBtn, closeBtn);
                }
            }
        }
        return instance;
    }

    public void initUIData() {
        mTableManager = new CourseRegistrationSessionTableManager(mTable);
        mTableManager.loadTableData();
    }

    public void addModActionlistener() {
        mOpenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOpen();
            }
        });

        mCloseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        });
    }

    private void onOpen() {
        AddCourseRegistrationSessionDlg addCourseRegistrationSessionDlg = new AddCourseRegistrationSessionDlg();
    }

    private void onClose() {
//        if(mTable.getSelectedRowCount() == 1){
//            Object curId =  mTable.getValueAt(mTable.getSelectedRow(),1);
//
//            // Remove Row from DB
//            ClazzDAO.delete(String.valueOf(curId.toString()));
//            return;
//        }
//        if(mTable.getRowCount() == 0){
//            JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu để thực hiện thao tác này");
//        } else{
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn chỉ 1 hàng để xoá");
//        }
    }
}

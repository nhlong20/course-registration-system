package GUI.Tabs;

import GUI.Diaglog.AddCourseRegistrationSessionDlg;
import GUI.TableManager.CourseRegistrationSessionTableManager;
import dao.SemesterDAO;
import pojo.Semester;

import javax.swing.*;

/**
 * GUI.Tabs
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/8/2021 - 4:05 PM
 * @Description
 */
public class CourseRegistrationSessionTabMod {
    public static CourseRegistrationSessionTableManager mTableManager;
    private static Semester curSem;
    private static JLabel mCurSemLabel;
    private JTable mTable;
    private JButton mOpenBtn;

    public CourseRegistrationSessionTabMod() {
    }

    public CourseRegistrationSessionTabMod(JTable table, JButton openBtn, JLabel currentSemLabel) {
        this.mTable = table;
        this.mOpenBtn = openBtn;
        this.mCurSemLabel = currentSemLabel;
    }

    public void initUIData() {
        mTableManager = new CourseRegistrationSessionTableManager(mTable);
        curSem = SemesterDAO.getCurrent();
        if (curSem == null) return;
        mTableManager.loadTableData(curSem);
        mCurSemLabel.setText(curSem.getSemName() + " - " + curSem.getSemYear());
    }

    public void addModActionlistener() {
        mOpenBtn.addActionListener(e -> onOpen());
    }

    private void onOpen() {
        AddCourseRegistrationSessionDlg addCourseRegistrationSessionDlg = new AddCourseRegistrationSessionDlg();
    }

    public static void refreshUIData() {
        curSem = SemesterDAO.getCurrent();
        mTableManager.refresh(curSem);
        mCurSemLabel.setText(curSem.getSemName() + " - " + curSem.getSemYear());
    }
}

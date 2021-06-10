package GUI.Tabs;

import GUI.Diaglog.AddCourseRegistrationSessionDlg;
import GUI.TableManager.CourseRegistrationSessionTableManager;
import dao.ClazzDAO;
import dao.SemesterDAO;
import pojo.Semester;

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
    private static Semester curSem;
    private JTable mTable;
    private JButton mOpenBtn;
    private static JLabel mCurSemLabel;
    public CourseRegistrationSessionTabMod() {
    }

    public CourseRegistrationSessionTabMod(JTable table, JButton openBtn, JLabel currentSemLabel) {
        this.mTable = table;
        this.mOpenBtn = openBtn;
        this.mCurSemLabel = currentSemLabel;
    }

    public static CourseRegistrationSessionTabMod getInstance(JTable table, JButton openBtn, JLabel currentSemLabel) {
        if (instance == null) {
            synchronized (SubjectTabMod.class) {
                if (instance == null) {
                    instance = new CourseRegistrationSessionTabMod(table, openBtn, currentSemLabel);
                }
            }
        }
        return instance;
    }

    public void initUIData() {
        mTableManager = new CourseRegistrationSessionTableManager(mTable);
        curSem = SemesterDAO.getCurrent();
        if(curSem == null) return;
        mTableManager.loadTableData(curSem);
        mCurSemLabel.setText(curSem.getSemName() + " - " + curSem.getSemYear());
    }

    public void addModActionlistener() {
        mOpenBtn.addActionListener(e->onOpen());
    }

    private void onOpen() {
        AddCourseRegistrationSessionDlg addCourseRegistrationSessionDlg = new AddCourseRegistrationSessionDlg();
    }
    public static void refreshUIData(){
        curSem = SemesterDAO.getCurrent();
        mTableManager.refresh(curSem);
        mCurSemLabel.setText(curSem.getSemName() + " - " + curSem.getSemYear());
    }
}

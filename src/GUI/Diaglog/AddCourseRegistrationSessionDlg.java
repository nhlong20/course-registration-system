package GUI.Diaglog;

import GUI.Tabs.CourseRegistrationSessionTabMod;
import com.toedter.calendar.JDateChooser;
import dao.CourseRegistrationSessionDAO;
import dao.SemesterDAO;
import pojo.CourseRegistrationSession;
import pojo.Semester;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddCourseRegistrationSessionDlg extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel startDatePanel;
    private JPanel endDatePanel;
    Calendar calendar;
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;

    public AddCourseRegistrationSessionDlg() {
        this.setTitle("Tạo mới phiên đăng ký học phần");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        calendar = Calendar.getInstance();
        startDateChooser = new JDateChooser(calendar.getTime());
        startDateChooser.setDateFormatString("dd/MM/yyyy");
        endDateChooser = new JDateChooser(calendar.getTime());
        endDateChooser.setDateFormatString("dd/MM/yyyy");

        startDatePanel.add(startDateChooser);
        endDatePanel.add(endDateChooser);

        this.addEventListener();
        this.setModal(true);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addEventListener(){
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    private void onOK() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String temp1 = simpleDateFormat.format(startDateChooser.getDate());
        String temp2 = simpleDateFormat.format(endDateChooser.getDate());
        Date startDate = Date.valueOf(temp1);
        Date endDate = Date.valueOf(temp2);

        Semester currentSem = SemesterDAO.getCurrent();
        if(currentSem == null){
            JOptionPane.showMessageDialog(null, "Học kì hiện tại không có, vui lòng kiểm tra lại");
            return;
        }
        CourseRegistrationSession newSesion = new CourseRegistrationSession(startDate,endDate, currentSem);
        if(CourseRegistrationSessionDAO.add(newSesion)){
            CourseRegistrationSessionTabMod.mTableManager.addRow(newSesion);
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}

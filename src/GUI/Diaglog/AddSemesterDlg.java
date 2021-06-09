package GUI.Diaglog;

import GUI.Tabs.SemesterTabMod;
import com.toedter.calendar.JDateChooser;
import dao.SemesterDAO;
import pojo.Semester;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddSemesterDlg extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField yearTextField;
    private JComboBox nameComboBox;
    private JPanel startDatePanel;
    private JPanel endDatePanel;
    Calendar calendar;
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;
    public AddSemesterDlg() {
        this.setTitle("Thêm học kỳ mới");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        nameComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"HK1", "HK2", "HK3"}));

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
        buttonOK.addActionListener(e-> onOK());

        buttonCancel.addActionListener(e -> onCancel());

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
        String name = String.valueOf(nameComboBox.getSelectedItem());
        int year = Integer.parseInt(yearTextField.getText());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String temp1 = simpleDateFormat.format(startDateChooser.getDate());
        String temp2 = simpleDateFormat.format(endDateChooser.getDate());
        Date startDate = Date.valueOf(temp1);
        Date endDate = Date.valueOf(temp2);

        Semester semester = new Semester(name,year,startDate,endDate);
        if(SemesterDAO.add(semester)){
            SemesterTabMod.mTableManager.addRow(semester);
            dispose();
        }
    }

    private void onCancel() {
        dispose();
    }
}

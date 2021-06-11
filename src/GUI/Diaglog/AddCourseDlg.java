package GUI.Diaglog;

import GUI.Tabs.CourseTabMod;
import dao.*;
import pojo.*;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class AddCourseDlg extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField maxSlotTextField;
    private JComboBox subjectComboBox;
    private JTextField roomTextField;
    private JComboBox dayOfWeekComboBox;
    private JComboBox shiftComboBox;
    private JComboBox teacherComboBox;
    private JTextField courseTextField;
    List<Subject> subjects;
    List<Teacher> teachers;
    List<Shift> shifts;

    public AddCourseDlg() {
        this.setTitle("Thêm học phần mới");
        this.initComponents();
        this.addEventListener();
        this.setModal(true);
        this.pack();
        // this following method must call after pack() method to set Java App Window to center of your computer screen
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
    private void initComponents(){
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        subjects = SubjectDAO.getAll();
        List<String> subjectNames = subjects.stream().map(p -> p.getSubjectName()).collect(Collectors.toList());
        subjectComboBox.setModel(new DefaultComboBoxModel<>(subjectNames.toArray()));

        teachers = TeacherDAO.getAll();
        List<String> teacherNames = teachers.stream().map(p -> p.getFullname()).collect(Collectors.toList());
        teacherComboBox.setModel(new DefaultComboBoxModel<>(teacherNames.toArray()));
        dayOfWeekComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Thứ 2","Thứ 3","Thứ 4","Thứ 5","Thứ 6","Thứ 7","Chủ Nhật"}));

        shifts = ShiftDAO.getAll();
        SimpleDateFormat sdF = new SimpleDateFormat("HH:mm");
        List<String> shiftPeriods = shifts.stream().map(p -> sdF.format(p.getStartAt()) +" - "+ sdF.format(p.getEndAt())).collect(Collectors.toList());

        shiftComboBox.setModel(new DefaultComboBoxModel<>(shiftPeriods.toArray()));
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
        String courseId = courseTextField.getText();
        Subject subject = subjects.get(subjectComboBox.getSelectedIndex());
        Teacher teacher = teachers.get(teacherComboBox.getSelectedIndex());
        String room = roomTextField.getText();
        String dayOfWeek = String.valueOf(dayOfWeekComboBox.getSelectedItem());
        Shift shift = shifts.get(shiftComboBox.getSelectedIndex());
        int maximumSlots = Integer.parseInt(maxSlotTextField.getText());
        Semester semester = SemesterDAO.getCurrent();
        Course course = new Course(courseId, dayOfWeek, maximumSlots, subject, teacher, shift, semester, room);
        onAdd(course);

    }
    private void onAdd(Course course){
        if(CourseDAO.add(course)){
            // Update current table
            CourseTabMod.mTableManager.addRow(course);
            JOptionPane.showMessageDialog(this, "Thêm mới thành công");
            dispose();
        }
    }
    private void onCancel() {
        dispose();
    }
}

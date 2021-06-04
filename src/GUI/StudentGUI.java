package GUI;

import pojo.Student;

import javax.swing.*;

/**
 * GUI
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/4/2021 - 9:41 PM
 * @Description
 */
public class StudentGUI extends JFrame{
    private JPanel panel;

    public StudentGUI() {
        super("Hệ thống đăng ký khoá học");
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(1920 / 2 - 200, 1080 / 2 - 200);
        this.pack();
        this.setVisible(true);
    }
}

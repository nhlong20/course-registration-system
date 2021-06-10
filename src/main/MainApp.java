package main;

import GUI.LoginGUI;
import GUI.ModeratorGUI;
import GUI.StudentGUI;
import pojo.Account;

import javax.swing.*;

/**
 * PACKAGE_NAME
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/19/2021 - 8:18 PM
 * @Description
 */
public class MainApp {
    private static Account currentAccount;
    public enum ViewControl {
        MODERATOR,
        STUDENT,
        LOGIN
    }
    public static void main(String[] args) {
        invokeGUI(ViewControl.MODERATOR);
    }
    public static void invokeGUI(ViewControl view) {
        switch (view) {
            case LOGIN -> SwingUtilities.invokeLater(() -> new LoginGUI());
            case MODERATOR -> SwingUtilities.invokeLater(()-> new ModeratorGUI());
            case STUDENT -> SwingUtilities.invokeLater(() -> new StudentGUI());
        }
    }
    public static Account getCurrentAccount() {
        return currentAccount;
    }
    public static void setCurrentAccount(Account currentAccount) {
        MainApp.currentAccount = currentAccount;
    }

}

package view;

import business.UserManager;
import core.Helper;

import javax.swing.*;

public class LoginView extends Layout {
    private JPanel container;
    private JLabel label_banner;
    private JLabel label_welcome;
    private JTextField field_username;
    private JButton button_login;
    private JPasswordField field_password;
    private JLabel label_password;
    private JLabel label_username;

    private UserManager userManager;

    public LoginView() {
        userManager = new UserManager();
        Helper.setupWindow(this, container, "Login", 500, 400);

        button_login.addActionListener(e -> login());
    }

    private void login() {
        if (!Helper.checkAndShowEmptyFields(this, field_username, field_password)) {
            return ;
        }

        String username = field_username.getText();
        String password = new String(field_password.getPassword());

        if (userManager.validateUser(username, password)) {
            Helper.showMessage(this, "Login successful!");
            // Giriş başarılı, admin panele yönlendir
            new AdminView();
            dispose();  // Mevcut pencereyi kapat
        } else {
            Helper.showMessage(this, "Invalid username or password. Please try again.");
        }
    }

    public static void main(String[] args) {
        Helper.setNimbusLookAndFeel();
        new LoginView();
    }
}

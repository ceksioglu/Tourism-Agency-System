package view;

import business.UserManager;
import core.Helper;
import entity.User;

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
        Helper.setupWindow(this, container, "User Panel", 400, 300);

        button_login.addActionListener(e -> login());
    }

    private void login() {
        String username = field_username.getText();
        String password = new String(field_password.getPassword());

        if (userManager.validateUser(username, password)) {
            User user = userManager.getUserByUsername(username); // Kullanıcıyı al
            Helper.showMessage(this,"Login successful!");

            // Kullanıcının rolüne göre uygun pencereyi aç
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                new AdminView(user.getUsername());
            } else if ("PERSONNEL".equalsIgnoreCase(user.getRole())) {
                new UserView(user.getUsername());
            }

            // Giriş penceresini kapat
            dispose();
        } else {
            Helper.showMessage(this,"Invalid username or password. Please try again.");
        }
    }

    public static void main(String[] args) {
        Helper.setNimbusLookAndFeel();
        new LoginView();
    }
}

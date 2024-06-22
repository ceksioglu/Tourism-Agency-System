package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

/**
 * The LoginView class provides an interface for users to log in.
 * Based on the role of the user, it redirects to the appropriate view.
 */
public class LoginView extends Layout {
    private JPanel container;
    private JLabel label_banner;
    private JLabel label_welcome;
    private JTextField field_username;
    private JButton button_login;
    private JPasswordField field_password;
    private JLabel label_password;
    private JLabel label_username;

    private final UserManager userManager;

    /**
     * Initializes the login interface and sets up necessary components.
     */
    public LoginView() {
        userManager = new UserManager();
        Helper.setupWindow(this, container, "User Panel", 400, 300);

        button_login.addActionListener(e -> login());
    }

    /**
     * Handles the login process. Validates user credentials and opens the corresponding view based on user role.
     */
    private void login() {
        String username = field_username.getText();
        String password = new String(field_password.getPassword());

        if (userManager.validateUser(username, password)) {
            User user = userManager.getUserByUsername(username); // Retrieve the user
            Helper.showMessage(this, "Login successful!");

            // Open the appropriate window based on the user's role
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                new AdminView(user);
            } else if ("PERSONNEL".equalsIgnoreCase(user.getRole())) {
                new UserView(user);
            }

            // Close the login window
            dispose();
        } else {
            Helper.showMessage(this, "Invalid username or password. Please try again.");
        }
    }

    /**
     * The main entry point of the application. Sets the Nimbus look and feel and starts the LoginView.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Helper.setNimbusLookAndFeel();
        new LoginView();
    }
}

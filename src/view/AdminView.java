package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The AdminView class provides an interface for admin users to view, add, update, and delete other users.
 */
public class AdminView extends Layout {
    private JPanel container;
    private JLabel label_banner;
    private JTable table_user;
    private JButton button_exit;
    private JScrollPane scroll_user;
    private JLabel label_user;
    private JButton button_update_user;
    private JButton button_create_user;
    private JButton button_delete_user;
    private JLabel label_welcome;
    private JComboBox<String> combo_role;

    private final UserManager userManager;
    private User currentUser;
    private DefaultTableModel tableModel;

    /**
     * Initializes the user management interface and sets up necessary components.
     *
     * @param user The logged-in user
     */
    public AdminView(User user) {
        userManager = new UserManager();
        this.currentUser = user;
        Helper.setupWindow(this, container, "Admin Panel", 800, 600);

        tableModel = new DefaultTableModel();
        table_user.setModel(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Username");
        tableModel.addColumn("Role");

        combo_role.addItem("ALL");
        combo_role.addItem("ADMIN");
        combo_role.addItem("PERSONNEL");

        label_welcome.setText("Welcome back, " + currentUser.getUsername() + "!");

        loadUsers(null);

        button_create_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser();
            }
        });

        button_update_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        button_delete_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        button_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        combo_role.addActionListener(e -> {
            String selectedRole = (String) combo_role.getSelectedItem();
            if (selectedRole != null && !selectedRole.equals("ALL")) {
                loadUsers(selectedRole);
            } else {
                loadUsers(null);
            }
        });
    }

    /**
     * Loads users and adds them to the table.
     *
     * @param role The role to filter users by (null if all users should be loaded)
     */
    private void loadUsers(String role) {
        List<User> users;
        if (role == null) {
            users = userManager.getAllUsers();
        } else {
            users = userManager.getUsersByRole(role);
        }
        tableModel.setRowCount(0); // Clear existing data in the table
        for (User user : users) {
            tableModel.addRow(new Object[]{user.getId(), user.getUsername(), user.getRole()});
        }
    }

    /**
     * Creates a new user.
     */
    private void createUser() {
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        String[] roles = {"ADMIN", "PERSONNEL"};
        JComboBox<String> roleBox = new JComboBox<>(roles);

        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField,
                "Role:", roleBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Create User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = (String) roleBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                Helper.showMessage(this, "Please fill in all fields.");
            } else {
                userManager.addUser(new User(0, username, password, role));
                Helper.showMessage(this, "User created successfully!");
                loadUsers(null);
            }
        }
    }

    /**
     * Updates the selected user.
     */
    private void updateUser() {
        int selectedRow = table_user.getSelectedRow();
        if (selectedRow == -1) {
            Helper.showMessage(this, "Please select a user to update.");
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0);
        String username = (String) tableModel.getValueAt(selectedRow, 1);
        String role = (String) tableModel.getValueAt(selectedRow, 2);

        JTextField usernameField = new JTextField(username);
        JTextField passwordField = new JTextField();
        String[] roles = {"ADMIN", "PERSONNEL"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        roleBox.setSelectedItem(role);

        Object[] message = {
                "Username:", usernameField,
                "Password (Leave blank to keep current password):", passwordField,
                "Role:", roleBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Update User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String newUsername = usernameField.getText();
            String newPassword = passwordField.getText();
            String newRole = (String) roleBox.getSelectedItem();

            if (newUsername.isEmpty()) {
                Helper.showMessage(this, "Username cannot be empty.");
            } else {
                User user = userManager.getUserById(userId);
                user.setUsername(newUsername);
                if (!newPassword.isEmpty()) {
                    user.setPassword(newPassword);
                }
                user.setRole(newRole);
                userManager.updateUser(user);
                Helper.showMessage(this, "User updated successfully!");
                loadUsers(null);
            }
        }
    }

    /**
     * Deletes the selected user.
     */
    private void deleteUser() {
        int selectedRow = table_user.getSelectedRow();
        if (selectedRow == -1) {
            Helper.showMessage(this, "Please select a user to delete.");
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0);

        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Delete User", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            userManager.deleteUser(userId);
            Helper.showMessage(this, "User deleted successfully!");
            loadUsers(null);
        }
    }

    /**
     * The main entry point of the application. Sets the Nimbus look and feel and starts the AdminView.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Helper.setNimbusLookAndFeel();
        User tempUser = new User();
        tempUser.setUsername("admintest");
        tempUser.setId(11);
        tempUser.setRole("ADMIN");
        new AdminView(tempUser);
    }
}

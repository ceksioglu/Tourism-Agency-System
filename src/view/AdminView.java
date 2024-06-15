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
 * AdminView sınıfı, admin kullanıcıların diğer kullanıcıları görüntülemesi, eklemesi, güncellemesi ve silmesi için bir arayüz sağlar.
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

    private UserManager userManager;
    private DefaultTableModel tableModel;

    /**
     * AdminView sınıfı, kullanıcı yönetimi arayüzünü başlatır ve gerekli bileşenleri ayarlar.
     */
    public AdminView() {
        userManager = new UserManager();
        Helper.setupWindow(this, container, "Admin Panel", 600, 500);

        tableModel = new DefaultTableModel();
        table_user.setModel(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Username");
        tableModel.addColumn("Role");

        loadUsers();

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
    }

    /**
     * Tüm kullanıcıları yükler ve tabloya ekler.
     */
    private void loadUsers() {
        List<User> users = userManager.getAllUsers();
        tableModel.setRowCount(0); // Tablodaki mevcut verileri temizle
        for (User user : users) {
            tableModel.addRow(new Object[]{user.getId(), user.getUsername(), user.getRole()});
        }
    }

    /**
     * Yeni bir kullanıcı oluşturur.
     */
    private void createUser() {
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        String[] roles = {"ADMIN", "PERSONNEL"};
        JComboBox<String> roleBox = new JComboBox<>(roles);

        Object[] message = {
                "User Name:", usernameField,
                "Password:", passwordField,
                "Role:", roleBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Create User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = (String) roleBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                Helper.showMessage(this, "Lütfen tüm alanları doldurun.");
            } else {
                userManager.addUser(new User(0, username, password, role));
                Helper.showMessage(this, "Kullanıcı başarıyla oluşturuldu!");
                loadUsers();
            }
        }
    }

    /**
     * Seçilen kullanıcıyı günceller.
     */
    private void updateUser() {
        int selectedRow = table_user.getSelectedRow();
        if (selectedRow == -1) {
            Helper.showMessage(this, "Lütfen güncellemek için bir kullanıcı seçin.");
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
                "Kullanıcı Adı:", usernameField,
                "Şifre (Mevcut şifreyi korumak için boş bırakın):", passwordField,
                "Rol:", roleBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Kullanıcıyı Güncelle", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String newUsername = usernameField.getText();
            String newPassword = passwordField.getText();
            String newRole = (String) roleBox.getSelectedItem();

            if (newUsername.isEmpty()) {
                Helper.showMessage(this, "Kullanıcı adı boş olamaz.");
            } else {
                User user = userManager.getUserById(userId);
                user.setUsername(newUsername);
                if (!newPassword.isEmpty()) {
                    user.setPassword(newPassword);
                }
                user.setRole(newRole);
                userManager.updateUser(user);
                Helper.showMessage(this, "Kullanıcı başarıyla güncellendi!");
                loadUsers();
            }
        }
    }

    /**
     * Seçilen kullanıcıyı siler.
     */
    private void deleteUser() {
        int selectedRow = table_user.getSelectedRow();
        if (selectedRow == -1) {
            Helper.showMessage(this, "Lütfen silmek için bir kullanıcı seçin.");
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0);

        int option = JOptionPane.showConfirmDialog(this, "Bu kullanıcıyı silmek istediğinizden emin misiniz?", "Kullanıcıyı Sil", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            userManager.deleteUser(userId);
            Helper.showMessage(this, "Kullanıcı başarıyla silindi!");
            loadUsers();
        }
    }

    /**
     * Uygulamanın giriş noktası. Nimbus görünümünü ayarlayıp AdminView'i başlatır.
     * @param args Komut satırı argümanları
     */
    public static void main(String[] args) {
        Helper.setNimbusLookAndFeel();
        new AdminView();
    }
}

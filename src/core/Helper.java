package core;

import javax.swing.*;
import java.awt.*;

public class Helper {

    // Nimbus teması ayarlama metodu
    public static void setNimbusLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // JTextField'lerin boş olup olmadığını kontrol eden metot
    public static boolean areTextFieldsEmpty(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    // Mesaj gösteren metot
    public static void showMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message);
    }

    // TextField'lerin boş olup olmadığını kontrol eden ve mesaj gösteren metot
    public static boolean checkAndShowEmptyFields(Component parent, JTextField... fields) {
        if (areTextFieldsEmpty(fields)) {
            showMessage(parent, "Please fill all fields.");
            return false;
        }
        return true;
    }

    // Pencereyi ekranın ortasında konumlandıran metot
    public static void centerWindow(Window window) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - window.getSize().width) / 2;
        int y = (dim.height - window.getSize().height) / 2;
        window.setLocation(x, y);
    }

    // Pencereyi ayarlayan metot
    public static void setupWindow(JFrame frame, JPanel contentPane, String title, int width, int height) {
        frame.setContentPane(contentPane);
        frame.setTitle(title);
        frame.setSize(width, height);
        centerWindow(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

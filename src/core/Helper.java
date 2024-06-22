package core;

import javax.swing.*;
import java.awt.*;

/**
 * The Helper class provides utility methods for setting the Nimbus look and feel,
 * checking if JTextFields are empty, showing messages, centering windows, and setting up windows.
 */
public class Helper {

    /**
     * Sets the Nimbus look and feel for the application.
     */
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

    /**
     * Checks if any of the provided JTextFields are empty.
     *
     * @param fields The JTextFields to check.
     * @return true if any of the fields are empty, false otherwise.
     */
    public static boolean areTextFieldsEmpty(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Displays a message in a dialog.
     *
     * @param parent The parent component for the dialog.
     * @param message The message to be displayed.
     */
    public static void showMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message);
    }

    /**
     * Checks if the provided JTextFields are empty and shows a message if any are empty.
     *
     * @param parent The parent component for the dialog.
     * @param fields The JTextFields to check.
     * @return true if all fields are filled, false if any field is empty.
     */
    public static boolean checkAndShowEmptyFields(Component parent, JTextField... fields) {
        if (areTextFieldsEmpty(fields)) {
            showMessage(parent, "Please fill all fields.");
            return true;
        }
        return false;
    }

    /**
     * Centers the given window on the screen.
     *
     * @param window The window to be centered.
     */
    public static void centerWindow(Window window) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - window.getSize().width) / 2;
        int y = (dim.height - window.getSize().height) / 2;
        window.setLocation(x, y);
    }

    /**
     * Sets up a JFrame with the given content pane, title, width, and height, and centers it on the screen.
     *
     * @param frame The JFrame to set up.
     * @param contentPane The content pane to set on the JFrame.
     * @param title The title of the JFrame.
     * @param width The width of the JFrame.
     * @param height The height of the JFrame.
     */
    public static void setupWindow(JFrame frame, JPanel contentPane, String title, int width, int height) {
        frame.setContentPane(contentPane);
        frame.setTitle(title);
        frame.setSize(width, height);
        centerWindow(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

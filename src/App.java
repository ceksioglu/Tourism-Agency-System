import core.Helper;
import entity.User;
import view.AdminView;
import view.LoginView;

/**
 * The App class is the entry point of the application. It sets the Nimbus look and feel and starts the login view.
 */
public class App {

    /**
     * The main method that starts the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Set Nimbus theme
        Helper.setNimbusLookAndFeel();

        // Start the app
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginView();

            // Uncomment the following block to directly open the AdminView for testing purposes
            /*
            User tempUser = new User();
            tempUser.setUsername("admintest");
            tempUser.setRole("ADMIN");
            tempUser.setId(11);
            tempUser.setPassword("1234");
            new AdminView(tempUser);
            */
        });
    }
}

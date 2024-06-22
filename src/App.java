import core.Helper;
import entity.User;
import view.AdminView;
import view.LoginView;

public class App {
    public static void main(String[] args) {
        // Set Nimbus theme
        Helper.setNimbusLookAndFeel();

        // Start the app
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginView();
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

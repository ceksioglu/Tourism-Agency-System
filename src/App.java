import core.Helper;
import view.LoginView;

public class App {
    public static void main(String[] args) {
        // Nimbus temasını ayarla
        Helper.setNimbusLookAndFeel();

        // LoginView'ı başlat
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginView();
            }
        });
    }
}

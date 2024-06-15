package view;

import javax.swing.*;
import java.awt.*;
import core.Helper;

public class Layout extends JFrame {

    public Layout() {
        // Nimbus temasını ayarla
        Helper.setNimbusLookAndFeel();

        // Pencereyi ortala
        Helper.centerWindow(this);

        // Varsayılan pencere kapatma işlemi
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Pencerede mesaj göster
    public void showMessage(String message) {
        Helper.showMessage(this, message);
    }

    // TextField'lerin boş olup olmadığını kontrol et ve mesaj göster
    protected boolean checkAndShowEmptyFields(JTextField... fields) {
        return Helper.checkAndShowEmptyFields(this, fields);
    }
}

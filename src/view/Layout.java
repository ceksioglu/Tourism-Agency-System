package view;

import javax.swing.*;

import core.Helper;

/**
 * The Layout class serves as a base class for setting up common features and utilities for JFrame-based windows.
 * It extends JFrame and provides helper methods for setting the Nimbus look and feel, centering the window,
 * showing messages, and checking if text fields are empty.
 */
public class Layout extends JFrame {

    /**
     * Constructs a Layout object, sets the Nimbus look and feel, centers the window,
     * and sets the default close operation.
     */
    public Layout() {
        // Set Nimbus look and feel
        Helper.setNimbusLookAndFeel();

        // Center the window
        Helper.centerWindow(this);

        // Default window close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

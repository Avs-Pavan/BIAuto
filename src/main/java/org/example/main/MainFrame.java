package org.example.main;

import org.example.datasourceselection.DataSourceFrame;

import javax.swing.*;

/**
 * The type Main frame.
 * This class is the main frame of the application.
 * It is the first frame that is shown to the user.
 * It contains the main panel.
 * It also contains the method to show the data sources panel.
 * This class extends JFrame.
 *
 */
public class MainFrame extends JFrame {

    /**
     * The Main panel.
     */
    MainPanel mainPanel;

    /**
     * Instantiates a new Main frame.
     */
    public MainFrame() {
        setTitle("Power Bi Automate - TAMU-CC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(410, 350);
        setLocationRelativeTo(null);
        showMainPanel();

    }

    /**
     * Show main panel.
     */
    public void showMainPanel() {
        setContentPane(new MainPanel(this));
        revalidate();
        repaint();
    }

    /**
     * Show data sources panel.
     */
    public void showDataSourcesPanel() {
        DataSourceFrame dataSourceFrame = new DataSourceFrame();
        dataSourceFrame.setVisible(true);
    }


}

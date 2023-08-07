package org.example.datasourceselection;

import org.example.addnew.AddDataSourceFrame;

import javax.swing.*;

/**
 * The type Data source frame.
 * This class is used to create the frame for the data source selection
 * It extends JFrame and uses the DataSourcesPanel as the content pane
 * It also has a method to show the add new data source frame
 * It also has a method to refresh the data source list
 * It is used by the MainFrame class
 * It is used by the DataSourcesPanel class
 * It is used by the AddDataSourceFrame class
 * It is used by the AddDataSourcePanel class
 * It is used by the DataSource class
 * It is used by the DataSourceList class
 * It is used by the DataSourceTableModel class
 *
 */
public class DataSourceFrame extends JFrame {

    /**
     * Instantiates a new Data source frame.
     */
    public DataSourceFrame() {
        setTitle("Power Bi Automate - TAMU-CC");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(700, 370);
        setLocationRelativeTo(null); // Center the window on the screen
        setContentPane(new DataSourcesPanel(this));

    }

    /**
     * Show add new data source panel.
     */
    public void showAddNewDataSourcePanel() {
        AddDataSourceFrame addDataSourceFRame = new AddDataSourceFrame(this);
        addDataSourceFRame.setVisible(true);
    }

    /**
     * Refresh data source list.
     * This method is used to refresh the data source list
     * It is used by the DataSourcesPanel class
     * It is used by the AddDataSourceFrame class
     * It is used by the AddDataSourcePanel class
     *
     */
    public void refreshDataSourceList() {
        ((DataSourcesPanel) getContentPane()).refreshDataSourceList();
    }
}

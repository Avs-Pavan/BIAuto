package org.example.addnew;

import org.example.datasourceselection.DataSourceFrame;

import javax.swing.*;

/**
 * The type Add data source frame.
 * This class is used to create a new frame to add a new data source
 * It extends JFrame
 * It has a constructor that takes in a DataSourceFrame object
 * It has a method to refresh the data source list
 * It has a method to create a new data source
 * It has a method to create a new data source panel
 *
 */
public class AddDataSourceFrame extends JFrame {

    /**
     * The Data source frame.
     */
    DataSourceFrame dataSourceFrame;

    /**
     * Instantiates a new Add data source frame.
     *
     * @param dataSourceFrame the data source frame
     */
    public AddDataSourceFrame(DataSourceFrame dataSourceFrame) {
        this.dataSourceFrame = dataSourceFrame;
        setTitle("Power Bi Automate - TAMU-CC");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(420, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        setContentPane(new AddDataSourcePanel(this));

    }

    /**
     * Refresh data source list.
     */
    public void refreshDataSourceList() {
        dataSourceFrame.refreshDataSourceList();
    }


}

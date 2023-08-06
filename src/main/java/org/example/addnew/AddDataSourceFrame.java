package org.example.addnew;

import org.example.datasourceselection.DataSourceFrame;

import javax.swing.*;

public class AddDataSourceFrame extends JFrame {

    //constructor
    DataSourceFrame dataSourceFrame;
    public AddDataSourceFrame(DataSourceFrame dataSourceFrame) {
        this.dataSourceFrame = dataSourceFrame;
        setTitle("Power Bi Automate - TAMU-CC");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(420, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        setContentPane(new AddDataSourcePanel(this));

    }

    public void refreshDataSourceList(){
        dataSourceFrame.refreshDataSourceList();
    }


}

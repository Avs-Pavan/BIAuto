package org.example.datasourceselection;

import org.example.addnew.AddDataSourceFrame;

import javax.swing.*;

public class DataSourceFrame extends JFrame {

        public DataSourceFrame() {
            setTitle("Power Bi Automate - TAMU-CC");
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
            setSize(700, 370);
            setLocationRelativeTo(null); // Center the window on the screen
            setContentPane(new DataSourcesPanel(this));

        }

        public void showAddNewDataSourcePanel(){
           AddDataSourceFrame addDataSourceFRame = new AddDataSourceFrame(this);
                addDataSourceFRame.setVisible(true);
        }

        public  void refreshDataSourceList(){
            ((DataSourcesPanel) getContentPane()).refreshDataSourceList();
        }
}

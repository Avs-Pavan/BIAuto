package org.example.main;

import org.example.datasourceselection.DataSourceFrame;

import javax.swing.*;

public class MainFrame extends JFrame {

    MainPanel mainPanel;
    public MainFrame() {
        setTitle("Power Bi Automate - TAMU-CC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(410, 350);
        setLocationRelativeTo(null);
        showMainPanel();

    }

    public  void showMainPanel(){
        setContentPane(new MainPanel(this));
        revalidate();
        repaint();
    }

    public void showDataSourcesPanel(){
        DataSourceFrame dataSourceFrame = new DataSourceFrame();
        dataSourceFrame.setVisible(true);
    }


}

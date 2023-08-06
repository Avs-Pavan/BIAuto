package org.example.datasourceselection;

import org.example.models.DataSource;

import javax.swing.*;

public class DataSourceDetailsDialog extends JDialog{
    public DataSourceDetailsDialog(DataSourceFrame dataSourceFrame, DataSource dataSource) {
        // show  details dialog

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create and add JLabels to the panel
        JLabel label1 = new JLabel(dataSource.getName());
        JLabel label2 = new JLabel(dataSource.getDescription());
        JLabel label3 = new JLabel("Label 3");

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);

        setLocationRelativeTo(dataSourceFrame);
        setSize(300, 200);
        // Add the panel to the frame's content pane
        getContentPane().add(panel);

    }
}

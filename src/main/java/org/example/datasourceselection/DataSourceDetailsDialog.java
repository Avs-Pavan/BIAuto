package org.example.datasourceselection;

import org.example.models.DataSource;

import javax.swing.*;

/**
 * The type Data source details dialog.
 * This class is used to show the details of a data source
 * when the user clicks on the data source in the data source selection frame
 * <p>
 *     This class extends JDialog
 *     <br>
 *     This class is used by the DataSourceFrame class
 *     <br>
 *     This class is used by the DataSourceSelectionFrame class
 *     <br>
 * </p>
 */
public class DataSourceDetailsDialog extends JDialog {
    /**
     * Instantiates a new Data source details dialog.
     *
     * @param dataSourceFrame the data source frame
     * @param dataSource      the data source
     */
    public DataSourceDetailsDialog(DataSourceFrame dataSourceFrame, DataSource dataSource) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel(dataSource.getName());
        JLabel label2 = new JLabel(dataSource.getDescription());
        JLabel label3 = new JLabel("Label 3");
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        setLocationRelativeTo(dataSourceFrame);
        setSize(300, 200);
        getContentPane().add(panel);

    }
}

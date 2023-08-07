package org.example.datasourceselection;

import org.example.Upload.UploadFileFrame;
import org.example.models.DataSource;
import org.example.util.FileHelper;

import javax.swing.*;
import java.io.File;
import java.util.List;

/**
 * The type Data sources panel.
 * This class is used to display the list of data sources
 * This class is used to add new data sources
 * This class is used to delete data sources
 * This class is used to update data sources
 * This class is used to select a data source
 * This class is used to display the details of a data source
 * This class is used to upload a file to a data source
 *
 */
public class DataSourcesPanel extends JPanel {

    DefaultListModel<String> model;
    private final DataSourceFrame dataSourceFrame;
    private JButton addNewButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JList<String> jList1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;

    /**
     * Instantiates a new Data sources panel.
     *
     * @param dataSourceFrame the data source frame
     */
    public DataSourcesPanel(DataSourceFrame dataSourceFrame) {
        this.dataSourceFrame = dataSourceFrame;
        initComponents();
    }

    /**
     * Add new data source.
     * This method is called when the add new button is clicked
     *
     * @param evt the evt
     */
    private void addNewDataSource(java.awt.event.ActionEvent evt) {
        dataSourceFrame.showAddNewDataSourcePanel();
    }

    /**
     * Init components.
     * This method is called from within the constructor to initialize the form.
     * This method adds the components to the panel
     *
     */
    public void initComponents() {
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        addNewButton = new JButton();
        jScrollPane1 = new JScrollPane();
        model = new DefaultListModel<String>();
        final List<DataSource> strings = new FileHelper().readJsonListFromFile(new File("data_sources.json"));
        for (DataSource string : strings) {
            model.addElement(string.getName());
        }
        jList1 = new JList<>(model);
        jLabel2 = new JLabel();

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Select a data source to view details");

        addNewButton.setText("Add new ");
        addNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewDataSource(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                                .addComponent(addNewButton)
                                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(13, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(addNewButton))
                                .addContainerGap())
        );
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {

                if (!evt.getValueIsAdjusting()) {
                    // show dialog with details
                    DataSource dataSource = new FileHelper().readJsonListFromFile(new File("data_sources.json")).get(jList1.getSelectedIndex());
                    new UploadFileFrame(dataSource).setVisible(true);
                    jList1.clearSelection();

                }
            }
        });

        jScrollPane1.setViewportView(jList1);

        jLabel2.setText("Available data sources");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(7, 7, 7)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }

    /**
     * Refresh data source list.
     * This method is called when a new data source is added
     * or when a data source is deleted
     * or when a data source is updated
     * or when a data source is selected
     *
     */
    public void refreshDataSourceList() {
        System.out.println("refreshDataSourceList");
        model.clear();
        List<DataSource> strings = new FileHelper().readJsonListFromFile(new File("data_sources.json"));
        for (DataSource dataSource : strings) {
            model.addElement(dataSource.getName());
        }
        jList1.repaint();
    }
}

package org.example.datasourceselection;

import org.example.Upload.UploadFileFrame;
import org.example.models.DataSource;
import org.example.util.FileHelper;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class DataSourcesPanel extends JPanel {

    private DataSourceFrame dataSourceFrame;

    public DataSourcesPanel(DataSourceFrame dataSourceFrame) {
        this.dataSourceFrame = dataSourceFrame;
        initComponents();
    }

    private void addNewDataSource(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        dataSourceFrame.showAddNewDataSourcePanel();
    }

    private JButton addNewButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JList<String> jList1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;


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

//        jList1.setModel(new javax.swing.AbstractListModel<String>() {
//            final List<DataSource> strings = new FileHelper().readJsonListFromFile(new File("data_sources.json"));
//
//            public int getSize() {
//                return strings.size();
//            }
//
//            public String getElementAt(int i) {
//                return strings.get(i).getName();
//            }
//        });

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

    DefaultListModel<String> model;

    public void refreshDataSourceList() {
        //TODO
        System.out.println("refreshDataSourceList");
        model.clear();
        List<DataSource> strings = new FileHelper().readJsonListFromFile(new File("data_sources.json"));
        for (DataSource dataSource : strings) {
            model.addElement(dataSource.getName());
        }
        jList1.repaint();
    }
}

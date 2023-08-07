package org.example.addnew;

import org.example.models.DataSource;
import org.example.util.FileHelper;

import javax.swing.*;
import java.io.File;
import java.util.List;

/**
 * The type Add data source panel.
 */
public class AddDataSourcePanel extends JPanel {
    /**
     * The F rame.
     */
    AddDataSourceFrame fRame;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private JTextField jTextField1;

    /**
     * Instantiates a new Add data source panel.
     *
     * @param addDataSourceFRame the add data source f rame
     */
    AddDataSourcePanel(AddDataSourceFrame addDataSourceFRame) {
        this.fRame = addDataSourceFRame;
        init();
    }

    private void init() {
        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        jLabel2 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jButton1 = new JButton();


        jLabel1.setText("Data source name");

        jLabel2.setText("Description");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Submit");
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextField1)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                                        .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(31, 31, 31))
        );


        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String name = jTextField1.getText();
                String description = jTextArea1.getText();
                if (name.equals("") || description.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields");
                } else {
                    FileHelper fileHelper = new FileHelper();
                    File file = fileHelper.getFile("data_sources.json");
                    List<DataSource> dataSourceList = fileHelper.readJsonListFromFile(file);

                    DataSource dataSource = new DataSource(name, description);
                    for (DataSource ds : dataSourceList) {
                        if (ds.getName().equals(dataSource.getName())) {
                            JOptionPane.showMessageDialog(null, "Data source already exists");
                            return;
                        }
                    }

                    // create new file
                    // add data source to file
                    // add data source to list
                    dataSourceList.add(dataSource);
                    fileHelper.writeJsonListToFile(dataSourceList, file);

                    JOptionPane.showMessageDialog(null, "Data source added successfully");
                    jTextField1.setText("");
                    jTextArea1.setText("");
                    fRame.refreshDataSourceList();
                    fRame.dispose();

                }
            }
        });
    }


}

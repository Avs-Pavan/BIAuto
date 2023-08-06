package org.example.main;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.example.util.DatabaseProperties;
import org.example.util.FileHelper;
import org.example.util.ParseUtils;
import javax.swing.*;


public class MainPanel extends javax.swing.JPanel {

    private final MainFrame mainFrame;

    public MainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
        startProcess();
    }

    private void initComponents() {

        JLabel jLabel1 = new JLabel();
        JLabel dbLinkLabel = new JLabel();
        dbUrl = new javax.swing.JTextField();
        JLabel jLabel = new JLabel();
        dbName = new javax.swing.JTextField();
        continueButton = new javax.swing.JButton();
        jLabel.setText("Enter Database Name:");
        dbLinkLabel.setText("Enter db url:");
        dbUrl.setText(databaseProperties.getDbUrl());
        dbName.setText(databaseProperties.getDbName());


        continueButton.setText("Continue");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(dbLinkLabel)
                                                        .addComponent(dbUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel)
                                                        .addComponent(dbName, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(31, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(continueButton)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(dbLinkLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dbUrl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dbName)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(continueButton)
                                .addContainerGap(12, Short.MAX_VALUE))
        );
    }


    // Variables declaration - do not modify
    private javax.swing.JButton continueButton;
    private javax.swing.JTextField dbUrl;
    private javax.swing.JTextField dbName;
    // End of variables declaration

    FileHelper fileHelper = new FileHelper();

    private void startProcess() {
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (dbUrl.getText().isEmpty() || dbName.getText().isEmpty()) {
                    // show alert message using dialog box
                    JOptionPane.showMessageDialog(null, "Please enter db url and db name");
                    return;
                }

                // test connection
                if (testMongoConnection(dbUrl.getText(), dbName.getText()))
                    mainFrame.showDataSourcesPanel();
            }
        });
    }


    DatabaseProperties databaseProperties = new DatabaseProperties();
    ParseUtils parseUtils = new ParseUtils();
    private boolean testMongoConnection(String dbUrl, String dbName) {

        try {
            MongoClient mongoClient = MongoClients.create(dbUrl);
            mongoClient.listDatabases().forEach(db -> System.out.println(db.toJson()));

            // save db url and db name to properties file
            databaseProperties.setDbUrl(dbUrl);
            databaseProperties.setDbName(dbName);
            databaseProperties.save();

            parseUtils.parseUrl(dbUrl);
            return true;
        } catch (Exception e) {
            if (e instanceof IllegalStateException)
                e.printStackTrace();
            else {
                JOptionPane.showMessageDialog(null, e.getMessage()+"\n\nnError connecting to database, Please recheck db url and db name");
            }
            return false;
        }
    }

}
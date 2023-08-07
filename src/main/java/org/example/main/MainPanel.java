package org.example.main;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.example.util.DatabaseProperties;
import org.example.util.FileHelper;
import org.example.util.ParseUtils;

import javax.swing.*;


/**
 * The type Main panel.
 * This class is used to show the main panel of the application
 * It contains the db url and db name fields
 * It also contains the continue button which is used to continue to the next panel
 * The continue button is disabled until the user enters the db url and db name
 * The db url and db name are saved to the properties file
 * The db url is parsed to get the host and port
 * The db name is used to create the database
 * The continue button is used to show the data sources panel
 * The data sources panel is used to show the data sources of the database
 */
public class MainPanel extends javax.swing.JPanel {

    private final MainFrame mainFrame;
    /**
     * The File helper.
     */
    FileHelper fileHelper = new FileHelper();
    /**
     * The Database properties.
     */
    DatabaseProperties databaseProperties = new DatabaseProperties();
    /**
     * The Parse utils.
     */
    ParseUtils parseUtils = new ParseUtils();
    private javax.swing.JButton continueButton;
    private javax.swing.JTextField dbUrl;
    private javax.swing.JTextField dbName;

    /**
     * Instantiates a new Main panel.
     *
     * @param mainFrame the main frame
     */
    public MainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
        startProcess();
    }

    /**
     * Init components.
     */
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

    /**
     * Start process.
     * This method is used to start the process
     * It is used to show the data sources panel
     * It is used to save the db url and db name to the properties file
     * It is used to parse the db url to get the host and port
     * It is used to test the mongo connection
     * It is used to show alert message if the db url and db name are empty
     * It is used to show alert message if the mongo connection is not successful
     * It is used to show alert message if the mongo connection is successful
     *
     */
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


    /**
     * Test mongo connection boolean.
     *
     * @param dbUrl  the db url
     * @param dbName the db name
     * @return the boolean
     */
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
                JOptionPane.showMessageDialog(null, e.getMessage() + "\n\nnError connecting to database, Please recheck db url and db name");
            }
            return false;
        }
    }

}
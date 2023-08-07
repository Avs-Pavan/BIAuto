package org.example.Upload;

import org.example.models.DataSource;
import org.example.models.DomainModel;
import org.example.util.DatabaseProperties;
import org.example.util.ParseUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The type Upload file panel.
 * This class is used to create a panel for the upload file frame
 * This panel is used to upload the csv file to the database
 * This panel is used to parse the database URL and extract the domain name, domain username, and domain password
 * This panel is used to create a new domain model and save it to the database
 *
 */
public class UploadFilePanel extends JPanel {

    /**
     * The Upload file frame.
     */
    UploadFileFrame uploadFileFrame;
    /**
     * The Parse utils.
     * This is used to parse the Urls and extract the domain name, domain username, and domain password
     */
    ParseUtils parseUtils = new ParseUtils();
    /**
     * The Database properties.
     */
    DatabaseProperties databaseProperties = new DatabaseProperties();
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JProgressBar jProgressBar1;
    private JTextField jTextField1;
    private final DataSource dataSource;

    /**
     * Instantiates a new Upload file panel.
     *
     * @param uploadFileFrame the upload file frame
     * @param dataSource      the data source
     */
    public UploadFilePanel(UploadFileFrame uploadFileFrame, DataSource dataSource) {
        this.uploadFileFrame = uploadFileFrame;
        this.dataSource = dataSource;
        initComponents();
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        jLabel2 = new JLabel();
        jButton1 = new JButton();
        jTextField1 = new JTextField();
        jLabel1 = new JLabel();
        jProgressBar1 = new JProgressBar();

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Select csv files");

        jButton1.setText("Select file");

        jTextField1.setText("");

        jButton1.addActionListener(evt -> selectFile());

        jLabel1.setText("Description");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField1)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                                                                .addComponent(jButton1)))
                                                .addGap(37, 37, 37))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1)
                                        .addComponent(jLabel2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jProgressBar1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(jProgressBar1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(144, Short.MAX_VALUE))
        );
    }

    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files (*.csv)", "csv");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(uploadFileFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            processFile(selectedFile.getAbsolutePath());
        }
    }

    /**
     * Sets progress.
     *
     * @param progress the progress
     */
    public void setProgress(int progress) {
        jProgressBar1.setValue(progress);
    }

    /**
     * Process file.
     *
     * @param fileName the file name
     */
    public void processFile(String fileName) {
        try {

            System.out.println("Selected CSV file: " + fileName);
            DomainModel domainModel = parseUtils.parseUrl(databaseProperties.getDbUrl());
            domainModel.setDomainDatabase(databaseProperties.getDbName());
            String batchFilePath = "mongo_import.bat";
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/c",
                    batchFilePath,
                    createURI(domainModel),
                    getCollectionName(),
                    fileName
            );
            System.out.println("cmd.exe /c start cmd.exe /c " + batchFilePath + " " + createURI(domainModel) + " " + getCollectionName() + " " + fileName);
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                JOptionPane.showMessageDialog(uploadFileFrame, "Csv dump successful");
                uploadFileFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(uploadFileFrame, "Csv dump failed. Please try again");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Process file.
     * Create a collection name from the data source name
     * This collection name will be used to create a collection in the database
     *
     */
    private String getCollectionName() {
        return dataSource.getName().toLowerCase().replace(" ", "_");
    }

    /**
     * Create a uri string to connect to the database.
     * This uri string will be used to connect to the database
     * @see <a href="https://docs.mongodb.com/manual/reference/connection-string/">MongoDB Connection String</a>
     * @param domainModel the domain model
     */
    private String createURI(DomainModel domainModel) {
        return "mongodb+srv://" + domainModel.getDomainUsername() + ":" + domainModel.getDomainPassword() + "@" + domainModel.getDomainName() + "/" + domainModel.getDomainDatabase();
    }

}

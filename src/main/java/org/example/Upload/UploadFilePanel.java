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

public class UploadFilePanel extends JPanel {

    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JProgressBar jProgressBar1;
    private JTextField jTextField1;


    UploadFileFrame uploadFileFrame;
    private DataSource dataSource;

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

        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(uploadFileFrame);

        // Check if a file was selected
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File selectedFile = fileChooser.getSelectedFile();


            processFile(selectedFile.getAbsolutePath());
        }
    }

    public void setProgress(int progress) {
        jProgressBar1.setValue(progress);
    }

    ParseUtils parseUtils = new ParseUtils();
    DatabaseProperties databaseProperties = new DatabaseProperties();

    public void processFile(String fileName) {
        try {

            System.out.println("Selected CSV file: " + fileName);
            DomainModel domainModel = parseUtils.parseUrl(databaseProperties.getDbUrl());
            domainModel.setDomainDatabase(databaseProperties.getDbName());

            // Process the selected file (e.g., read and parse the CSV data)
            String batchFilePath = "mongo_import.bat";
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/c",
                    batchFilePath,
                    createURI(domainModel),
                    getCollectionName(),
                    fileName
            );

            System.out.println("cmd.exe /c start cmd.exe /c " + batchFilePath + " " + createURI(domainModel) + " " + getCollectionName() + " " + fileName);

            // Create the process and execute the batch file
            Process process = processBuilder.start();

            // Optional: You can read the output of the batch file if needed
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the batch file execution to complete
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

    private String getCollectionName() {
        return dataSource.getName().toLowerCase().replace(" ", "_");
    }

    private String createURI(DomainModel domainModel) {
        return "mongodb+srv://" + domainModel.getDomainUsername() + ":" + domainModel.getDomainPassword() +"@"+domainModel.getDomainName()+ "/" + domainModel.getDomainDatabase();
    }

}

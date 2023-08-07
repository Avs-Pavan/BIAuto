package org.example.Upload;

import org.example.models.DataSource;

/**
 * The type Upload file frame.
 * This class is used to create a frame for the upload file panel
 */
public class UploadFileFrame extends javax.swing.JFrame {

    /**
     * The Data source.
     * This is the data source that is used to upload the file
     * to the database
     */
    DataSource dataSource;

    /**
     * Instantiates a new Upload file frame.
     *
     * @param dataSource the data source
     */
    public UploadFileFrame(DataSource dataSource) {
        this.dataSource = dataSource;

        setTitle("Power Bi Automate - TAMU-CC");
        setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
        setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));
        setSize(420, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        setContentPane(new UploadFilePanel(this, dataSource));
    }
}

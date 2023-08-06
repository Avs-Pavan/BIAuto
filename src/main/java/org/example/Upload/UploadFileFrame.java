package org.example.Upload;

import org.example.models.DataSource;

public class UploadFileFrame extends  javax.swing.JFrame{

    DataSource dataSource;
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

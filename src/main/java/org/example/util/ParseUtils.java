package org.example.util;

import org.example.models.DomainModel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseUtils {
    
    public DomainModel parseUrl(String connectionString){
        DomainModel domainModel = new DomainModel();
        // Replacing "mongodb+srv" with "http" to allow Java's URI class to parse the connection string
        connectionString = connectionString.replaceFirst("mongodb\\+srv", "http");
        try {
            URI uri = new URI(connectionString);
            String userInfo = uri.getUserInfo();
            String[] userPass = userInfo.split(":");
            String username = userPass[0];
            String password = userPass[1]; // Note: Ensure to replace <password> with your actual password
            String domain = uri.getHost();

            domainModel.setDomainPort("27017");
            domainModel.setDomainName(domain);
            domainModel.setDomainUsername(username);
            domainModel.setDomainPassword(password);
            domainModel.setDomainUrl(connectionString);
            System.out.println(domainModel);

        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalStateException("Unable to parse connection string");
        }

        return domainModel;
    }
}

package org.example.util;

import org.example.models.DomainModel;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * The type Parse utils.
 */
public class ParseUtils {

    /**
     * Parse url domain model.
     *
     * @param connectionString the connection string
     * @return the domain model
     */
    public DomainModel parseUrl(String connectionString) {
        DomainModel domainModel = new DomainModel();
        connectionString = connectionString.replaceFirst("mongodb\\+srv", "http");
        try {
            URI uri = new URI(connectionString);
            String userInfo = uri.getUserInfo();
            String[] userPass = userInfo.split(":");
            String username = userPass[0];
            String password = userPass[1];
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

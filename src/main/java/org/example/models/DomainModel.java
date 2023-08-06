package org.example.models;

public class DomainModel {
    private String domainName;
    private String domainUrl;
    private String domainUsername;
    private String domainPassword;
    private String domainPort;
    private String domainDatabase;

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainUrl() {
        return domainUrl;
    }

    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    public String getDomainUsername() {
        return domainUsername;
    }

    public void setDomainUsername(String domainUsername) {
        this.domainUsername = domainUsername;
    }

    public String getDomainPassword() {
        return domainPassword;
    }

    public void setDomainPassword(String domainPassword) {
        this.domainPassword = domainPassword;
    }

    public String getDomainPort() {
        return domainPort;
    }

    public void setDomainPort(String domainPort) {
        this.domainPort = domainPort;
    }

    public String getDomainDatabase() {
        return domainDatabase;
    }

    public void setDomainDatabase(String domainDatabase) {
        this.domainDatabase = domainDatabase;
    }

    @Override
    public String toString() {
        return "DomainModel{" +
                "domainName='" + domainName + '\'' +
                ", domainUrl='" + domainUrl + '\'' +
                ", domainUsername='" + domainUsername + '\'' +
                ", domainPassword='" + domainPassword + '\'' +
                ", domainPort='" + domainPort + '\'' +
                ", domainDatabase='" + domainDatabase + '\'' +
                '}';
    }
}

package org.example.models;

/**
 * The type Domain model.
 */
public class DomainModel {
    private String domainName;
    private String domainUrl;
    private String domainUsername;
    private String domainPassword;
    private String domainPort;
    private String domainDatabase;

    /**
     * Gets domain name.
     *
     * @return the domain name
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * Sets domain name.
     *
     * @param domainName the domain name
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * Gets domain url.
     *
     * @return the domain url
     */
    public String getDomainUrl() {
        return domainUrl;
    }

    /**
     * Sets domain url.
     *
     * @param domainUrl the domain url
     */
    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    /**
     * Gets domain username.
     *
     * @return the domain username
     */
    public String getDomainUsername() {
        return domainUsername;
    }

    /**
     * Sets domain username.
     *
     * @param domainUsername the domain username
     */
    public void setDomainUsername(String domainUsername) {
        this.domainUsername = domainUsername;
    }

    /**
     * Gets domain password.
     *
     * @return the domain password
     */
    public String getDomainPassword() {
        return domainPassword;
    }

    /**
     * Sets domain password.
     *
     * @param domainPassword the domain password
     */
    public void setDomainPassword(String domainPassword) {
        this.domainPassword = domainPassword;
    }

    /**
     * Gets domain port.
     *
     * @return the domain port
     */
    public String getDomainPort() {
        return domainPort;
    }

    /**
     * Sets domain port.
     *
     * @param domainPort the domain port
     */
    public void setDomainPort(String domainPort) {
        this.domainPort = domainPort;
    }

    /**
     * Gets domain database.
     *
     * @return the domain database
     */
    public String getDomainDatabase() {
        return domainDatabase;
    }

    /**
     * Sets domain database.
     *
     * @param domainDatabase the domain database
     */
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

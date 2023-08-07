package org.example.models;

/**
 * The type Data source.
 * This class is used to create a DataSource object
 * which is used to store the name and description of a data source
 * and is used to display the data sources in the data source list
 * in the data source page
 * <p>
 * The class has two attributes:
 *     <ul>
 *         <li>name</li>
 *         <li>description</li>
 *     </ul>
 *     The class has two constructors:
 *     <ul>
 *         <li>DataSource(String name, String description)</li>
 *         <li>DataSource()</li>
 *     </ul>
 * <p>
 */
public class DataSource {
    /**
     * The Name.
     */
    String name;
    /**
     * The Description.
     */
    String description;

    /**
     * Instantiates a new Data source.
     *
     * @param name        the name
     * @param description the description
     */
//constructor
    public DataSource(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DataSource{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

package org.example.models;

public class DataSource {
    String name;
    String description;
    //constructor
    public DataSource(String name, String description){
        this.name = name;
        this.description = description;
    }
    //getters
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
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

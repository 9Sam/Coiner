package com.blacklion.coiner.entities;

import java.sql.Date;

public class Prize {
    private int id;
    private String name;
    private String description;
    private double cost;
    private Date creationDate;

    public Prize(){}

    public Prize(int id, String name, String description, double cost, Date creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    
}

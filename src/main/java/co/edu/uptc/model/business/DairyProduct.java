package co.edu.uptc.model.business;

import java.util.Date;

public abstract class DairyProduct {
    private String id;
    private String name;
    private Date expirationDate;
    private double calories;
    private Integer units;

    public DairyProduct(String id, String name, Date expirationDate, Integer units) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.units = units;
        this.calories = 0.0;
    }

    public abstract void calcCalories();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }
}

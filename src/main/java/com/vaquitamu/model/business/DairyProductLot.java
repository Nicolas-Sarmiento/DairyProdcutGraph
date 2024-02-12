package com.vaquitamu.model.business;

import java.util.Date;

/**
 * Dairy Products share some properties.
 * Some of the children classes must be share its own
 * properties, but it's just a simulation so it tries
 * to be 'realistic'. Each Dairy Product lot contains:
 * an id, a name , an expiration date and the amount of
 * units in the Lot. Also, it has some abstract methods
 * that each finish Product implements.
 */
public abstract class DairyProductLot {
    private String id;
    private String name;
    private Date expirationDate;
    private double calories;
    private Integer units;

    /**
     *
     * @param id
     * @param name name of the product
     * @param expirationDate Date when it will be expirate
     * @param units units that compose the lot.
     */
    public DairyProductLot(String id, String name, Date expirationDate, Integer units) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.units = units;
        this.calories = 0.0;
    }

    public abstract void calcCalories();
    public abstract String show();

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

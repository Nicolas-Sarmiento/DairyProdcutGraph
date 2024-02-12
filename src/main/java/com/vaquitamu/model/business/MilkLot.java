package com.vaquitamu.model.business;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class try to model a Milk Lot
 * To this belongs some real properties and
 * the actions performed in this are only examples
 * are not real processes. So the Milk Lot
 * contains an id to idintificate, a name of the product,
 * the units that compose the lot, amount of fats that the mik contains
 * and the content by unit.
 */
public class MilkLot extends DairyProductLot {

    private double fats;
    private double content;

    /**
     * This instances a new Milk Lot
     * @param id identifieer
     * @param name name of the product
     * @param expirationDate date whe it will be expirated
     * @param units units that compose the lot
     * @param fats amout of fats in the milk
     * @param content content in each unit measured in Liters
     */
    public MilkLot(String id, String name, Date expirationDate, Integer units, double fats, double content) {
        super(id, name, expirationDate, units);
        this.fats = fats;
        this.content = content;
        this.calcCalories();
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getContent() {
        return content;
    }

    public void setContent(double content) {
        this.content = content;
    }

    @Override
    public void calcCalories() {
        this.setCalories(this.fats * 0.2);
    }

    @Override
    public String show() {
        DecimalFormat numFormat = new DecimalFormat("#.##");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "Lote de Leche{" +
                "id ='" + this.getId() + '\'' +
                ", nombre lote ='" + this.getName() + '\'' +
                ", fecha caducidad=" + dateFormat.format(this.getExpirationDate())+
                ", calorias=" + numFormat.format(this.getCalories()) + " cal'"+
                ", unidades=" + this.getUnits() +
                ", grasas = '" + numFormat.format(this.getFats()) + " gr'"+
                ", contenido = '"+ numFormat.format(this.getContent()) + " L'"+
                '}';
    }
}

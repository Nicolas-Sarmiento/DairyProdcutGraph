package com.vaquitamu.model.business;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class try to model a Yogourt Lot
 * To this belongs some real properties and
 * the actions performed in this are only examples
 * are not real processes. So the Yogourt Lot
 * contains an id to idintificate, a name of the product,
 * the units that compose the lot, amount of sugar and the
 * content by unit.
 */

public class YogourtLot extends DairyProductLot {

    private double sugar;
    private double content;

    /**
     * Instances a Yogourt Lot
     * @param id id
     * @param name name of product
     * @param expirationDate Date when it expires
     * @param units units that compose the lot
     * @param sugar amount of sugar
     * @param content content in ml
     */
    public YogourtLot(String id, String name, Date expirationDate, Integer units, double sugar, double content) {
        super(id, name, expirationDate, units);
        this.sugar = sugar;
        this.content = content;
        this.calcCalories();
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getContent() {
        return content;
    }

    public void setContent(double content) {
        this.content = content;
    }

    @Override
    public void calcCalories() {
        this.setCalories(this.sugar * 0.5);
    }

    @Override
    public String show() {
        DecimalFormat numFormat = new DecimalFormat("#.##");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "Lote de Yogourt{" +
                "id ='" + this.getId() + '\'' +
                ", nombre lote ='" + this.getName() + '\'' +
                ", fecha caducidad=" + dateFormat.format(this.getExpirationDate()) +
                ", calorias=" + numFormat.format(this.getCalories()) + " cal'"+
                ", unidades=" + this.getUnits() +
                ", azucares = '" + numFormat.format(this.getSugar()) + " gr'"+
                ", contenido = '"+ numFormat.format(this.getContent()) + " ml'"+
                '}';
    }
}

package com.vaquitamu.model.business;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class try to model a Cheese Lot
 * To this belongs some real properties and
 * the actions performed in this are only examples
 * are not real processes. So the Cheese Lot
 * contains an id to idintificate, a name of the product,
 * the units that compose the lot, amount of proteins
 * and the weight.
 */
public class CheeseLot extends DairyProductLot {

    private Double weight;
    private Double proteins;

    /**
     * Instances a CheeseLot
     * @param id identifieer
     * @param name name of the product
     * @param expirationDate date whe it will be expirated
     * @param units units that compose the lot
     * @param proteins amout of proteins in each cheese.
     * @param weight weight of each cheese
     */
    public CheeseLot(String id, String name, Date expirationDate, Integer units, Double proteins, Double weight) {
        super(id, name, expirationDate, units);
        this.weight = weight;
        this.proteins = proteins;
        this.calcCalories();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    @Override
    public void calcCalories() {
        this.setCalories(this.proteins * 0.125);
    }

    @Override
    public String show() {
        DecimalFormat numFormat = new DecimalFormat("#.##");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "Lote de Quesos{" +
                "id ='" + this.getId() + '\'' +
                ", nombre lote ='" + this.getName() + '\'' +
                ", fecha caducidad=" + dateFormat.format(this.getExpirationDate()) +
                ", calorias=" + numFormat.format(this.getCalories()) + " cal'" +
                ", unidades=" + this.getUnits() +
                ", proteinas = '" + numFormat.format(this.getProteins()) + " gr'"+
                ", peso = '"+ numFormat.format(this.getProteins()) + " kg'"+
                '}';
    }
}

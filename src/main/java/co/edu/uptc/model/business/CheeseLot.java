package co.edu.uptc.model.business;

import java.util.Date;

public class CheeseLot extends DairyProduct {

    public CheeseLot(String id, String name, Date expirationDate, Integer units) {
        super(id, name, expirationDate, units);
    }

    @Override
    public void calcCalories() {

    }
}

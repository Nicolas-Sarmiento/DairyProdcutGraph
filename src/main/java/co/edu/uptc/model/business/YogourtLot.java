package co.edu.uptc.model.business;

import java.util.Date;

public class YogourtLot extends DairyProduct {
    public YogourtLot(String id, String name, Date expirationDate) {
        super(id, name, expirationDate);
    }

    @Override
    public void calcCalories() {

    }
}

package co.edu.uptc.model.business;

import java.util.Date;

public class MilkLot extends DairyProduct{
    public MilkLot(String id, String name, Date expirationDate) {
        super(id, name, expirationDate);
    }

    @Override
    public void calcCalories() {

    }
}

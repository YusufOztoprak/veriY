package com.example.veriy;

public class Shampoo extends Personal_Care {
    //private boolean paraben;
    private String hairType;
    private double volume;

    // Constructor


    public Shampoo(int id, String name, int price, int amount, int expiration_date, String brand, String userInstructions, String hairType, Double volume) {
        super(id, name, price, amount, expiration_date, brand, userInstructions);
        // this.paraben = paraben;
        this.hairType = hairType;
        this.volume = volume;
    }

    // Getters and Setters
    /*public boolean isParaben() {
        return paraben;
    }

    public void setParaben(boolean paraben) {
        this.paraben = paraben;
    }*/

    public String getHairType() {
        return hairType;
    }

    public void setHairType(String hairType) {
        this.hairType = hairType;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}

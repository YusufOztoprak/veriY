package com.example.veriy;

public class Personal_Care extends Product {
    private int expiration_date;
    private String brand;
    private String userInstructions;

    public Personal_Care(int id, String name, int price, int amount, int expiration_date, String brand, String userInstructions) {
        super(id, name, price);
        this.expiration_date = expiration_date;
        this.brand = brand;
        //this.ingredients = new ArrayList<>();
        this.userInstructions = userInstructions;
    }


    public int getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(int expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUserInstructions() {
        return userInstructions;
    }

    public void setUserInstructions(String userInstructions) {
        this.userInstructions = userInstructions;
    }
}

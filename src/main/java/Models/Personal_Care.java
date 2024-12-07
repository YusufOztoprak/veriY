package Models;

public class Personal_Care extends Product {
    private int expiration_date;
    private String brand;
    private String userInstructions;

    // Constructor for Personal_Care
    public Personal_Care(String id, String name, int price, int amount, int expiration_date, String brand, String userInstructions) {
        super(id, name, price, amount);  // Call the constructor of the Product class
        this.expiration_date = expiration_date;
        this.brand = brand;
        this.userInstructions = userInstructions;
    }

    // Method to display product details (overridden)
    @Override
    public void get_info() {
        super.get_info();  // Call the get_info() method from Product
        System.out.println("Expiration Date: " + expiration_date);
        System.out.println("Brand: " + brand);
        System.out.println("User Instructions: " + userInstructions);
    }

    // Getters and Setters for the new properties
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

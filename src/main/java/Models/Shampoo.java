package Models;

public class Shampoo extends Personal_Care {
    private String hairType;
    private double volume;

    // Constructor for Shampoo
    public Shampoo(String id, String name, int price, int amount, int expirationDate, String userInstructions, String hairType, double volume) {
        super(id, name, price, amount, expirationDate, userInstructions);  // Call the constructor of Personal_Care
        this.hairType = hairType;
        this.volume = volume;
    }

    // Method to display shampoo-specific information
    @Override
    public void get_info() {
        super.get_info();  // Call the get_info() method from Personal_Care
        System.out.println("Hair Type: " + hairType);
        System.out.println("Volume: " + volume + " ml");
    }

    // Getters and Setters for Shampoo-specific properties
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

package Models;

public class Product {
    private String id;
    private String name;
    private int price;
    private int amount;

    // Constructor for Product
    public Product(String id, String name, int price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    // Method to display product details
    public void get_info() {
        System.out.println("Ürün ID: " + id);
        System.out.println("Ürün Adı: " + name);
        System.out.println("Ürün Fiyatı: " + price);
        System.out.println("Ürün Miktarı: " + amount);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

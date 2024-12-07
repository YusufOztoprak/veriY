package Models;

public class Clothing extends Product {
    public String size;
    public String color;
    public String cloth;
    public String gender;

    public Clothing(String id,String name, int price, int amount, String size, String color, String cloth, String gender) {
        super(id,name, price, amount);
        this.size = size;
        this.color = color;
        this.cloth = cloth;
        this.gender = gender;
    }

    @Override
    public void get_info() {
        super.get_info();
        System.out.println("ürün bedeni:" + size + "\nürün rengi:" + color + "\nürünün kumaş tipi:" + cloth + "\nürün cinsiyet tipi:" + gender);
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getCloth() {
        return cloth;
    }
    public void setCloth(String cloth) {
        this.cloth = cloth;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

}
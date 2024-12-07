package Models;

public class Parfume extends Personal_Care {
    private double volume;
    private String genderTarget;
    private double alcoholContent;

    public Parfume(String id, String name, int price, int amount, int expiration_date, String brand, String userInstructions, Double volume, String genderTarget, Double alcoholContent) {
        super(id, name, price, amount, expiration_date, brand, userInstructions);
        this.volume = volume;
        this.genderTarget = genderTarget;
        this.alcoholContent = alcoholContent;
    }

    @Override
    public void get_info() {
        super.get_info();
        System.out.println("ürün hacmi:" + volume + "ürün hedefi" + genderTarget + "ürün alkol içeriyor mu:" + alcoholContent);
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getGenderTarget() {
        return genderTarget;
    }

    public void setGenderTarget(String genderTarget) {
        this.genderTarget = genderTarget;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }
}
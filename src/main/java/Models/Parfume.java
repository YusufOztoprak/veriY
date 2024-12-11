package Models;

public class Parfume extends Personal_Care {
    private double volume;
    private String genderTarget;

    public Parfume(String id, String name, int price, int amount, int expiration_date, String brand, String userInstructions, Double volume, String genderTarget) {
        super(id, name, price, amount, expiration_date, brand, userInstructions);
        this.volume = volume;
        this.genderTarget = genderTarget;
    }

    @Override
    public void get_info() {
        super.get_info();
        System.out.println("ürün hacmi:" + volume + "ürün hedefi" + genderTarget);
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

}
package Models;

public class PC extends Technology{

    private int ekranboyutu;

    public PC(String id, String name, int price, int amount, int ram, int storage, String Cpu, int warranty, String brand, int ekranboyutu) {
        super(id, name, price, amount,ram, storage, Cpu, warranty);
        this.ekranboyutu = ekranboyutu;

    }

    @Override
    public void get_info() {
        super.get_info();
        System.out.println("bilgisyarın ekran boyutu:" + ekranboyutu );
    }

    public int getEkranboyutu() {
        return  ekranboyutu;
    }

    public void setEkranboyutu(int ekranboyutu) {
        this.ekranboyutu = ekranboyutu;
    }
}
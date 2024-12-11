package Models;

public class Phone extends Technology{
    private boolean fiveGsupport;
    private int numberofCameras;
    private  boolean fastCharging;

    public Phone(String id,String name, int price, int amount, int ram, int storage, String Cpu, int warranty, boolean fiveGsupport, int numberofCameras, boolean fastCharging) {
        super(id,name, price, amount, ram, storage, Cpu, warranty);
        this.numberofCameras = numberofCameras;
        this.fiveGsupport = fiveGsupport;
        this.fastCharging = fastCharging;
    }

    @Override
    public void get_info() {
        super.get_info();
        System.out.println("ürün 5g destekliiyor mu::" + fiveGsupport + "\nürünün kamera megapikseli:" + numberofCameras + "\nürün hızlı şarj destekliyor mu:" + fastCharging );

    }

    void setFiveGsupport(boolean fiveGsupport){
        this.fiveGsupport = fiveGsupport;
    }
    void setFastCharging(boolean fastCharging){
        this.fastCharging = fastCharging;
    }
    void setNumberofCameras(int numberofCameras){
        this.numberofCameras = numberofCameras;
    }

    public boolean isFiveGsupport() {
        return fiveGsupport;
    }

    public int getNumberofCameras() {
        return numberofCameras;
    }

    public boolean isFastCharging() {
        return fastCharging;
    }
}
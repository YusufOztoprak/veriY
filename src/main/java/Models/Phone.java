package Models;

public class Phone extends Technology{
    private int numberofCameras;
    private boolean fiveGsupport;

    public Phone(String id,String name, int price, int amount, int ram, int storage, String Cpu, int warranty, int numberofCameras, boolean fiveGsupport) {
        super(id,name, price, amount, ram, storage, Cpu, warranty);
        this.numberofCameras = numberofCameras;
        this.fiveGsupport = fiveGsupport;
    }

    @Override
    public void get_info() {
        super.get_info();
        System.out.println("ürün 5g destekliiyor mu::" + fiveGsupport + "\nürünün kamera megapikseli:" + numberofCameras );

    }

    void setFiveGsupport(boolean fiveGsupport){
        this.fiveGsupport = fiveGsupport;
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

}
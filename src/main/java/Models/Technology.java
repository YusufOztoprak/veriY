package Models;

import java.util.ArrayList;

public class Technology extends Product {
    //public ArrayList<Technology> tecnologylist = new ArrayList<Technology>();

    private int ram;
    private  int storage;
    private String Cpu;
    private  int warranty;

    public Technology(String id, String name, int price, int amount, int ram, int storage, String Cpu, int warranty) {
        super(id,name,price,amount);
        this.ram = ram;
        this.storage = storage;
        this.Cpu = Cpu;
        this.warranty = warranty;

    }

    @Override
    public void get_info() {
        super.get_info();
        System.out.println("ürünün ram kapasitesi:" + ram + "\nürünün işlemcisi:" + Cpu + "\nürünün garanti süresi:" + warranty );
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getCpu() {
        return Cpu;
    }

    public void setCpu(String cpu) {
        Cpu = cpu;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

}
//public void setBrand(String brand) {
//  this.brand = brand;
//}*/

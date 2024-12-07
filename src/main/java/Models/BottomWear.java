package Models;

public class BottomWear extends Clothing {
    //private String fit;
    private boolean hasPockets;

    public BottomWear(String id,String name, int price, int amount, String size, String color, String cloth, String gender, boolean hasPockets) {
        super(id,name, price, amount, size, color, cloth, gender);
        //this.fit = fit;
        this.hasPockets = hasPockets;
    }

    /*public BottomWear(String name, int price, int amount, String size, String color, String cloth, String gender) {
        super(name, price, amount, size, color, cloth, gender);
    }*/

    @Override
    public void get_info() {
        super.get_info();
        System.out.println("Cepli: " + (hasPockets ? "Evet" : "Hayır"));
    }

    /*public String getFit() {
        return fit;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }*/

    public boolean hasPockets() {
        return hasPockets;
    }

    public void setHasPockets(boolean hasPockets) {
        this.hasPockets = hasPockets;
    }
}
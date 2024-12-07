package Models;

public class TopWear extends Clothing {
    private String sleeveType;
    private String neckType;

    public TopWear(String id, String name, int price, int stock_amount, String size, String color, String cloth, String gender, String sleeveType, String neckType) {
        super(id,name, price, stock_amount, size, color, cloth, gender);
        this.sleeveType = sleeveType;
        this.neckType = neckType;
    }

    @Override
    public void get_info() {
        super.get_info();
        System.out.println("Kol Tipi: " + sleeveType + "\nYaka Tipi: " + neckType);
    }

    public String getSleeveType() {
        return sleeveType;
    }

    public void setSleeveType(String sleeveType) {
        this.sleeveType = sleeveType;
    }

    public String getNeckType() {
        return neckType;
    }

    public void setNeckType(String neckType) {
        this.neckType = neckType;
    }
}
package BookInventory;

public class HardcoverBook extends Book {

    String coverMaterial;

    public HardcoverBook(String title, String author, String coverMaterial, int cost) {
        super(title, author, Medium.Hardcover, cost);
        this.coverMaterial=coverMaterial;
    }

    @Override
    public boolean isForSale() {
        return true;
    }
    public String toString(){
        return  super.toString()+" "+this.coverMaterial+".";
    }
    public String getMedium(){
        return Medium.Hardcover.toString()+" "+this.coverMaterial;
    }

}

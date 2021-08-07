package BookInventory;

public class ElectronicBook extends Book {

    private String theURL;
    public ElectronicBook(String title, String author, String theURL, int cost) {
        super(title, author, Medium.Electronic, cost);
        this.theURL=theURL;
    }

    @Override
    public boolean isForSale() {
        return false;
    }
    public String toString(){
        return  super.toString()+" from "+this.theURL;
    }
    public String getMedium(){
        return Medium.Electronic.toString()+" from "+this.theURL;
    }
}

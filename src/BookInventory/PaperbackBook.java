package BookInventory;

public class PaperbackBook extends Book {
    public PaperbackBook(String title, String author, int cost) {
        super(title, author, Medium.Paperback, cost);
    }

    @Override
    public boolean isForSale() {
        return true;
    }
    public String toString(){
        return super.toString()+".";
    }

}

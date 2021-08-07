package BookInventory;

public class AudioBook extends Book {

    private int numDisc;

    public AudioBook(String title, String author, int numDisc, int cost) {
        super(title, author, Medium.Audio, cost);
        this.numDisc=numDisc;
    }

    @Override
    public boolean isForSale() {
        return false;
    }
    public String toString(){
        return super.toString()+": "+this.numDisc+" discs.";
    }
    public String getMedium(){
        return Medium.Audio.toString()+": "+numDisc;
    }
}

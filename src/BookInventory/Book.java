package BookInventory;

public abstract class Book {
    public abstract boolean isForSale();
    private String title;
    private String author;
    private Medium medium;
    private int cost;

    public Book(String title,String author, Medium medium,int cost){
        this.author=author;
        this.title=title;
        this.cost=cost;
        this.medium=medium;

    }

    public String toString(){
        return "\""+title+"\".\n\t\t"+author+".\n\t\t"+medium;
    }
    public String  getMedium(){
        return this.medium.toString();
    }
    public String getTitle(){
        return "\""+this.title+"\"";
    }
    public String getAuthor(){
        return this.author;
    }
    public double getCost(){
        return (double)this.cost/100;
    }
}

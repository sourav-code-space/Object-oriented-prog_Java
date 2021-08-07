package BookInventory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {
    private String name;
    private List<Book> inventory=new ArrayList<>();
    private Scanner in;
    private double rentalPrice;
    private static final char TITLE_SEARCH='t';
    private static final char AUTHOR_SEARCH='a';
    private static final char MEDIA_SEARCH='m';
    private String CODE;
    char[] ccc;
    public Store(){
        rentalPrice=3.95;
        name="Reader's Choice";
    }
    public double getMarkUp(){
        return 2;
    }
    public void addBook(Book book){
        inventory.add(book);
    }
    public void fillInventory(String filename) throws FileNotFoundException {
        File file=new File(filename);
        in= new Scanner(new FileReader(file));
        String line;
        Book aBook;
        while (in.hasNextLine()){
            line=in.nextLine();
            aBook=produceBook(line);
            addBook(aBook);
        }
        in.close();
    }
    private List<Book> listMatching(String partText, char code){
        List<Book> matchedBooks=new ArrayList<>();
        if(code==TITLE_SEARCH){
            for(Book book:inventory){
                if(book.getTitle().matches(".*"+partText+".*"))
                    matchedBooks.add(book);
            }
        }
        else if(code==AUTHOR_SEARCH){
            for(Book book:inventory){
                if(book.getAuthor().matches(".*"+partText+".*"))
                    matchedBooks.add(book);
            }
        }
        else if(code==MEDIA_SEARCH){
            for(Book book:inventory){
                if(book.getMedium().matches(".*"+partText+".*"))
                    matchedBooks.add(book);
            }
        }

        return matchedBooks;
    }
    private void offerPurchase(List<Book> book) throws IOException {
        String choiceStr="";
        Scanner scan= new Scanner(System.in);
        System.out.print("\n\nWhich would you like to buy(ENTER to quit): ");
        if(scan.hasNextLine()) {
            choiceStr = scan.nextLine();
        }

        if (choiceStr.trim().isEmpty()) {
            System.out.println("\nSearch again anytime!!");
            System.exit(0);
        } else {
            int choice = Integer.parseInt(choiceStr.trim());
            Book selectedBook = inventory.get(choice - 1);
            if (!selectedBook.isForSale()) {
                System.out.println("\nSorry." + selectedBook.getTitle() + "\n\t" +
                        "\t"+selectedBook.getMedium()+"is not for sale." +
                        "\n\t\tThe " +
                        "rental price" +
                        " is $" + rentalPrice + " per week.");
            } else {
                System.out.println("The price of " + selectedBook.getTitle() + " " +
                        "is $" + (((double)selectedBook.getCost()) * this.getMarkUp()));
            }
        }

    }
    void offerSearch() throws IOException {
        String input="";
        do{
            System.out.println("\n\nEnter one of these one-letter codes: t" +
                    "(title), a(author), m(medium),\n" +
                    "and a portion of the desired text. Or enter a blank line to quit.\n" +
                    "\t(Note: medium is paper, hard, audio, or electronic).");
            in= new Scanner(System.in);

            System.out.print("\nHow would you like to search? ");
            input=in.nextLine();
            if(input.trim().isEmpty()){
                in.close();
                System.out.println("\nSearch again anytime!!");
                System.exit(0);
            }
            else{
                String[] inputArray=new String[2];
                inputArray=input.split(" ");
                char code=inputArray[0].charAt(0);
                String partText=inputArray[1];
                List<Book> matchedBooks=listMatching(partText,code);
                int count=1;
                for(Book book:matchedBooks){
                    System.out.println(count+". "+book.getTitle()+"\n\t"+book.getMedium()+"\t$"+(book.getCost()*this.getMarkUp()));
                    count++;
                }
                offerPurchase(matchedBooks);
            }
        }while (true);



    }
    void printInventory(){
        int count=1;
        System.out.println("The inventory of "+name);
        for(Book book:inventory){
            System.out.println(count+". "+book.toString());
            count++;
        }
    }

    Book produceBook(String line){
        String[] arr= new String[4];
        String title;
        String author;
        boolean isPaperback=false;
        int cost;
        arr=line.split("_");
        if(arr.length<4)
            isPaperback=true;
        title=arr[0];
        author=arr[1];
        cost=Integer.parseInt(arr[2]);
        if(!isPaperback){
            if(arr[3].startsWith("http://")){
                return new ElectronicBook(title,author,arr[3],cost);
            }
            else if(Character.isDigit(arr[3].charAt(0))){
                return new AudioBook(title,author,
                        Integer.parseInt(arr[3]),cost);
            }
            else {
                return new HardcoverBook(title,author,arr[3],cost);
            }
        }
        else{
            return new PaperbackBook(title,author,cost);
        }
    }

    String selectField(Book bk, char code){
        return null;
    }

    public static void main(String[] args) throws IOException {
        Store aStore= new Store();
        if (args.length<1){
            System.out.println("File name not found!!!");
            System.exit(0);
        }
        String filename=args[0];
        aStore.fillInventory(filename);
        aStore.printInventory();
        aStore.offerSearch();
    }
}

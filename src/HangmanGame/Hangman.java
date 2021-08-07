/*
 *
 * Hangman.java
 *
 * Version : 1.0		Date :- 01/28/2020
 *
 * Revision : $Log$
 *
 */
/**
 * This class acts as the driver class which takes the level of difficulty and
 * initializes the respective constructors to load the files and num of tries.
 *
 */
package HangmanGame;
import java.util.Scanner;

public class Hangman {
    GameBoard gameBoard;
    public Hangman(LevelMode mode){
        gameBoard = new GameBoard(mode);
    }
    /**
     * This method runs the game loop until the condition for game over is
     * satisfied.
     *
     * @param
     * @return
     */
    void play(){
        Scanner sc =null;
        do{
            if(!gameBoard.gameOver()){
                System.out.println(gameBoard.toString());
                System.out.println("Enter a letter: ");
                sc = new Scanner(System.in);
            }
        }while(enterLetter(sc));
    }

    /**
     * This method takes the input(character) inserted by the user and checks
     * its validity.g
     *
     * @param in
     * @return true if entered letter is a valid character else false.
     */
    boolean enterLetter(Scanner in){
        char c = in.next().charAt(0);
        if(c=='0'){
            return false;
        }
        else if(Character.isDigit(c)){
            System.out.println("Invalid input!");
            gameBoard.toString();
        }
        gameBoard.enterLetter(c);
        return true;
    }
    public static void main(String[] args) {
        if(args.length<1){
            System.out.println("Usage: java hangman mode(easy-e medium-m hard-h)");
            System.exit(0);
        }
        LevelMode mode;
        Hangman h=null;
        if(args[0].equals("e")){
            h = new Hangman(LevelMode.EASY);
        }
        else if(args[0].equals("m")){
            h = new Hangman(LevelMode.MEDIUM);
        }
        else {
            h = new Hangman(LevelMode.HARD);
        }
        System.out.println("Welcome to the Hangman Game!!");
        h.play();
    }
}

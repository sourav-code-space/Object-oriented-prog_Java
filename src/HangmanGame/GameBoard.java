/*
 *
 * GameBoard.java
 *
 * Version : 1.0		Date :- 01/28/2020
 *
 * Revision : $Log$
 *
 */
/**
 * This class is responsible for deciding the game. It takes the letters
 * inserted by the user and interactively produces the output string.
 *
 */
package HangmanGame;
import java.util.HashSet;

public class GameBoard {
    int maxNumTries = 0;
    WordReader wordReader =null;
    private String hiddenWord="";
    char[] maskWord=null;
    HashSet<Character> missedLetters=new HashSet<>();
    public GameBoard(LevelMode mode){
        maxNumTries = mode.getMaxNumTries();
        wordReader = new WordReader(mode.getDictFile());
        hiddenWord=wordReader.pickHiddenWord().toLowerCase();
        maskWord=hiddenWord.toCharArray();
        for(int i=0;i<hiddenWord.length();i++){
            maskWord[i]='*';
        }
    }
    /**
     * This method takes a valid character, checks if the character matches
     * with the hidden word or not. If matches it reveals the character in
     * the masked word else reduces the remaining number of tries.
     *
     * @param guessesLetter
     * @return
     */
    void enterLetter(char guessesLetter){
        if(hiddenWord.indexOf(guessesLetter)==-1){
            missedLetters.add(guessesLetter);
            maxNumTries-=1;
        }else {
            for(int i=0;i<hiddenWord.length();i++){
                if(hiddenWord.charAt(i)==guessesLetter){
                    maskWord[i]=guessesLetter;
                }
            }
        }
    }
    /**
     * This method decides whether the game is over or yet to continue.
     *
     * @param
     * @return maxNumTries
     */
    boolean gameOver(){
        if (maxNumTries==0) {
            System.out.println("Game over! The secret word was:" +hiddenWord);
            System.exit(0);
        }
        else if(hiddenWord.equalsIgnoreCase(new String(maskWord))){
            System.out.println(toString());
            System.out.println("You have guessed the secret word!");
            System.exit(0);
        }
        return false;
    }

    /**
     * This method returns the string containing all the necessary
     * information interactively at any given point in the game.
     *
     * @param
     * @return maxNumTries
     */
    public String toString(){

        return "Word: "+new String(maskWord)+"\n"+"Misses: "+missedLetters+
                "\n"+
                "Num. of " +
                "remaining tries: "+maxNumTries;

    }
}

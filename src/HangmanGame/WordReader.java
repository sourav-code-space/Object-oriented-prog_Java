/*
 *
 * WordReader.java
 *
 *
 * Version : 1.0		Date :- 01/28/2020
 *
 *
 * Revision : $Log$
 *
 */
/**
 * This class reads the file and picks a random word.
 *
 */
package HangmanGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordReader {
    private List<String> dict = new ArrayList<String>();
    public WordReader(String filename){
        readFile(filename);
    }

    /**
     * This method reads the file.
     *
     * @param filename
     * @return
     */
    private void readFile(String filename){
        ClassLoader classLoader = this.getClass().getClassLoader();
        Scanner sc=
                new Scanner(classLoader.getResourceAsStream(filename));
        while (sc.hasNext()){
            dict.add(sc.nextLine());
        }
    }

    /**
     * This method chooses the random word.
     *
     * @param
     * @return hiddenword
     */
    String pickHiddenWord(){
        Random random = new Random();
        int index=random.nextInt(dict.size());
        String hiddenWord=dict.get(index);
        return hiddenWord;
    }
}

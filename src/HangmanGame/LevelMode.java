/*
 *
 * LevelMode.java
 *
 * Version : 1.0		Date :- 01/28/2020
 *
 * Revision : $Log$
 *
 */
/**
 * This class initializes the max no of tries and file according to the
 * difficulty level.
 */
package HangmanGame;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

enum LevelMode {
     EASY,MEDIUM,HARD;
     private int maxNumTries;
     private String dictFile;
     private static final String CONFIG = "src/HangmanGame/resources/config" +
             ".properties";
     private static final String DEFAULLT_DICTIONARY_FILE= "src" +
             "/HangmanGame/resources" +
             "/dict" +
             ".txt";
     private static final int DEFAULT_MAX_NUM_TRIES=8;
     LevelMode(){
         init();
     }

    /**
     * This method initializes the filename and num of tries from the
     * property file, based on difficulty level.
     *
     * @param
     * @return
     */
     private void init(){
         try (InputStream input = new FileInputStream(CONFIG)) {
             Properties prop = new Properties();
             prop.load(input);
             if(this.name().equals("EASY")){
                 dictFile=prop.getProperty("easy.dict");
                 maxNumTries=Integer.parseInt(prop.getProperty("easy.tries"));
             }
             else if(this.name().equals("MEDIUM")){
                 dictFile=prop.getProperty("medium.dict");
                 maxNumTries=Integer.parseInt(prop.getProperty("medium.tries"));
             }
             else {
                 dictFile=prop.getProperty("hard.dict");
                 maxNumTries=Integer.parseInt(prop.getProperty("hard.tries"));
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
    /**
     * This method returns max no of tries.
     *
     * @param
     * @return maxNumTries
     */
     int getMaxNumTries() {
         return maxNumTries;
     }

    /**
     * This method returns the file name from which a random would be picked.
     *
     * @param
     * @return dictFile
     */
     String getDictFile(){
         return this.dictFile;
     }
}

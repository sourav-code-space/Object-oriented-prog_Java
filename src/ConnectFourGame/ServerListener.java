/*
 *
 * ServerListener.java
 *
 * Version : 1.0		Date :- 04/22/2020
 *
 * Revision : $Log$
 *
 */
package ConnectFourGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * The ServerListener(Client helper) is the thread class which keeps on
 * listening for Server
 * commands. Based on Server command it asks player to enter moves and prints
 * appropriate messages to the console.
 *
 */
public class ServerListener extends Thread implements ConnectFourProtocol {
    Socket s;
    ConnectFourClient client;
    DataInputStream din;
    DataOutputStream dout;
    boolean shouldRun=true;
    public ServerListener(Socket s, ConnectFourClient client){
        this.s=s;
        this.client=client;
        try{
            this.din= new DataInputStream(s.getInputStream());
            this.dout = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Sends data to the server.
     * @param
     * @return
     */
    public void sendDataToServer(String data){
        try {
            dout.writeUTF(data);
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Looks for Server command and takes appropriate actions based on the
     * commands.
     * @param
     * @return
     */
    public void run(){
        while(shouldRun){
            try {
                while (din.available()==0){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String text=din.readUTF();
                //System.out.println("Message from Server: "+text);
                if(text.equals(CONNECT)){
                    System.out.println("Connected!");
                }
                else if(text.equals(MAKE_MOVE)){
                    System.out.print("Your turn! Enter a column: ");
                }
                else if(text.startsWith(MOVE_MADE)){
                    int col=Integer.parseInt(text.split(
                            " ")[1]);
                    System.out.println("A move has been made in column "+col);
                    try {
                        client.connectFour.makeMove(col);
                        System.out.println(client.connectFour.toString());
                    } catch (ConnectFourException e) {
                        e.printStackTrace();
                    }
                }
                else if(text.equals(GAME_WON)){
                    System.out.println("You win! Yay!");
                    client.close();
                }
                else if(text.equals(GAME_LOST)){
                    System.out.println("You lost :(");
                    client.close();
                }
                else if(text.equals(GAME_TIED)){
                    System.out.println("Game tied..");
                    client.close();
                }
                else if(text.equals(ERROR)){
                    System.out.println("Oops! Some error has been caused! " +
                            "Exiting the game.");
                    client.close();
                }
                else continue;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

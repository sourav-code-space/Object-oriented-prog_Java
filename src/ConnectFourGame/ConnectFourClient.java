/*
 *
 * ConnectFourClient.java
 *
 * Version : 1.0		Date :- 04/22/2020
 *
 * Revision : $Log$
 *
 */
package ConnectFourGame;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * ConnectFourClient is the blue print of client that players will use to
 * connect to the server and play the game.
 * The client will prompt the player when it is his/her turn to make a move, and
 * inform the player of the game’s progress. The client will be responsible for
 * keeping track of the state of the game, making moves only when instructed to
 * do so by the server.
 *
 */
public class ConnectFourClient implements ConnectFourProtocol {
    ServerListener listener = null;
    Socket s;
    static ConnectFour connectFour=new ConnectFour();
    public ConnectFourClient(String ip, int port){
        try {
            s = new Socket("localhost", 8828);
            listener = new ServerListener(s, this);
            listener.start();
            listenForInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Keeps on listening for standard console input and once input available
     * it sends the input in appropriate format.
     * @param
     * @return
     */
    public void listenForInput(){
        Scanner sc= new Scanner(System.in);
        while(true){
            while (!sc.hasNextLine()){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //String data="Move "+sc.hasNextInt();
            String data=MOVE+" "+sc.nextLine();
            //System.out.println(data);
            listener.sendDataToServer(data);
        }
    }
    /**
     * Closes all the resources at the end of the game.
     * @param
     * @return
     */
    public void close(){
        try {
            listener.dout.close();
            listener.din.close();
            s.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String ip="localhost";
        int port=8828;
        if(args.length>=2){
            ip = args[0];
            port=Integer.parseInt(args[1]);
        }
        else {
            System.out.println("Usage: java <program file> <ip> <port>");
            System.exit(1);
        }
        new ConnectFourClient(ip,port);
    }
}

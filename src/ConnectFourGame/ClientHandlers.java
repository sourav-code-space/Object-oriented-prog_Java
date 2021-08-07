/*
 *
 * ClientHandlers.java
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
 * The ClientHandlers(Server helper) handles single client requests and send
 * proper response
 * to the respective client.
 */
public class ClientHandlers extends Thread {
    Socket s;
    static ConnectFourServer server;
    DataInputStream din;
    DataOutputStream dout;
    //boolean shouldRun=true;

    public ClientHandlers(String name, Socket s, ConnectFourServer server){
        super(name);
        this.s=s;
        this.server=server;
        try {
            din=new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sendCommandToClient("You are online!!");
    }
    /**
     * Closes all the resources at the end of the game.
     * @param
     * @return
     */
    public void close(){
        try {
            this.din.close();
            this.dout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Sends commands to a single client.
     * @param
     * @return
     */
    public void sendCommandToClient(String command){
        try {
            dout.writeUTF(command);
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Sends commands to all clients.
     * @param
     * @return
     */
    public static void sendCommandToAllClient(String command){
        for(int i=0;i<server.handlers.size();i++){
            ClientHandlers ch= server.handlers.get(i);
            ch.sendCommandToClient(command);
        }
    }
    /**
     * Keeps on listening from Clients if a move has been made.
     * @param
     * @return
     */
    public void run(){
        try {
            while(server.shouldRun){
                while(din.available()==0){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String text=din.readUTF();//reading move from client
                server.col = Integer.parseInt(text.split(
                        " ")[1]);
                server.dataAvailable=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

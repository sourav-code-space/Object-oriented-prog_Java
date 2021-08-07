/*
 *
 * ConnectFourServer.java
 *
 * Version : 1.0		Date :- 04/22/2020
 *
 * Revision : $Log$
 *
 */

package ConnectFourGame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The ConnectFourServer accepts two clients, monitors and validates
 * their moves in an alternate turn based manner. At last it decides winner,
 * loser or the game has tied.
 *
 * @author Sourav Khan(sk9675@rit.edu)
 * @author Kundan Patil(kp4677@rit.edu)
 */

public class ConnectFourServer implements ConnectFourProtocol {
    ServerSocket ss;
    static Socket s;
    static boolean shouldRun=true;
    int count=1;
    static int turn=0;
    ClientHandlers handler;
    static int col=0;
    static boolean dataAvailable=false;
    static ArrayList<ClientHandlers> handlers = new ArrayList<>();
    static ConnectFour connectFour = new ConnectFour();
    public static void main(String[] args) {
        int port=8828;
        if(args.length>=1){
            port=Integer.parseInt(args[0]);
        }
        else {
            System.out.println("Usage: java <program file> <port>");
            System.exit(1);
        }
        new ConnectFourServer(port);
        processMove();
    }
    public ConnectFourServer(int port){
        try {
            ss=new ServerSocket(port);
            while (count<3){
                s=ss.accept();
                handler = new ClientHandlers(("Client"+count),s
                        , this);
                handler.start();
                System.out.println("Client"+count++ +" is online!");
                handlers.add(handler);
            }
            ClientHandlers.sendCommandToAllClient(CONNECT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Driver method to process moves from the players until game is won/lost
     * or tied .
     * @param
     * @return
     */
    public static void processMove(){
        while (shouldRun){
            makeMove();
            while (!dataAvailable) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            updateBoard();
            toggleTurn();
            dataAvailable=false;
        }

    }
    /**
     * Ask the appropriate player to enter the next move.
     * @param
     * @return
     */
    public static void makeMove(){
        ClientHandlers handler=null;
        if(turn==0){
            handler=handlers.get(0);
        }
        else {
            handler=handlers.get(1);
        }
        handler.sendCommandToClient(MAKE_MOVE);
    }
    /**
     * Update the game board and check for win,lose or tie condition.
     * @param
     * @return
     */
    public static void updateBoard(){
        ClientHandlers handler;
        try {
            if (col >= connectFour.COLS) {
                ClientHandlers.sendCommandToAllClient(ERROR);
                closeResources();
                throw new OutOfBoundsException(OutOfBoundsException.COLUMN_PAST_EDGE);
            } else {
                connectFour.makeMove(col);
                ClientHandlers.sendCommandToAllClient(MOVE_MADE + " " + col);
                if (connectFour.hasWonGame()) {
                    handler = handlers.get(turn);
                    handler.sendCommandToClient(GAME_WON);
                    toggleTurn();
                    handler = handlers.get(turn);
                    handler.sendCommandToClient(GAME_LOST);
                    shouldRun = false;
                    closeResources();
                    System.out.println("Exiting..");
                    System.exit(0);
                } else if (connectFour.hasTiedGame()) {
                    ClientHandlers.sendCommandToAllClient(GAME_TIED);
                    shouldRun = false;
                    closeResources();
                    System.out.println("Exiting..");
                    System.exit(0);
                } else
                    return;
            }
        } catch (ConnectFourException e) {
            System.out.println(e.getMessage());
        }

    }
    /**
     * Closes all the resources at the end of the game.
     * @param
     * @return
     */
    public static void closeResources(){
        for(ClientHandlers handler:handlers){
            handler.close();
        }
        try {
            s.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Toggles the turn of players.
     * @param
     * @return
     */
    public static void toggleTurn(){
        if(turn==0)
            turn=1;
        else
            turn=0;
    }
}

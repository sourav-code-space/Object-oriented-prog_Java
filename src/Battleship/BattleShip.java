/*
 *
 * BattleShip.java
 *
 * Version : 1.0		Date :- 04/02/2020
 *
 * Revision : $Log$
 *
 */
package Battleship;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

import Battleship.Ship.Orientation;

/**
 * This class represent a Battleship class. It has access to a game board (Board
 * class) instance, which, importantly, contains or refers to all data related
 * to the current state of the game at any point in time. This capability is
 * realized by the Board having access to all of the ships and all of the
 * individual cells on its grid. For design convenience every cell is an object
 * as well, and each cell on which a ship is placed has a reference to that
 * ship.
 *
 * @author Sourav Khan(sk9675@rit.edu)
 *
 */

public class BattleShip {
	static String ALL_SHIP_Sunk = "All ships sunk!";
	static String BAD_ARG_COUNT = "Wrong number of arguments for command";
	static String BAD_CONFIG_File = "Malformed board text file";
	static String DIM_TOO_BIG = "Board dimensions too big to display.";
	static int MAX_DIM = 20;
	static String MISSING_SETUP_FILE = "No setup file specified.";
	static String Prompt = "> ";
	static String WHITESPACE = "\\s+";

	static Board board;
	static int height;
	static int width;

	/**
	 * This method check for argument provided by the user
	 * 
	 * @param input
	 * @param length
	 * @return true if matched else false
	 */
	public boolean checkArgCount(String[] input, int length) {
		if (input.length == length) {
			return true;
		}
		return false;
	}

	/**
	 * This method is the starting point of the game.
	 */
	public void play() {
		while (!board.allSunk()) {
			promptPlayer();
		}
		System.out.println("\n" + ALL_SHIP_Sunk);
	}

	/**
	 * This method prompts for the input from the user until and unless all
	 * battleship are sunk.
	 */
	public void promptPlayer() {
		System.out.print("\n" + Prompt);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line = br.readLine();
			String data[] = line.split(WHITESPACE);
			if (data[0].equals("h")) {
				if (checkArgCount(data, 3)) {
					hit(data);
				} else {
					System.out.println(BAD_ARG_COUNT);
				}
			} else if (data[0].equals("s")) {
				if (checkArgCount(data, 2)) {
					save(data);
				} else {
					System.out.println(BAD_ARG_COUNT);
				}
			} else if (data[0].equals("!")) {
				if (checkArgCount(data, 1)) {
					cheat(data);
				} else {
					System.out.println(BAD_ARG_COUNT);
				}
			} else if (data[0].equals("q")) {
				if (checkArgCount(data, 1)) {
					quit(data);
				} else {
					System.out.println(BAD_ARG_COUNT);
				}
			}
		} catch (IOException | CellPlayedException | OutOfBoundsException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	/**
	 * This method upon invocation parse the required information and check for any
	 * exception and then hit the cell
	 *
	 * @param input
	 * @throws CellPlayedException
	 * @throws OutOfBoundsException
	 */
	public void hit(String[] input) throws CellPlayedException, OutOfBoundsException {
		int row = Integer.parseInt(input[1]);
		int column = Integer.parseInt(input[2]);
		if (row >= board.height)
			throw new OutOfBoundsException(row, column, OutOfBoundsException.PAST_EDGE);
		if (column >= board.width)
			throw new OutOfBoundsException(row, column, OutOfBoundsException.PAST_EDGE);
		Cell cell = board.getCell(row, column);
		cell.hit();
		board.display();
	}

	/**
	 * This method is used to reveal all the un-sunk ship present in the game.
	 * 
	 * @param input
	 */
	public void cheat(String[] input) {
		int rowCount = 0;
		int colCount = 0;
		for (int i = 0; i < width; i++, colCount++) {
			System.out.print(" " + colCount);
		}
		System.out.println();
		for (Cell[] rows : board.cell) {
			System.out.print(rowCount);
			for (Cell element : rows) {

				if (element.cellValue == '_') {
					if (element.ship != null) {
						System.out.print(Cell.HIDDEN_SHIP_SECTION + " ");
					} else {
						System.out.print(element.displayChar() + " ");
					}
				} else {
					System.out.print(element.displayChar() + " ");
				}
			}
			System.out.println();
			rowCount++;
		}

	}

	/**
	 * This method adds the saving(pause the game) functionality to our battleship
	 * game.
	 * 
	 * @param input
	 */
	public void save(String[] input) {
		try {
			FileOutputStream fos = new FileOutputStream(input[1]);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(board);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method quits the game
	 * 
	 * @param input
	 */
	public void quit(String[] input) {
		System.exit(0);
	}

	/**
	 * Main method is responsible to read the .txt file or .bin file and set the
	 * game accordingly.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		BattleShip battleShipObject = new BattleShip();
		Ship ship = null;
		String fileName;
		boolean flag = battleShipObject.checkArgCount(args, 1);
		if (flag == false) {
			System.out.println("Usage : " + MISSING_SETUP_FILE);
		} else {
			fileName = args[0];
			boolean checkFileType = false;
			System.out.print("Checking if "+fileName+" is a saved game file... ");
			if (Pattern.matches(".*.bin", fileName)) {
				checkFileType = true;
				if(checkFileType) {
					System.out.println("yes");
				}
				try {
					FileInputStream fis = new FileInputStream(fileName);
					ObjectInputStream ois = new ObjectInputStream(fis);
					board = (Board) ois.readObject();
					board.display();
					battleShipObject.play();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (Pattern.matches(".*.txt", fileName)) {
				if(!checkFileType) {
					System.out.print("no;");
				}
				System.out.print("will read as a text setup file.\n");
				try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
					String line = "";
					while ((line = br.readLine()) != null) {
						String[] data = line.split(WHITESPACE);
						if (data.length == 2) {
							height = Integer.parseInt(data[0]);
							width = Integer.parseInt(data[1]);
							board = new Board(height, width);
						} else if (data.length == 4) {
							int uRow = Integer.parseInt(data[0]);
							int lCol = Integer.parseInt(data[1]);
							Ship.Orientation ort = Orientation.valueOf(data[2]);
							int length = Integer.parseInt(data[3]);
							ship = new Ship(board, uRow, lCol, ort, length);
							board.addShip(ship);
						}
					}
					board.display();
					battleShipObject.play();
				} catch (FileNotFoundException | OutOfBoundsException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OverlapException e) {
					System.out.println(e);
				}
			}
		}

	}
}
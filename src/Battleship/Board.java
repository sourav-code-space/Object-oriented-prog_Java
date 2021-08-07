/*
 *
 * Board.java
 *
 * Version : 1.0		Date :- 04/02/2020
 *
 * Revision : $Log$
 *
 */
package Battleship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Battleship.Ship.Orientation;

/**
 * The class to represent the grid of cells (squares). A collection of ships is
 * also kept so the Board can be asked if the game is over. The class is
 * Serializable so that its instance can be saved to a file in binary form using
 * an ObjectOutputStream and restored with an ObjectInputStream. Because the
 * object holds references to all other objects in the system, no other objects
 * need to be separately saved.
 * 
 * @author Sourav Khan(sk9675@rit.edu)
 *
 */
public class Board implements Serializable {
	public int height;
	public int width;
	public Cell[][] cell;
	List<Ship> shipList = new ArrayList<Ship>();

	/**
	 * Parameterized Constructor.Constructs a board.
	 * 
	 * @param height
	 * @param width
	 */
	public Board(int height, int width) {
		this.height = height;
		this.width = width;
		cell = new Cell[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cell[i][j] = new Cell(i, j);

			}
		}
	}

	/**
	 * used for error checking
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * used for error checking
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Fetch the Cell object at the given location.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public Cell getCell(int row, int column) {
		return this.cell[row][column];
	}

	/**
	 * Add a ship to the board. The only current reason that the board needs direct
	 * access to the ships is to poll them to see if they are all sunk and the game
	 * is over.
	 * 
	 * @param ship
	 * @throws OverlapException
	 */
	public void addShip(Ship ship) throws OverlapException {
		shipList.add(ship);
		Cell singleCell = null;

		if (ship.ort == Orientation.valueOf("HORIZONTAL")) {
			for (int startIndex = ship.lCol; startIndex <= ship.ort.cDelta; startIndex++) {
				singleCell = cell[ship.uRow][startIndex];
				if (singleCell.ship == null)
					singleCell.putShip(ship);
				else
					throw new OverlapException(ship.uRow, startIndex, OverlapException.OVERLAP);
			}
		} else if (ship.ort == Orientation.valueOf("VERTICAL")) {
			for (int startIndex = ship.uRow; startIndex <= ship.ort.rDelta; startIndex++) {
				singleCell = cell[startIndex][ship.lCol];
				if (singleCell.ship == null) {
					singleCell.putShip(ship);
				} else
					throw new OverlapException(startIndex, ship.lCol, OverlapException.OVERLAP);
			}
		}
	}

	/**
	 * Display the board in character form to the user. Cells' display characters
	 * are described in Cell. Output is double-spaced in both dimensions. The
	 * numbers of the columns appear above the first row, and the numbers of each
	 * row appears to the left of the row.
	 */
	public void display() {
		int rowCount = 0;
		int colCount = 0;
		for (int i = 0; i < width; i++, colCount++) {
			System.out.print(" " + colCount);
		}
		System.out.println();
		for (Cell[] rows : cell) {
			System.out.print(rowCount);
			for (Cell element : rows) {
				System.out.print(element.displayChar() + " ");
			}
			System.out.println();
			rowCount++;
		}
	}

	/**
	 * Is the game over?
	 * @return
	 */
	public boolean allSunk() {
		for (Ship ship : shipList) {
			if (!ship.isSunk()) {
				return false;
			}
		}
		return true;
	}
}
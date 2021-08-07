/*
 *
 * Ship.java
 *
 * Version : 1.0		Date :- 04/02/2020
 *
 * Revision : $Log$
 *
 */
package Battleship;

import java.io.Serializable;

/**
 * A single ship in a Battleship game
 */
public class Ship implements Serializable {
	public Board board;
	public int uRow;
	public int lCol;
	public Orientation ort;
	public int length;
	public int hitCount;

	public static final String SUNK_MESSAGE = "A battleship has been sunk!";

	/**
	 * Orientation is a property of a ship. The names of the enum values were chosen
	 * because they are descriptive and match the words put in the configuration
	 * files.
	 *
	 * @see Orientation#valueOf(String)
	 */
	public enum Orientation {
		HORIZONTAL(0, 1), VERTICAL(1, 0);

		/**
		 * Use it to loop through all of the cell locations a ship is on, based on the
		 * upper left end of the ship.
		 */
		public int rDelta, cDelta;

		/**
		 * Associate a direction vector with the orientation.
		 * 
		 * @param rDelta how much the row number changes in this direction
		 * @param cDelta how much the column number changes in this direction
		 */
		Orientation(int rDelta, int cDelta) {
			this.rDelta = rDelta;
			this.cDelta = cDelta;
		}
	}

	/**
	 * Initialize this new ship's state. Tell the Board object and each involved
	 * Cell object about the existence of this ship by trying to put the ship at
	 * each applicable Cell.
	 * 
	 * @param board  holds a collection of ships
	 * @param uRow   the uppermost row that the ship is on
	 * @param lCol   the leftmost column that the ship is on
	 * @param ort    the ship's orientation
	 * @param length how many cells the ship is on
	 * @throws OverlapException     if this ship would overlap another one that
	 *                              already exists
	 * @throws OutOfBoundsException if this ship would extend beyond the board
	 */
	public Ship(Board board, int uRow, int lCol, Orientation ort, int length) throws OutOfBoundsException {
		this.board = board;
		this.uRow = uRow;
		this.lCol = lCol;
		this.ort = ort;
		this.length = length;
		if (this.ort == Orientation.HORIZONTAL) {
			int calWidth=(length - 1) + lCol;
			if(calWidth<=board.width) {
				ort.cDelta = (length - 1) + lCol;
				ort.rDelta = 0;
			}else throw new OutOfBoundsException(uRow,calWidth,"Coordinates " +
					"are past board edge");
		} else {
			int calHeight=(length - 1) + uRow;
			if(calHeight<=board.height){
				ort.rDelta = calHeight;
				ort.cDelta = 0;
			}
			else
				throw new OutOfBoundsException(calHeight,lCol,"Coordinates are " +
						"past board edge");
		}

	}

	/**
	 * A Cell object has been hit and tells this ship that is sitting on it that the cell has been hit.
	 */
	public void hit() {
		this.hitCount++;
		if(isSunk()){
			Cell singleCell = null;
			if (this.ort == Orientation.HORIZONTAL) {
				for (int i = this.lCol; i < (this.lCol + this.length); i++) {
					singleCell = board.cell[this.uRow][i];
					singleCell.cellValue = Cell.SUNK_SHIP_SECTION;
				}
			}
			if (this.ort == Orientation.VERTICAL) {
				for (int i = this.uRow; i < (this.uRow + this.length); i++) {
					singleCell = board.cell[i][this.lCol];
					singleCell.cellValue = Cell.SUNK_SHIP_SECTION;
				}
			}
		}

	}

	/**
	 * Is this ship already sunk?
	 * @return
	 */
	public boolean isSunk() {
		if (this.hitCount == this.length) {

			return true;
		}
		else
			return false;
	}
}

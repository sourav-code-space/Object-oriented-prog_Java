/*
 *
 * Cell.java
 *
 * Version : 1.0		Date :- 04/02/2020
 *
 * Revision : $Log$
 *
 */
package Battleship;

import java.io.Serializable;

/**
 * A single spot on the Battleship game board.
 * A cell knows if there is a ship on it, and it remember
 * if it has been hit.
 */
public class Cell implements Serializable{
	/**
     * Character to display for a ship section that has not been
     * sunk, when revealing the hidden locations of ships
     */
	static char HIDDEN_SHIP_SECTION = 'S';
	/** Character to display for a ship that has been hit but not sunk */
	static char HIT_SHIP_SECTION = 	9744;
	/** Character to display for a water cell that has been hit */
	static char HIT_WATER = '.';
	/**
     * Character to display for a water cell that has not been hit.
     * This character is also used for an unhit ship segment.
     */
	static char PRISTINE_WATER = '_';
	/** Character to display for a ship that has been entirely sunk */
	static char SUNK_SHIP_SECTION = '*';
	
	public int row;
	public int column;
	public Ship ship;
	public char cellValue = PRISTINE_WATER;
	public boolean cellHit=false;
	
	/**
	 * Create a new cell.
	 * @param row
	 * @param column
	 */
	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Place a ship on this cell.
	 * @param ship
	 */
	public void putShip(Ship ship) {
		this.ship = ship;
	}

	/**
	 * Simulate hitting this water cell.
	 * @throws CellPlayedException
	 */
	public void hit() throws CellPlayedException {
		if(cellHit==true){
			throw new CellPlayedException(this.row,this.column,CellPlayedException.ALREADY_HIT);
		}
		else{
			this.cellHit = true;
			if(this.ship != null) {
				this.cellValue = HIT_SHIP_SECTION;
				this.ship.hit();
				boolean has_sunk=ship.isSunk();
				if(has_sunk)
					System.out.println(Ship.SUNK_MESSAGE);
			}
			else {
				this.cellValue = HIT_WATER;
			}
		}

		
	}
	/**
	 * Return a character representing the state of this Cell but without revealing unhit portions of ships
	 * @return
	 */
	public char displayHitStatus() {
		return this.cellValue;
	}
	
	/**
	 * Return a character representing the state of this Cell.
	 * @return
	 */
	public char displayChar() {
		return this.cellValue;
	}
}
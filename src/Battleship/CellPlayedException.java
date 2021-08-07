package Battleship;

import java.io.Serializable;

public class CellPlayedException extends BattleshipException implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String ALREADY_HIT = "This cell has already been hit";
	
	public CellPlayedException(int row, int column, String message) {
		super(row, column, message);
		// TODO Auto-generated constructor stub
	}
	
}

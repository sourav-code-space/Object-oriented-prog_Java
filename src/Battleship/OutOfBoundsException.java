package Battleship;

import java.io.Serializable;

public class OutOfBoundsException extends BattleshipException implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String PAST_EDGE = "Co\u00f6rdinates are past board edge";
	
	public OutOfBoundsException(int row, int column, String message) {
		super(row, column, message);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	
}


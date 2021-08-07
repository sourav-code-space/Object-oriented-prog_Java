package Battleship;

import java.io.Serializable;

public class OverlapException extends BattleshipException implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String OVERLAP = "Ships placed in overlapping positions";
	
	public OverlapException(int row, int column, String message) {
		super(row, column, message);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	
}


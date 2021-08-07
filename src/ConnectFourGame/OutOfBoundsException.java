package ConnectFourGame;

import java.io.Serializable;

public class OutOfBoundsException extends ConnectFourException implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String COLUMN_PAST_EDGE = "Input Column is past board edge";
	public static final String ROW_PAST_EDGE = "Column full no more entries in the same column";
	
	public OutOfBoundsException(String message) {
		
		super(message);
		// TODO Auto-generated constructor stub
	}
}
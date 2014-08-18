package main.java.server;

public class SudokuException extends Exception {
	private String message;
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public SudokuException(String message){
		this.message = message;
	}

}

package main.java.beans;
import java.util.ArrayList;


public class Cell {
	
	private int value;
	private boolean found;
	private ArrayList<Integer> guesses;
	private String color = "black";
	
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		setGuesses(null);
		setFound(true);
	}
	public boolean isFound() {
		return found;
	}
	public void setFound(boolean found) {
		this.found = found;
	}
	public ArrayList<Integer> getGuesses() {
		return guesses;
	}
	public void setGuesses(ArrayList<Integer> guesses) {
		this.guesses = guesses;
	}
	
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	public void copy(Cell cell) throws CloneNotSupportedException{
		this.clone();
	}

}

package main.java.beans;

import java.util.ArrayList;

public class Cell {

	private Group row;
	private Group column;
	private Group threeByThreeSquare;
	private int value;
	private boolean found;
	private ArrayList<Integer> guesses;
	private String color = "black";

	public Cell(Group group) {
		if (group instanceof Row) {
			this.setRow(group);
		} else if (group instanceof Column) {
			this.setColumn(group);
		} else if (group instanceof ThreeByThreeSquare) {
			this.setThreeByThreeSquare(group);
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		setGuesses(null);
		setFound((value == 0) ? false : true);
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		if (getValue() == 0 && found == false) {
			this.found = found;
		} else if (getValue() != 0 && found == true) {
			this.found = found;
		} else {
			System.out.println("trying to set cell found to " + found
					+ " but value is " + getValue());
		}
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
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	public Group getRow() {
		return row;
	}

	public void setRow(Group row) {
		this.row = row;
	}

	public Group getColumn() {
		return column;
	}

	public void setColumn(Group column) {
		this.column = column;
	}

	public Group getThreeByThreeSquare() {
		return threeByThreeSquare;
	}

	public void setThreeByThreeSquare(Group threeByThreeSquare) {
		this.threeByThreeSquare = threeByThreeSquare;
	}

	public void copy(Cell cell) throws CloneNotSupportedException {
		this.clone();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Cell value = ");
		sb.append(value).append(" ");
		sb.append("Cell color = ");
		sb.append(color).append(" ");
		sb.append("Cell guesses = ");
		sb.append(guesses).append(" ");
		return sb.toString();
	}

}

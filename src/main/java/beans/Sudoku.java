package main.java.beans;

import java.io.IOException;
import java.util.ArrayList;

import main.java.server.parser.Parser;

public class Sudoku implements Cloneable, Comparable<Sudoku> {
	
	private ArrayList<Row> rowArray= new ArrayList<Row>();
	private ArrayList<Column> columnArray= new ArrayList<Column>();
	private ArrayList<ThreeByThreeSquare> threeByThreeArray= new ArrayList<ThreeByThreeSquare>();
	private boolean solved=false;
	private int HowManyCellsLeft=81;
	private long puzzleId;
	private int puzzleLevel;
	private boolean sudokuHasChanged;
	
	public Sudoku(){
			for(int i=0;i<9;i++){
				rowArray.add( new Row(this,i)); 
			}	
			syncColumnsToRow();
			syncThreeByThreeSquaresToRow();
			registerAllObservers();
			System.out.println("created new sudoku");
	}


	private void registerAllObservers() {
		for (Row row : rowArray) {
			for (Cell cell : row.getGroup()) {
				cell.registerObservers();
			}
		}
		
	}


	//GETTERS & SETTERS
	public ArrayList<Row> getRowArray() {
		if ( rowArray == null){
			rowArray = new ArrayList<Row>(); 
		}
		return rowArray;
	}
	public void setRowArray(ArrayList<Row> rowArray) {
		this.rowArray = rowArray;
	}
	public ArrayList<Column> getColumnArray() {
		return columnArray;
	}
	public void setColumnArray(ArrayList<Column> columnArray) {
		this.columnArray = columnArray;
	}
	public ArrayList<ThreeByThreeSquare> getThreeByThreeArray() {
		return threeByThreeArray;
	}
	public void setThreeByThreeArray(ArrayList<ThreeByThreeSquare> threeByThreeArray) {
		this.threeByThreeArray = threeByThreeArray;
	}
	
	public boolean isSolved() {
		return solved;
	}


	public void setSolved(boolean solved) {
		this.solved = solved;
	}


	public int getHowManyCellsLeft() {
		return HowManyCellsLeft;
	}


	public void setHowManyCellsLeft(int howManyCellsLeft) {
		HowManyCellsLeft = howManyCellsLeft;
	}


	public long getPuzzleId() {
		return puzzleId;
	}


	public void setPuzzleId(long puzzleId) {
		this.puzzleId = puzzleId;
	}




	public int getPuzzleLevel() {
		return puzzleLevel;
	}


	public void setPuzzleLevel(int puzzleLevel) {
		this.puzzleLevel = puzzleLevel;
	}


	private void syncColumnsToRow(){
		columnArray.clear();
		for(int rowIndex=0;rowIndex<9;rowIndex++)
		{
			for(int columnIndex=0;columnIndex<9;columnIndex++)
			{
				
					if(columnArray.size()<columnIndex+1){
						columnArray.add( new Column(this,columnIndex));
//						System.out.println("created collumn for row : "+ row +", collumn: "+ collumn );
					}
					Cell cell=rowArray.get(rowIndex).getGroup().get(columnIndex);	
					columnArray.get(columnIndex).getGroup().set(rowIndex, cell);
					cell.setColumn(columnArray.get(columnIndex));
//					Cell leftCell=collumnArray.get(collumn).getGroup().get(row);
					
//					System.out.println(leftCell.equals(rightCell)+" , "+leftCell.toString()+" , "+rightCell.toString());
			}
		}
	}
	
	
	private void syncThreeByThreeSquaresToRow() {
		threeByThreeArray.clear();
		
		
		syncThreeByThreeSquaresToRowHelper(0, 3, 0, 3);
		syncThreeByThreeSquaresToRowHelper(0, 3, 3, 6);
		syncThreeByThreeSquaresToRowHelper(0, 3, 6, 9);
		syncThreeByThreeSquaresToRowHelper(3, 6, 0, 3);
		syncThreeByThreeSquaresToRowHelper(3, 6, 3, 6);
		syncThreeByThreeSquaresToRowHelper(3, 6, 6, 9);
		syncThreeByThreeSquaresToRowHelper(6, 9, 0, 3);
		syncThreeByThreeSquaresToRowHelper(6, 9, 3, 6);
		syncThreeByThreeSquaresToRowHelper(6, 9, 6, 9);
		
		
	}
	
	private void syncThreeByThreeSquaresToRowHelper(int rowStart,int rowEnd, int columnStart, int columnEnd){
		int threeByThreeIndex=0;
		int groupCount=0;
		for(int rowIndex=rowStart;rowIndex<rowEnd;rowIndex++)
		{
			
			for(int columnIndex=columnStart;columnIndex<columnEnd;columnIndex++,groupCount++)
			{
				threeByThreeIndex=calculateGroup(rowIndex, columnIndex);
					//alt taraf ok 
					if(threeByThreeArray.size()<threeByThreeIndex+1){
						threeByThreeArray.add( new ThreeByThreeSquare(this,threeByThreeIndex));
//						System.out.println("created ThreeByThreeSquare for group: "+group +", row : "+ row +", collumn: "+ collumn );
					}
					//alt taraf ok
					Cell cell=rowArray.get(rowIndex).getGroup().get(columnIndex);	
					threeByThreeArray.get(threeByThreeIndex).getGroup().set(groupCount, cell);
					cell.setThreeByThreeSquare(threeByThreeArray.get(threeByThreeIndex));
//					Cell leftCell=threeByThreeArray.get(group).getGroup().get(groupCount);
					
//					System.out.println("For threebythree "+leftCell.equals(rightCell)+" , "+leftCell.toString()+" , "+rightCell.toString());
			}
		}
		
	}
	
	private int calculateGroup(int row,int column){
		int group=0;
		if (row<3&&column<3)group=0;
		else if (row<3&&column<6) group=1;
		else if (row<3&&column<9) group=2;
		else if (row<6&&column<3) group=3;
		else if (row<6&&column<6) group=4;
		else if (row<6&&column<9) group=5;
		else if (row<9&&column<3) group=6;
		else if (row<9&&column<6) group=7;
		else if (row<9&&column<9) group=8;
		
		return group;
	}
	
	/**
	 * @return the sudokuHasChanged
	 */
	public boolean isSudokuHasChanged() {
		return sudokuHasChanged;
	}


	/**
	 * @param sudokuHasChanged the sudokuHasChanged to set
	 */
	public void setSudokuHasChanged(boolean sudokuHasChanged) {
		this.sudokuHasChanged = sudokuHasChanged;
	}


	public Sudoku copy(){
		Sudoku sudoku=new Sudoku();
		for (int i =0; i<9; i++ ) {
			Row row = sudoku.getRowArray().get(i);
			for (int j =0; j<9; j++) {
				Cell cell = row.getGroup().get(j);
				cell.setValue(this.getRowArray().get(i).getGroup().get(j).getValue());
			}
		}
		return sudoku;
	}
	
	


	
	public int compareTo(Sudoku o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		Sudoku testSudoku = Parser.parseWebSudoku();
		System.out.println(testSudoku.toString());
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell cell = getRowArray().get(i).getGroup().get(j);
				sb.append("Cell ").append(i).append(j).append(" : ");
				sb.append(cell.getValue()).append(" ");
				if (!cell.getColor().equals("black")) {
					sb.append("Guesses : ");
					sb.append(cell.getGuesses()).append(" ");
					sb.append("Color :  ");
					sb.append(cell.getColor()).append(" ");
				}
				sb.append("\n");
			}
		}
		return sb.toString();
	}


}

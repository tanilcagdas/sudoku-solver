package main.java.beans;

import java.util.ArrayList;

public class Sudoku implements Cloneable, Comparable<Sudoku> {
	
	ArrayList<Row> rowArray= new ArrayList<Row>();
	ArrayList<Collumn> collumnArray= new ArrayList<Collumn>();
	ArrayList<ThreeByThreeSquare> threeByThreeArray= new ArrayList<ThreeByThreeSquare>();
	boolean solved=false;
	int HowManyCellsLeft=81;
	
	public Sudoku(){
		
			for(int i=0;i<9;i++){
				rowArray.add( new Row()); 
			}	
		
			syncCollumnsToRow();
			syncThreeByThreeSquaresToRow();

	
	/*	for(int i=0;i<9;i++){
			//row arrayden al
			threeByThreeArray.add( new ThreeByThreeSquare()); 
		}*/

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
	public ArrayList<Collumn> getCollumnArray() {
		return collumnArray;
	}
	public void setCollumnArray(ArrayList<Collumn> collumnArray) {
		this.collumnArray = collumnArray;
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


	private void syncCollumnsToRow(){
		collumnArray.clear();
		for(int row=0;row<9;row++)
		{
			for(int collumn=0;collumn<9;collumn++)
			{
				
					if(collumnArray.size()<collumn+1){
						collumnArray.add( new Collumn());
						System.out.println("created collumn for row : "+ row +", collumn: "+ collumn );
					}
					Cell rightCell=rowArray.get(row).getGroup().get(collumn);	
					collumnArray.get(collumn).getGroup().set(row, rightCell);
					Cell leftCell=collumnArray.get(collumn).getGroup().get(row);
					
					System.out.println(leftCell.equals(rightCell)+" , "+leftCell.toString()+" , "+rightCell.toString());
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
	
	private void syncThreeByThreeSquaresToRowHelper(int rowStart,int rowEnd, int collumnStart, int collumnEnd){
		int group=0;
		int groupCount=0;
		for(int row=rowStart;row<rowEnd;row++)
		{
			
			for(int collumn=collumnStart;collumn<collumnEnd;collumn++,groupCount++)
			{
				group=calculateGroup(row, collumn);
					//alt taraf ok 
					if(threeByThreeArray.size()<group+1){
						threeByThreeArray.add( new ThreeByThreeSquare());
						System.out.println("created ThreeByThreeSquare for group: "+group +", row : "+ row +", collumn: "+ collumn );
					}
					//alt taraf ok
					Cell rightCell=rowArray.get(row).getGroup().get(collumn);	
					threeByThreeArray.get(group).getGroup().set(groupCount, rightCell);
					Cell leftCell=threeByThreeArray.get(group).getGroup().get(groupCount);
					
					System.out.println("For threebythree "+leftCell.equals(rightCell)+" , "+leftCell.toString()+" , "+rightCell.toString());
			}
		}
		
	}
	
	private int calculateGroup(int row,int collumn){
		int group=0;
		if (row<3&&collumn<3)group=0;
		else if (row<3&&collumn<6) group=1;
		else if (row<3&&collumn<9) group=2;
		else if (row<6&&collumn<3) group=3;
		else if (row<6&&collumn<6) group=4;
		else if (row<6&&collumn<9) group=5;
		else if (row<9&&collumn<3) group=6;
		else if (row<9&&collumn<6) group=7;
		else if (row<9&&collumn<9) group=8;
		
		return group;
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
/*		try {
			sudoku=(Sudoku) this.clone();
			
			
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*sudoku.setCollumnArray((ArrayList<Collumn>)getCollumnArray().clone());
		sudoku.setRowArray((ArrayList<Row>)getRowArray().clone());
		sudoku.setThreeByThreeArray((ArrayList<ThreeByThreeSquare>)getThreeByThreeArray().clone());*/
		
		return sudoku;
	}
	
	


	
	public int compareTo(Sudoku o) {
		// TODO Auto-generated method stub
		return 0;
	}


}

package main.java.beans;

import java.util.ArrayList;

public abstract class Group {
   
   private int index;
	
	private ArrayList<Cell> group;

	private Sudoku sudoku;
	
	public Group(Sudoku sudoku,int index){
		this.index = index;
		this.sudoku=sudoku;
		if (group==null){
		   group=new ArrayList<Cell>();
		   
		   for(int i=0;i<9;i++){
		      Cell cell = new Cell(this);
		      group.add( cell ); 
		   }	
		}
	}

	public ArrayList<Cell> getGroup() {
		return group;
	}

	public void setGroup(ArrayList<Cell> group) {
		this.group = group;
	}
	
	public int getIndex() {
      return index;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   /**
 * @return the sudoku
 */
public Sudoku getSudoku() {
	return sudoku;
}

/**
 * @param sudoku the sudoku to set
 */
public void setSudoku(Sudoku sudoku) {
	this.sudoku = sudoku;
}

@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Cell cell : group) {
			sb.append("\n");
			sb.append(cell);
		}
		return sb.toString();
	}
	
	
}

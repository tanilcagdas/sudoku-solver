package main.java.beans;

import java.util.ArrayList;

public abstract class Group {
	
	private ArrayList<Cell> group;
	
	public Group(){
		getGroup();
	}

	public ArrayList<Cell> getGroup() {
		if (group==null){
			group=new ArrayList<Cell>();
			
		for(int i=0;i<9;i++){
			group.add( new Cell()); 
		}	
		}
		return group;
	}

	public void setGroup(ArrayList<Cell> group) {
		this.group = group;
	}
	
	
}

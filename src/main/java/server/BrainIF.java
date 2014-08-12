package main.java.server;

import main.java.beans.Sudoku;

public interface BrainIF {
	
	
	

	public Sudoku solveSudoku(Sudoku sudoku);

	public Sudoku loadDemoSudoku(Sudoku demoSudoku);

}

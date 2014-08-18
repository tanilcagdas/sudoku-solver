package main.java.server;

import java.lang.reflect.InvocationTargetException;

import main.java.beans.Sudoku;

public interface BrainIF {

	public Sudoku solveSudoku(Sudoku sudoku);

	public Sudoku solveSudokuStepByStep(Sudoku sudoku,int algorithm);

	public Sudoku loadDemoSudoku(Sudoku demoSudoku);
	
	public boolean isSudokuCorrect(Sudoku sudoku) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;

}

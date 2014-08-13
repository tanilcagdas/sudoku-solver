package main.java.ui.controller;
import java.io.IOException;

import main.java.beans.Sudoku;
import main.java.server.BrainIF;
import main.java.server.parser.Parser;


public class SudokuController {
	private String className="main.java.server.BrainImpl";
	private BrainIF brain;
	
	private static Sudoku sudoku;
	
	private static Sudoku SudokuSolution;
	
	public SudokuController() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		brain = (BrainIF) BrainIF.class.forName(className).newInstance();
		reset();
	}
	
	public String action(){
		SudokuSolution=brain.solveSudoku(sudoku);
		return "succes";
	}
	
	public String reset(){
		sudoku= new Sudoku();
		SudokuSolution = new Sudoku();
		return null;
	}

	public String loadDemoSudoku(){
		sudoku = brain.loadDemoSudoku(sudoku);
		return null;
	}
	
	public String loadWebSudoku() throws IOException{
		sudoku = Parser.parseWebSudoku();
		return null;
	}

	public  Sudoku getDemoSudoku() {
		return sudoku;
	}
	
	public  void setDemoSudoku(Sudoku demoSudoku) {
		SudokuController.sudoku = demoSudoku;
	}
	public  Sudoku getSudokuSolution() {
		return SudokuSolution;
	}
	public  void setSudokuSolution(Sudoku sudokuSolution) {
		SudokuSolution = sudokuSolution;
	} 

}

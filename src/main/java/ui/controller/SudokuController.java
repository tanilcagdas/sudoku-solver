package main.java.ui.controller;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import main.java.beans.Cell;
import main.java.beans.Sudoku;
import main.java.server.BrainIF;
import main.java.server.parser.Parser;
import main.java.server.util.NotSolvedWriter;


public class SudokuController {
	private String className="main.java.server.BrainImpl";
	private BrainIF brain;
	
	private static Sudoku sudoku;
	
	private static Sudoku sudokuSolution;
	
	private boolean sudokuCorrect;
	
	public SudokuController() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		brain = (BrainIF) BrainIF.class.forName(className).newInstance();
		reset();
	}
	
	public String action(){
		sudokuSolution=brain.solveSudoku(sudoku);
		return "succes";
	}

	public String solveStepByStep1(){
		solveStepByStep( 1);
		return "succes";
	}
	public String solveStepByStep2(){
		solveStepByStep( 2);
		return "succes";
	}
	public String solveStepByStep(int algorithm){
		if(!startedSolving()){
			sudokuSolution=brain.solveSudokuStepByStep(sudoku, algorithm);
		}else{
			sudokuSolution=brain.solveSudokuStepByStep(sudokuSolution, algorithm);
		}
		return "succes";
	}
	
	private boolean startedSolving() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell cell = sudokuSolution.getRowArray().get(i).getGroup().get(j);
				if(cell.getValue() != 0){
					return true;
				}
			}
		}
		return false;
	}

	public String reset(){
		sudoku= new Sudoku();
		sudokuSolution = new Sudoku();
		return null;
	}

	public String loadDemoSudoku(){
		reset();
		sudoku = brain.loadDemoSudoku(sudoku);
		return null;
	}
	
	public String loadWebSudoku() throws IOException{
		reset();
		sudoku = Parser.parseWebSudoku();
		return null;
	}

	public String loadUnSolvedSudoku() throws IOException{
		reset();
		sudoku = NotSolvedWriter.readANonSolvedSudoku();
		return null;
	}

	public boolean isSudokuCorrect() throws IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if(startedSolving()){
			return 	brain.isSudokuCorrect(sudokuSolution);
		}else {
			return false;
		}
	}

	/**
	 * @param sudokuCorrect the sudokuCorrect to set
	 */
	public void setSudokuCorrect(boolean sudokuCorrect) {
		this.sudokuCorrect = sudokuCorrect;
	}

	public  Sudoku getDemoSudoku() {
		return sudoku;
	}
	
	public  void setDemoSudoku(Sudoku demoSudoku) {
		SudokuController.sudoku = demoSudoku;
	}
	public  Sudoku getSudokuSolution() {
		return sudokuSolution;
	}
	public static void setSudokuSolution(Sudoku sudokuSolution) {
		SudokuController.sudokuSolution = sudokuSolution;
	} 

}

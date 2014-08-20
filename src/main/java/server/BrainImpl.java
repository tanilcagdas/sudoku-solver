package main.java.server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.beans.Cell;
import main.java.beans.Group;
import main.java.beans.Sudoku;
import main.java.server.util.NotSolvedWriter;

public class BrainImpl implements BrainIF {
	
	Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	private static final String RED = "red";
	private static final String BLUE = "blue";
	private static final String ROW = "row";
	private static final String ALL = "all";
	private static final String COLUMN = "column";
	private static final String THREExTHREE = "3x3";
	private static ArrayList<Integer> DEFAULT_GUESSES = new ArrayList<Integer>();
	private int trial;
	{
		if (DEFAULT_GUESSES.size() == 0) {
			for (int i = 1; i < 10; i++)
				DEFAULT_GUESSES.add(i);

		}
	}
	private boolean sudokuCorrect;

	public Sudoku solveSudoku(Sudoku sudoku) {
		Sudoku sudokuSolution = new Sudoku();
		sudokuSolution = sudoku.copy();
		sudokuSolution.setSudokuHasChanged(true);
		try {
			evaluateGuesses(sudokuSolution);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error Ocured", e);
		}
		//  Solve the f.cking sudoku

		//  Check if the sudoku has changed
		trial = 1;
		while (sudokuSolution.isSudokuHasChanged()) {
			while (sudokuSolution.isSudokuHasChanged()) {
				solveSudokuByAlgorithm1(sudokuSolution);
			}
			solveSudokuByAlgorithm2(sudokuSolution);

		}
		if (!sudokuSolution.isSolved()) {
			try {
				NotSolvedWriter.log(sudoku, sudokuSolution);
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Error Ocured", e);
			}
		}
		return sudokuSolution;
	}

	public Sudoku solveSudokuStepByStep(Sudoku sudokuSolution, int algorithm) {
		try {
			evaluateGuesses(sudokuSolution);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error Ocured", e);
		}

		//  Check if the sudoku has changed
		trial = 1;

		//  reflection
		String methodName = "solveSudokuByAlgorithm" + algorithm;
		try {
			Method method = getClass().getDeclaredMethod(methodName,
					Sudoku.class);
			// method.invoke(this, sudoku);
			method.invoke(this, sudokuSolution);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error Ocured", e);
		} 
		return sudokuSolution;
	}

	public Sudoku solveSudokuByAlgorithm1(Sudoku sudokuSolution) {
		sudokuSolution.setSudokuHasChanged(false);
		try {
			clearGuessesInGroup(sudokuSolution);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error Ocured", e);
		}
		try {
			determineCellsWhoHas1Guess(sudokuSolution);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error Ocured", e);
		}
		if (sudokuSolution.getHowManyCellsLeft() == 0) {
			sudokuSolution.setSolved(true);
			sudokuSolution.setSudokuHasChanged(false);
			System.out.println("Sudoku is solved");
			return sudokuSolution;
		}
		System.out.println("This is the trial number: " + trial);
		trial++;
		return sudokuSolution;
	}

	public Sudoku solveSudokuByAlgorithm2(Sudoku sudokuSolution) {
		if (sudokuSolution.getHowManyCellsLeft() != 0)
			try {
				determineWhoHasUniqueGuessInGroup(sudokuSolution);
			} 
			catch (Exception e) {
				if(e.getCause() instanceof SudokuException){
					System.out.println(e.getCause().getMessage());
					return sudokuSolution;
				}else {
					logger.log(Level.SEVERE, "Error Ocured", e);
				}
			}
		// 
		if (sudokuSolution.getHowManyCellsLeft() == 0) {
			sudokuSolution.setSolved(true);
			sudokuSolution.setSudokuHasChanged(false);
			System.out.println("Sudoku is solved");
			return sudokuSolution;
		}
		return sudokuSolution;
	}

	public Sudoku loadDemoSudoku(Sudoku demoSudoku) {
		//  set all zeros
		for (int row = 0; row < demoSudoku.getRowArray().size(); row++) {
			for (int column = 0; column < demoSudoku.getRowArray().get(row)
					.getGroup().size(); column++)
				demoSudoku.getRowArray().get(row).getGroup().get(column)
						.setValue(0);
		}
		//  put known values
		createSudoku createSudoku = new createSudoku();
		createSudoku.loadSudoku1(demoSudoku);

		return demoSudoku;

	}

	private void methodRange(Sudoku sudoku, String methodName, String range)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Method method;
		if (range.equalsIgnoreCase(ALL)) {
			method = getClass().getDeclaredMethod(methodName, Cell.class);
			for (int row = 0; row < 9; row++) {
				for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
					Cell cell = sudoku.getRowArray().get(row).getGroup()
							.get(columnIndex);
					method.invoke(this, cell);
				}
			}
		} else {
			String str = null;
			method = getClass().getDeclaredMethod(methodName, Group.class);
			if (methodName.equals("clearGuessesInGroup"))
				str = "Clearing guesses";
			else if (methodName.equals("determineWhoHasUniqueGuessInGroup"))
				str = "Determining Who Has Unique Guess In Group";

			Group group = null;
			for (int i = 0; i < 9; i++) {
				if (range.equalsIgnoreCase(ROW)) {
					group = sudoku.getRowArray().get(i);
				} else if (range.equalsIgnoreCase(COLUMN)) {
					group = sudoku.getColumnArray().get(i);
				} else if (range.equalsIgnoreCase("3x3")) {
					group = sudoku.getThreeByThreeArray().get(i);
				}
				// System.out.println(str + " from " + range +
				// " with the index of: " + i);
				method.invoke(this, group);
			}
		}
	}

	private void evaluateGuesses(Cell cell) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		if (cell.getValue() == 0) {
			cell.setGuesses((ArrayList<Integer>) DEFAULT_GUESSES.clone());
		} else {
			cell.setFound(true);
		}

		// System.out.println(cell.getValue() + ", " + cell.isFound());
	}

	private void clearGuessesInGroup(Group group) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		ArrayList<Integer> foundValuesInGroup = new ArrayList<Integer>();
		foundValuesInGroup.clear();
		for (int i = 0; i < 9; i++) {
			if (group.getGroup().get(i).getValue() != 0) {
				foundValuesInGroup.add(group.getGroup().get(i).getValue());
			}
		}
		for (int foundValues : foundValuesInGroup) {
			for (int Groupidx = 0; Groupidx < 9; Groupidx++) {
				for (int gssidx = 0; gssidx < 9; gssidx++) {
					ArrayList<Integer> Guesses = null;
					try {
						Guesses = group.getGroup().get(Groupidx).getGuesses();
					} catch (Exception e) {
						logger.log(Level.SEVERE, "Error Ocured", e);
					}
					if (Guesses != null && Guesses.size() > gssidx
							&& Guesses.get(gssidx) == foundValues) {
						group.getGroup().get(Groupidx).getGuesses()
								.remove(gssidx);
						if (group.getSudoku().isSudokuHasChanged() == false) {
							group.getSudoku().setSudokuHasChanged(true);
						}
						// System.out
						// .println("the Guess: "
						// + foundValues
						// + "have been removed from group element with id "
						// + Groupidx);
						continue;
					}
				}
			}

		}
	}

	private void determineCellsWhoHas1Guess(Cell cell)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		if (cell.getValue() == 0 && cell.getGuesses() != null
				&& cell.getGuesses().size() == 1) {
			int value = cell.getGuesses().get(0);
			cell.setValue(value);
			cell.setColor(RED);
			if (cell.getRow().getSudoku().isSudokuHasChanged() == false) {
				cell.getRow().getSudoku().setSudokuHasChanged(true);
				System.out.println("sudoku has changed value has been found");
			}
		}
	}


	private void determineWhoHasUniqueGuessInGroup(Group group)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException, SudokuException {

		for (int number = 1; number < 10; number++) {
			int uniqueGuessCount = 0;
			for (Cell cell : group.getGroup()) {
				if (cell.getGuesses() != null) {
					for (int guess : cell.getGuesses()) {
						if (guess == number) {
							uniqueGuessCount++;
						}
					}
				}
			}
			if (uniqueGuessCount == 1) {
				for (Cell cell : group.getGroup()) {
					if(cell.getValue()==number){
//						System.out.println("The number : " + number +" is a unique guess but it exists in the group");
						return;
					}
				}
				
				
				markAsUniqueGuessAndDetermine(number, group);
				setSudokuCorrect(true);
				isSudokuCorrect(group);
				if(!isSudokuCorrect()){
					throw new SudokuException("Sudoku is not Correct after markAsUniqueGuessAndDetermine number : " +number+", group : "+group );
				}
			}
		}
	}

	private void markAsUniqueGuessAndDetermine(int number, Group group) {
		for (int i = 0; i < 9; i++) {
			Cell cell = group.getGroup().get(i);
			if (cell.getGuesses() != null) {
				for (int guess : cell.getGuesses()) {
					if (guess == number) {
						// TODO check others
						for (int j = 0; j < 9; j++) {
							if (i == j)
								continue;
							Cell compareCell = group.getGroup().get(j);
							if (compareCell.getGuesses() != null) {
							for (int compareGuess : compareCell.getGuesses()) {
								if (compareGuess == number) {
									return;
								}
							}
							}
						}
						cell.setValue(number);
						cell.setColor(BLUE);
						group.getSudoku().setSudokuHasChanged(true);
						break;
					}
				}
			}
		}
	}

	private void evaluateGuesses(Sudoku sudoku) throws SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		methodRange(sudoku, "evaluateGuesses", ALL);
	}

	private void clearGuessesInGroup(Sudoku sudoku) throws SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		methodRange(sudoku, "clearGuessesInGroup", ROW);
		methodRange(sudoku, "clearGuessesInGroup", COLUMN);
		methodRange(sudoku, "clearGuessesInGroup", "3x3");
	}

	private void determineCellsWhoHas1Guess(Sudoku sudokuSolution)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		methodRange(sudokuSolution, "determineCellsWhoHas1Guess", ALL);
		System.out.println(sudokuSolution.getHowManyCellsLeft()
				+ " Cells is waiting to be solved");
	}


	private void determineWhoHasUniqueGuessInGroup(Sudoku sudokuSolution)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		methodRange(sudokuSolution, "determineWhoHasUniqueGuessInGroup", ROW);
		methodRange(sudokuSolution, "determineWhoHasUniqueGuessInGroup",
				COLUMN);
		methodRange(sudokuSolution, "determineWhoHasUniqueGuessInGroup", "3x3");

	}

	@Deprecated
	
	public boolean isSudokuCorrect(Sudoku sudoku) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		setSudokuCorrect(true);
		methodRange(sudoku, "isSudokuCorrect",ROW);
		if(isSudokuCorrect())
		methodRange(sudoku, "isSudokuCorrect",COLUMN);
		if(isSudokuCorrect())
		methodRange(sudoku, "isSudokuCorrect",THREExTHREE);
		return isSudokuCorrect();
	}

	private void isSudokuCorrect(Group group) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		for (int i = 0; i < 9; i++) {
			int uniqueValue = group.getGroup().get(i).getValue();
			if(uniqueValue == 0){
				continue;
			}
			for (int j = 0; j < 9; j++) {
				if(i==j)
					continue;
				int compareValue = group.getGroup().get(j).getValue();
				if(compareValue == uniqueValue){
					setSudokuCorrect(false);
					return;
				}
			}
			
		}
	}

	/**
	 * @return the sudokuCorrect
	 */
	public boolean isSudokuCorrect() {
		return sudokuCorrect;
	}

	/**
	 * @param sudokuCorrect the sudokuCorrect to set
	 */
	public void setSudokuCorrect(boolean sudokuCorrect) {
		this.sudokuCorrect = sudokuCorrect;
	}

}

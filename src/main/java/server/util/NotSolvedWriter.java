package main.java.server.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import main.java.beans.Sudoku;

public class NotSolvedWriter {

	public static void log(Sudoku sudoku, Sudoku unfinishedSolution)
			throws IOException {
		if(sudoku.getHowManyCellsLeft()!=81){
		StringBuffer sb = new StringBuffer();
		sb.append("Date : ");
		sb.append(new Date().toString());
		sb.append("\n");
		sb.append("_____________Sudoku__________________");
		sb.append("\n");
		sb.append(sudoku.toString());
		sb.append("_____________Unfinished_Solution__________________");
		sb.append("\n");
		sb.append(unfinishedSolution.toString());

		String outputFile = "sudokusolver/unSolvedSudoku_"
				+ new Date().getTime();
		Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(outputFile), "UTF8"));
		writer.write(sb.toString());
		writer.close();
		}else{
			System.out.println("This is an empty sudoku aint writing");
		}		
	}

	public static Sudoku readANonSolvedSudoku() throws FileNotFoundException {
		Sudoku sudoku = new Sudoku();
		Collection<File> all = getAllFileNames();
		Object[] strArray = all.toArray();
		if (strArray.length != 0) {
			File file = (File) strArray[0];
			file.toString();
			String inputFile = file.toString();
			Reader reader = new InputStreamReader(
					new FileInputStream(inputFile));
			String str = "";
			try {
				for (int i = 0; i < 1130; i++) {
					char c = (char) reader.read();
					str = str + c;
				}
				// System.out.println(str);
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					int value = Character.getNumericValue(((str.split("" + i
							+ j + " : "))[1].charAt(0)));
					// System.out.println(value);
					sudoku.getRowArray().get(i).getGroup().get(j)
							.setValue(value);
				}
			}
		}
		return sudoku;
	}

	public static void main(String[] args) throws FileNotFoundException {
		readANonSolvedSudoku();
	}

	public static Collection<File> getAllFileNames() {
		Collection<File> all = new ArrayList<File>();
		addTree(new File("D:/springsource/sts-3.4.0.RELEASE/sudokusolver"), all);
		return all;
	}

	static void addTree(File file, Collection<File> all) {
		File[] children = file.listFiles();
		if (children != null) {
			for (File child : children) {
				all.add(child);
				addTree(child, all);
			}
		}
	}

}

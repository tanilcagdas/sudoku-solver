package main.java.server.parser;

import java.io.IOException;

import main.java.beans.Cell;
import main.java.beans.Sudoku;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

	public static void main(String[] args) throws IOException {
		parseWebSudoku();
	}

	public static Sudoku parseWebSudoku() throws IOException {

		Sudoku inputSudoku = new Sudoku();
		Document doc = Jsoup.connect("http://show.websudoku.com/").get();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Elements elements = doc.select("#c" + j + i);
				String value = elements.get(0).getAllElements().get(1).attributes().get("value");

				Cell cell = inputSudoku.getRowArray().get(i).getGroup().get(j);
				if (!value.equals("")) {
					cell.setValue(Integer.parseInt(value));
				} else {
					cell.setValue(0);
				}

				// System.out.println("cell : "+i+j+" value : "+value);
			}
		}

		Elements elements2 = doc.select("a[href], [title]");
		System.out.println("Loaded web sudoku");
		// contains "Copy link for this puzzle"
		String puzzleId = null;
		String puzzleLevelStr =  null;
		for (Element element : elements2) {
			if (element.toString().contains("Copy link for this puzzle")) {
				puzzleId = element.toString().split("</A>")[0].split("set_id=")[1].split("\"")[0];
				puzzleLevelStr  = element.toString().split("level=")[1].split("&")[0];
			}
		}
		int puzzleIdInt = Integer.parseInt(puzzleId.trim());
		inputSudoku.setPuzzleId(puzzleIdInt);
		int puzzleLevelInt = Integer.parseInt(puzzleLevelStr.trim());
		inputSudoku.setPuzzleLevel(puzzleLevelInt);
		return inputSudoku;
	}

	public static Sudoku parseWebSudoku(long selectedSudokuId,int selectedSudokuLevel) throws IOException {

		Sudoku inputSudoku = new Sudoku();
		Document doc;
		if(selectedSudokuId != 0){
			doc = Jsoup.connect("http://show.websudoku.com/?level="+selectedSudokuLevel+"&set_id=" + selectedSudokuId).get();
		}else if(selectedSudokuLevel != 0){
			doc = Jsoup.connect("http://show.websudoku.com/?level="+selectedSudokuLevel).get();
		}else {
			doc = Jsoup.connect("http://show.websudoku.com/").get();
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Elements elements = doc.select("#c" + j + i);
				String value = elements.get(0).getAllElements().get(1).attributes().get("value");

				Cell cell = inputSudoku.getRowArray().get(i).getGroup().get(j);
				if (!value.equals("")) {
					cell.setValue(Integer.parseInt(value));
				} else {
					cell.setValue(0);
				}

			}
		}

		Elements elements2 = doc.select("a[href], [title]");
		System.out.println("Loaded web sudoku");
		// contains "Copy link for this puzzle"
		String puzzleId = null;
		String puzzleLevelStr = null;
		for (Element element : elements2) {
			if (element.toString().contains("Copy link for this puzzle")) {
				puzzleId = element.toString().split("</A>")[0].split("set_id=")[1].split("\"")[0];
				puzzleLevelStr = element.toString().split("level=")[1].split("&")[0];
			}
		}
		try {
			long puzzleIdInt = Long.parseLong(puzzleId.trim());
			inputSudoku.setPuzzleId(puzzleIdInt);
			int puzzleLevelInt = Integer.parseInt(puzzleLevelStr.trim());
			inputSudoku.setPuzzleLevel(puzzleLevelInt);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputSudoku;
	}

}

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
	
	public static Sudoku parseWebSudoku() throws IOException{

		Sudoku inputSudoku = new Sudoku();
		Document doc = Jsoup.connect("http://show.websudoku.com/").get();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Elements elements = doc.select("#c"+i+j);
				String value = elements.get(0).getAllElements().get(1).attributes().get("value");
					
					Cell cell = inputSudoku.getRowArray().get(i).getGroup().get(j);
					if(!value.equals("")){
						cell.setValue(Integer.parseInt(value));
					}else{
						cell.setValue(0);
					}
					
//					System.out.println("cell : "+i+j+" value : "+value);
			}
		}
		System.out.println("Loaded web sudoku");
	
		return inputSudoku;
		
	}

}

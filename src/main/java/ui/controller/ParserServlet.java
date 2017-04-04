package main.java.ui.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import main.java.beans.Cell;
import main.java.beans.Row;
import main.java.beans.Sudoku;

/**
 * Servlet implementation class SudokuServlet
 */
public class ParserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ParserServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ObjectMapper mapper = new ObjectMapper();

		SudokuController sc;
		Sudoku sudoku = null;
		List<Row> rowArray = null;
		try {
			sc = new SudokuController();
			sc.setSelectedSudokuLevel(4);
			sc.loadCustomWebSudoku();
			sudoku = sc.getDemoSudoku();
			System.out.println(sudoku);
			rowArray = sudoku.getRowArray();
			for (Row row : rowArray) {
				row.setSudoku(null);
				for (Cell cell : row.getGroup()) {
					cell.setRow(null);
					cell.setColumn(null);
					cell.setThreeByThreeSquare(null);
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		

	    String responseToClient = null;
		try {
			responseToClient = mapper.writeValueAsString(rowArray);
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write(responseToClient);
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

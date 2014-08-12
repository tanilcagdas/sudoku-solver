package main.java.server;

import main.java.beans.Sudoku;

public class createSudoku {

	public Sudoku changeValue(Sudoku sudoku,int row,int collumn,int value){
		sudoku.getRowArray().get(row).getGroup().get(collumn).setValue(value);
	return sudoku;
}
	
	public Sudoku loadSudoku1(Sudoku sudoku){
/*	fptr = fopen("test.txt","w+");  if (fptr == NULL){ cout<<"                     problem opening the file"<<endl<<                    "check the sudoku.txt file"; menu(); }
		   fprintf(fptr,"%2d",  9) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;
		   fprintf(fptr,"%2d",  3) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;
		   fprintf(fptr,"%2d",  5) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d\n",  7) ;*/
		   
		   changeValue(sudoku, 0, 0, 9);
		   changeValue(sudoku, 0, 3, 3);
		   changeValue(sudoku, 0, 6, 5);
		   changeValue(sudoku, 0, 8, 7);
		   
		   
		 /*  fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  8) ;   fprintf(fptr,"%2d",  7) ;
		   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  1) ;
		   fprintf(fptr,"%2d",  3) ;   fprintf(fptr,"%2d",  4) ;   fprintf(fptr,"%2d\n",  0) ;*/ 
		   
		   changeValue(sudoku, 1, 1, 8);
		   changeValue(sudoku, 1, 2, 7);
		   changeValue(sudoku, 1, 5, 1);
		   changeValue(sudoku, 1, 6, 3);
		   changeValue(sudoku, 1, 7, 4);
		   
		  /* fprintf(fptr,"%2d",  5) ;   fprintf(fptr,"%2d",  4) ;   fprintf(fptr,"%2d",  0) ;
		   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  6) ;
		   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  2) ;   fprintf(fptr,"%2d\n",  0) ;*/
		   
		   changeValue(sudoku, 2, 0, 5);
		   changeValue(sudoku, 2, 1, 4);
		   changeValue(sudoku, 2, 5, 6);
		   changeValue(sudoku, 2, 7, 2);
		  
		   /*fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  7) ;   fprintf(fptr,"%2d",  6) ;
		   fprintf(fptr,"%2d",  2) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  8) ;
		   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d\n",  3) ;*/
		   
		   changeValue(sudoku, 3, 1, 7);
		   changeValue(sudoku, 3, 2, 6);
		   changeValue(sudoku, 3, 3, 2);
		   changeValue(sudoku, 3, 5, 8);
		   changeValue(sudoku, 3, 8, 3);
		   
		   /*fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;
		   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  4) ;   fprintf(fptr,"%2d",  0) ;
		   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d\n",  0) ;*/
		   
		   changeValue(sudoku, 4, 4, 4);
		   
		   /*fprintf(fptr,"%2d",  4) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;
		   fprintf(fptr,"%2d",  6) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  5) ;
		   fprintf(fptr,"%2d",  1) ;   fprintf(fptr,"%2d",  7) ;   fprintf(fptr,"%2d\n",  0) ;*/
		   
		   changeValue(sudoku, 5, 0, 4);
		   changeValue(sudoku, 5, 3, 6);
		   changeValue(sudoku, 5, 5, 5);
		   changeValue(sudoku, 5, 6, 1);
		   changeValue(sudoku, 5, 7, 7);
		   
		   /*fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  9) ;   fprintf(fptr,"%2d",  0) ;
		   fprintf(fptr,"%2d",  8) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;
		   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  6) ;   fprintf(fptr,"%2d\n",  5) ;*/
		   
		   changeValue(sudoku, 6, 1, 9);
		   changeValue(sudoku, 6, 3, 8);
		   changeValue(sudoku, 6, 7, 6);
		   changeValue(sudoku, 6, 8, 5);
		   
		   /*fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  1) ;   fprintf(fptr,"%2d",  8) ;
		   fprintf(fptr,"%2d",  5) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;
		   fprintf(fptr,"%2d",  2) ;   fprintf(fptr,"%2d",  3) ;   fprintf(fptr,"%2d\n",  0) ;*/
		   
		   changeValue(sudoku, 7, 1, 1);
		   changeValue(sudoku, 7, 2, 8);
		   changeValue(sudoku, 7, 3, 5);
		   changeValue(sudoku, 7, 6, 2);
		   changeValue(sudoku, 7, 7, 3);
		   
		   /*fprintf(fptr,"%2d",  7) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  2) ;
		   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  3) ;
		   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  0) ;   fprintf(fptr,"%2d",  9) ;*/
		   
		   changeValue(sudoku, 8, 0, 7);
		   changeValue(sudoku, 8, 2, 2);
		   changeValue(sudoku, 8, 5, 3);
		   changeValue(sudoku, 8, 8, 9);
		   
			   /*fclose(fptr);
			   cout<<"                              !test.txt created!"<<endl;*/
		   sudoku.setHowManyCellsLeft(44);
			return sudoku;   
			}
		}

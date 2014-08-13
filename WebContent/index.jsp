<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.sudokuSolution_jsp
{

margin:auto;
width:50%;
background-color:#f0e0e6;
}
.sudoku_jsp
{
margin-top:auto;
margin:auto;
width:50%;
background-color:#b0e0e6;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
</head>
<body>

<f:view>
<h:form>
	<rich:tabPanel switchType="client" >
        <rich:tab label="Sudoku" name="sudoku">
            <f:subview id="SUDOKU">
<div class="sudoku_jsp"  >
 <jsp:include page="sudoku.jsp"></jsp:include>
 </div>
 </f:subview>
 </rich:tab>
         <rich:tab label="SudokuSolution" name="sudokuSolution">
            <f:subview id="SUDOKUSOLUTION">
 <div class="sudokuSolution_jsp"  >
 <jsp:include page="sudokuSolution.jsp"></jsp:include>
 </div>
 </f:subview>
 </rich:tab>
 </rich:tabPanel>
 <h:commandButton action="#{sudokuController.action}" value="solve"></h:commandButton>
 <h:commandButton action="#{sudokuController.loadDemoSudoku}" value="loadDemoSudoku"></h:commandButton>
 <h:commandButton action="#{sudokuController.loadWebSudoku}" value="loadWebSudoku"></h:commandButton>
 <h:commandButton action="#{sudokuController.reset}" value="reset"></h:commandButton>
</h:form>
</f:view>
</body>
</html>
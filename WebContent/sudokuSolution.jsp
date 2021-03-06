<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>


<center>

			  
			<rich:dataTable  id="row" value="#{sudokuController.sudokuSolution.rowArray}" var="row"  >  
              <f:facet name="header">
				<rich:columnGroup>
				
					<h:column>
						<h:outputText styleClass="headerText" value="" />
					</h:column>
				
					<h:column>
						<h:outputText styleClass="headerText" value="S" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="O" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="L" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="U" />
					</h:column>
	
					<h:column>
						<h:outputText styleClass="headerText" value="T" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="I" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="O" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="N" />
					</h:column>
	
					
					
				</rich:columnGroup>
			</f:facet>
              
              
                <h:column>  
                <h:dataTable id="col" value="#{row.group}" var="col" rows="1" first="0">  
                    <h:column>  
                        <h:outputText id="value" value="#{col.value}" style="color:#{col.color}"/>  
                    </h:column>  
                </h:dataTable>  
                </h:column>
                <h:column>  
                <h:dataTable id="col2" value="#{row.group}" var="col" rows="1" first="1">  
                    <h:column>  
                        <h:outputText id="value" value="#{col.value}" style="color:#{col.color}"/>  
                    </h:column>  
                </h:dataTable>  
                </h:column> 
                <h:column>  
                <h:dataTable id="col3" value="#{row.group}" var="col" rows="1" first="2">  
                    <h:column>  
                        <h:outputText id="value" value="#{col.value}" style="color:#{col.color}"/>  
                    </h:column>  
                </h:dataTable>  
                </h:column>
                <h:column>  
                <h:dataTable id="col4" value="#{row.group}" var="col" rows="1" first="3">  
                    <h:column>  
                        <h:outputText id="value" value="#{col.value}" style="color:#{col.color}"/>  
                    </h:column>  
                </h:dataTable>  
                </h:column>
                <h:column>  
                <h:dataTable id="col5" value="#{row.group}" var="col" rows="1" first="4">  
                    <h:column>  
                        <h:outputText id="value" value="#{col.value}" style="color:#{col.color}"/>  
                    </h:column>  
                </h:dataTable>  
                </h:column>
                <h:column>  
                <h:dataTable id="col6" value="#{row.group}" var="col" rows="1" first="5">  
                    <h:column>  
                        <h:outputText id="value" value="#{col.value}" style="color:#{col.color}"/>  
                    </h:column>  
                </h:dataTable>  
                </h:column>
                <h:column>  
                <h:dataTable id="col7" value="#{row.group}" var="col" rows="1" first="6">  
                    <h:column>  
                        <h:outputText id="value" value="#{col.value}" style="color:#{col.color}"/>  
                    </h:column>  
                </h:dataTable>  
                </h:column>
                <h:column>  
                <h:dataTable id="col8" value="#{row.group}" var="col" rows="1" first="7">  
                    <h:column>  
                        <h:outputText id="value" value="#{col.value}" style="color:#{col.color}"/>  
                    </h:column>  
                </h:dataTable>  
                </h:column>
                <h:column>  
                <h:dataTable id="col9" value="#{row.group}" var="col" rows="1" first="8">  
                    <h:column>  
                        <h:outputText id="value" value="#{col.value}" style="color:#{col.color}"/>  
                    </h:column>  
                </h:dataTable>  
                </h:column>
                
            </rich:dataTable> 
			<h:outputText value="how many sells left : "></h:outputText>
			<h:outputText value="#{sudokuController.sudokuSolution.howManyCellsLeft}"></h:outputText>
			<h:outputText value=" sudoku solved :"></h:outputText>
			<h:outputText value="#{sudokuController.sudokuSolution.solved}"></h:outputText>
			<h:outputText value=" is Sudoku correct : "></h:outputText>
			<h:outputText value="#{sudokuController.sudokuCorrect}"></h:outputText>
</center>



</f:view>
</body>
</html>
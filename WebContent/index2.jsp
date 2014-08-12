<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
</head>
<body>
<f:view>
<h:outputText value="Hello_World"></h:outputText>

<rich:dataTable value="#{sudokuBean.demoRow}" var="item" rowKeyVar="row">

	
			<f:facet name="header">
				<rich:columnGroup>
				
					<h:column>
						<h:outputText styleClass="headerText" value="" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="S" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="U" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="D" />
					</h:column>
	
					<h:column>
						<h:outputText styleClass="headerText" value="O" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="K" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="U" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="" />
					</h:column>
	
					<h:column>
						<h:outputText styleClass="headerText" value="" />
					</h:column>
					
					
				</rich:columnGroup>
			</f:facet>
				
			<h:column>
			    <h:outputText value="#{item.value}" />			    
			</h:column>
			<h:column>
			   <h:outputText value="#{item.value}" />			    
			</h:column>
			<h:column>
			    <h:outputText value="#{item.value}" />			    
			</h:column>
			<h:column>
			    <h:outputText value="#{item.value}" />			    
			</h:column>
			<h:column>
			    <h:outputText value="#{item.value}" />			    
			</h:column>
			<h:column>
			    <h:outputText value="#{item.value}" />			    
			</h:column>
			<h:column>
			    <h:outputText value="#{item.value}" />			    
			</h:column>
			<h:column>
			    <h:outputText value="#{item.value}" />			    
			</h:column>
			<h:column>
			    <h:outputText value="#{item.value}" />			    
			</h:column>
			
			</rich:dataTable>
</f:view>
</body>
</html>
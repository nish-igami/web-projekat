<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<!--<jsp:useBean id="component" class="model.ComponentBean" scope="session"/> -->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="stylesheet.css">										 
	</head>
	
	<body>
		<table class="tabela">
		<c:forEach items="${components}" var="component" >
			<tr>
				<td> Name : ${component.name} </td>
				<td> Description : <textarea rows="4" cols="25" name="description" disabled="disabled">
						${component.description} </textarea> </td>
				<td> Price : ${component.price}</td>
				<td> Quantity : ${component.availableQuantity} </td>
				<td> This component is in ${component.category.name} category</td>
				<td> Homepage of component : ${component.homeURL} </td>
				<td> Component picture: <img src="${component.pictureURL}" alt="no picture available!" height="66" width="66" /> </td>
				
				<td><a href="MasterPrepareServlet?action=editComponent&id=${component.id}"> Edit </a></td>
				<td><a href="MasterPrepareServlet?action=deleteComponent&id=${component.id}"> Delete </a></td>
			</tr>
		</c:forEach>	
		</table>
			
	</body>
</html>
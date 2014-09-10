<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<jsp:useBean id="category" class="model.CategoryBean" scope="session"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		
		<table>
		<c:forEach items="${categories}" var="category" >
			<tr>
				<td> Name : ${category.name} </td>
				<td> Description : <textarea rows="4" cols="25" name="description" disabled="disabled">
						${category.description} </textarea> </td>
				<td> <c:choose>
				<c:when test="${category.subcategoryName == 'notSub'}" >
					This category is not a subcategory of any category!
			</c:when>
			<c:when test="${category.subcategoryName != 'notSub'}" >
				This category is a subcategory of ${category.subcategoryName}
			</c:when>
			</c:choose></td>
			<td><a href="MasterPrepareServlet?action=editCategory&id=${category.id}"> Edit </a></td>
			<td><a href="MasterPrepareServlet?action=deleteCategory&id=${category.id}"> Delete </a></td>
			</tr>
		</c:forEach>	
		</table>
		
		
			
	</body>
</html>
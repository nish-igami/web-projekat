<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<form method="POST" action="MasterServlet?action=editedCategory" accept-charset="UTF-8">    
			Enter name <input disabled="disabled" type="text" name="ime" value="${category.name}" /> <br />
			Description : <textarea rows="4" cols="50" name="description"> ${category.description}</textarea>
			<input type="hidden" name="id" value="${category.id}" />
			<input type="hidden" name="name" value="${category.name}" />
		 	<select name="subCategory">
				<option value="notSub"> this isn't a subcategory </option>
				<c:forEach items="${categories}" var="category" >
					<option value="${category.name}">${category.name}</option>
				</c:forEach>			
			</select>
			  
			<input type="submit" value="Submit">
		</form>
	</body>
</html>
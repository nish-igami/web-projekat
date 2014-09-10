<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<!--<jsp:useBean id="newCategory" class="model.CategoryBean" scope="session"/>-->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<form method="POST" action="CreateCategoryServlet"> 
			Name : <input type="text" name="name" id="name" placeholder="name here..." /> <br />
			Description : <textarea rows="4" cols="50" name="description"></textarea> <br />
			
			Is this category also a subcategory of : 
		 	<select name="subCategory">
		 		<option value="notSub"> this isn't a subcategory </option>
				<c:forEach items="${categories}" var="category" >
					<option value="${category.name}">${category.name}</option>
				</c:forEach>			
			</select>  ?? <br />
	
			<input type="submit" value="Submit">
		</form>
	</body>
</html>
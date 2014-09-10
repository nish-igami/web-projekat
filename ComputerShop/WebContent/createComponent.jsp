<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<form method="POST" action="CreateComponentServlet">    
			Enter name : <input type="text" name="name" placeholder="name here..." /> <br />
			Component price : <input type="number" step="any" min="0" name="price" placeholder="and price here!" /> <br />
			Quantity : <input type="number" min="1" name="quantity" placeholder="enter quantity" /> <br />
			
			Description : <textarea rows="4" cols="50" name="description"> </textarea>

		 	<select name="category">
				<c:forEach items="${categories}" var="cat" >
					<option value="${cat.name}">${cat.name}</option>
				</c:forEach>			
			</select> <br />
  
			Add its homepage: <input type="url" name="homepage" /><br />
			Picture url: <input type="url" name="picture" /> <br /> 

			<input type="submit" value="Submit">
		</form>
	</body>
</html>
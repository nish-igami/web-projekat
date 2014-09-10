<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<form method="POST" action="MasterServlet?action=editedComponent" accept-charset="UTF-8">    
			
			<input type="hidden" name="id" value="${category.id}" />
			<input type="hidden" name="name" value="${category.name}" />
			
			Enter name <input disabled="disabled" type="text" name="ime" value="${component.name}" /> <br />
			Component price : <input type="number" step="any" min="0" name="price" placeholder="and price here!" />${component.price} <br />
			Quantity : <input type="number" min="1" name="quantity" placeholder="enter quantity" /> ${component.availableQuantity} <br />
			Description : <textarea rows="4" cols="50" name="description"> ${component.description}</textarea>
			
		 	<select name="category">
				<c:forEach items="${categories}" var="category" >
					<option value="${category.name}">${category.name}</option>
				</c:forEach>			
			</select>  <br />
  
			Add its homepage: <input type="url" name="homepage" />${component.homepage}<br />
			Picture url: <input type="url" name="picture" />${component.picture} <br /> 
			
			
			
			<input type="submit" value="Submit">
		</form>
	</body>
</html>
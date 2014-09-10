<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<form method="POST" action="MasterServlet?action=editedDevice" accept-charset="UTF-8">    
			Enter name <input disabled="disabled" type="text" name="ime" value="${device.name}" /> <br />
			Description : <textarea rows="4" cols="50" name="description"> ${device.description}</textarea>
			<input type="hidden" name="id" value="${device.id}" />
			<input type="hidden" name="name" value="${device.name}" />
		 	<select multiple name="components">
				<c:forEach items="${components}" var="component" >
					<option value="${component.name}">${component.name}</option>
				</c:forEach>			
			</select>
  
			<input type="submit" value="Submit">
		</form>
	</body>
</html>
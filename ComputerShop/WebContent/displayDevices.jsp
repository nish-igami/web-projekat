<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<jsp:useBean id="component" class="model.ComponentBean" scope="session"/>
<jsp:useBean id="dev" class="model.DeviceBean" scope="session"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		
		<table>
		<c:forEach items="${devices}" var="device" >
			<tr>
				<td> Name : ${device.name} </td>
				<td> Description : <textarea rows="4" cols="25" name="description" disabled="disabled"> 
				${device.description}
					      </textarea> </td>
		 		<td> Components :
					<c:forEach items="${device.components}" var="component" >
						-> ${component.name} <br />
					</c:forEach>
				</td>
				<td><a href="MasterPrepareServlet?action=editDevice&id=${device.id}"> Edit </a></td>
				<td><a href="MasterPrepareServlet?action=deleteDevice&id=${device.id}"> Delete </a></td>
			</tr>
		</c:forEach>	
		</table>
			
	</body>
</html>
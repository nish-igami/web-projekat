<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<jsp:useBean id="user" class="model.UserBean" scope="session"/>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>ComputerChopShop</title>
		<link rel='stylesheet' type='text/css' href='css/stylesheet.css'/>
		<!--  <link href="<%=request.getContextPath()%>/css/MyStyles.css"	rel="stylesheet" type="text/css">  -->
	    <script type='text/javascript' src='script.js'></script>        
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
		<script src='https://cdn.firebase.com/js/client/1.0.15/firebase.js'></script>
	</head>

	<body>
		
		<div class="header"> <a href="bootstrapLogin.jsp"><img src="resources/images/users.png" height="64" width="64"> </a>
					    <a href="bootstrapLoginAdmin.jsp"><img src="resources/images/admin.png" height="64" width="64"> </a>
					   <a href="bootstrapRegister.jsp"><img src="resources/images/register.jpg" height="64" width="64"> </a> 
		</div>

		<h1> Welcome page </h1>
		
		<br />
		
		<table> 
			<tr> 
				<td> 
					<a href="bootstrapLogin.jsp" > <img src="resources/images/users.png" height="256" width="256"> </a>
			    </td> 
				<td> 
					<a href="bootstrapLoginAdmin.jsp" > <img src="resources/images/admin.png" height="256" width="256"> </a> 
				</td>
				<td>
					<a href="bootstrapRegister.jsp" > <img src="resources/images/register.jpg" height="256" width="256"> </a>
				</td> 
			</tr> 
		</table>

		<input type="hidden" name="role" value="${user.role}" />
		
		
	</body>

</html>
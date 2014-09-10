<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" class="model.UserBean" scope="session"/>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
	</head>
	
	<body>
		<% if (!user.isLoggedIn()) { %>
		<h1>admin/admin</h1>
		<form method="POST" action="LoginServlet">  
			<input type="text" name="username" placeholder="username here..." /> <br />
			<input type="password" name="password" placeholder="and password here!" /> <br />
			Should we remember you? <input type="checkbox" name="remember" /> <br />
			<input type="submit" value="Log in!">
		</form>
		
		<% } else { %>
 	 	<p> Već ste se prijavili! <br>
  		[<a href="/ComputerShop/LoginServlet?logoff=true&username=${user.getUsername()}">Odjavi se</a>]
  		</p>
		
		<% } %>
		<p><a href="index.jsp">Nazad</a></p>
	</body>

</html>



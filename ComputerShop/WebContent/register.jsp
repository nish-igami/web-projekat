<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" class="model.UserBean" scope="session"/>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Register new user</title>
	</head>
	
	<body>
		<form method="POST" action="RegisterServlet"> 
			<input type="text" name="username" placeholder="username here..." /> <br />
			<input type="password" name="password" placeholder="and password here!" /> <br />
			<input type="password" name="password_conf" placeholder="enter password one more time" /> <br />
			<br />
			
			Tell us a few things about you : <br />
			<input type="text" name="first_name" placeholder="Like, your first name" /> <br />
			<input type="text" name="last_name" placeholder="..and last name" /> <br />
			<input type="text" name="phone" placeholder="Maybe your phone? " /> <br />
			<input type="text" name="email" placeholder="We'll need your email fo sho" /> <br />
			<input type="submit" value="Submit">
		</form>
	</body>
</html>
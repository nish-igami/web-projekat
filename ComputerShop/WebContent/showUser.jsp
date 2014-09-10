<%@ page import="model.Role"%>
<%@ page import="model.UserBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>

<jsp:useBean id="user" class="model.UserBean" scope="page"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	
	<body>
    	<jsp:setProperty name="user" property="role"      value="ADMIN" />
		<jsp:setProperty name="user" property="username"  value="nish" />
		<jsp:setProperty name="user" property="password"  value="nish1234" />
		<jsp:setProperty name="user" property="firstName" value="Milos" />
		<jsp:setProperty name="user" property="lastName"  value="Karanovic" />
		<jsp:setProperty name="user" property="phone"     value="066/555-999-0" />
		<jsp:setProperty name="user" property="email"     value="nish@test.com" />
        
        <p> Role:       <jsp:getProperty name="user" property="role"      /> </p>
		<p> Username:   <jsp:getProperty name="user" property="username"  /> </p>
		<p> Password:   <jsp:getProperty name="user" property="password"  /> </p>
		<p> First Name: <jsp:getProperty name="user" property="firstName" /> </p>
		<p> Last Name:  <jsp:getProperty name="user" property="lastName"  /> </p>
		<p> Phone:      <jsp:getProperty name="user" property="phone"     /> </p>
		<p> E-mail:     <jsp:getProperty name="user" property="email"     /> </p>
        
        <!--  <form name="beanTest" method="POST" action="showUser.jsp">
            Enter a name: <input type="text" name="name" size="50"><br/>
            Choose an option:
            <select name="deceased">
                <option value="false">Alive</option>
                <option value="true">Dead</option>
            </select>
            <input type="submit" value="Test the Bean">
        </form>   -->
	</body>
</html>
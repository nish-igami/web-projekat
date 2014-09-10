<%@page import="model.Role"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	</head>

	<body>
		
		Welcome :)
		
		<% if (user.getRole() == Role.ADMIN) { %>
		<br />
		
		<p> <a href="PrepareCreateComponentServlet" > -&gt; Add Component &lt;- </a>
		<p> <a href="PrepareComponentServlet?action=displayComponents" > -&gt; Display Components &lt;- </a>
		
		<p> <a href="PrepareCreateCategoryServlet" > -&gt; Add Category &lt;- </a>
		<p> <a href="MasterPrepareServlet?action=displayCategories" > -&gt; Display Categories &lt;- </a>
		
		<p> <a href="PrepareCreateDeviceServlet" > -&gt; Add Device &lt;- </a>
		<p> <a href="MasterPrepareServlet?action=displayDevices" > -&gt; Display Devices &lt;- </a>
		
		<p> <a href="MasterPrepareServlet?action=search" > -&gt; Search &lt;- </a>
		
		<p> <a href="" > -&gt; Generate Report for specific period &lt;- </a>
		<p> <a href="" > -&gt; Generate Report for specific categories &lt;- </a>
		
		<% } else if (user.getRole() == Role.SHOPER) { %>
		
		<p> <a href="PrepareComponentServlet?action=displayComponents" > -&gt; Display Components &lt;- </a>
		<p> <a href="MasterPrepareServlet?action=displayCategories" > -&gt; Display Categories &lt;- </a>
		<p> <a href="MasterPrepareServlet?action=displayDevices" > -&gt; Display Devices &lt;- </a>
		
		<p> <a href="MasterPrepareServlet?action=search" > -&gt; Search &lt;- </a>
		<p> <a href="MasterPrepareServlet?action=buy" > -&gt; Buy Component(s)/Device(s) &lt;- </a>
		<p> <a href="assembleDevice.jsp" > -&gt; Assemble Device &lt;- </a>
		
		<% } else { %>
		<p> <a href="login.jsp" > -&gt; Login &lt;- </a>
		<p> <a href="register.jsp" > -&gt; Register &lt;- </a>
		
		<% } %>
		<p><a href="">Nazad</a></p>
		
	</body>

</html>
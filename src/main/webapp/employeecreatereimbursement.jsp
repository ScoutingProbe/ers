<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Employee" %>
<% Employee employee = (Employee) request.getSession().getAttribute("employee"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee: reimbursement</title>
</head>
<body>
<div class='container'>
	<h2><%= employee.getUsername() %> create reimbursement</h2>
	<a href='employee-home.do'>Home</a>
	
	<form action='EmployeeReimbursementServlet' enctype='multipart/form-data' method='post'>
		<input type='radio' name='category' value='lodging'>
		<label for='lodging'>lodging</label>
		
		<input type='radio' name='category' value='travel'>
		<label for='travel'>travel</label>
		
		<input type='radio' name='category' value='food'>
		<label for='food'>food</label>
		
		<input type='radio' name='category' value='other'>
		<label for='other'>other</label>
		
		<input type='file' name='image' value='image'>
		<input type='submit' value='submit'>
	</form>
</div>
</body>
</html>
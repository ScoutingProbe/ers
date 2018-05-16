<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.Manager" %>
<% Manager manager = (Manager) request.getSession().getAttribute("manager"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager: create reimbursement</title>
</head>
<body>
<div class='container'>	
	<h2><%= manager.getUsername() %> create reimbursement</h2>
	<a href='manager-home.do'>Home</a>
	
	<form action='ManagerReimbursementServlet' enctype='multipart/form-data' method='post'>
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
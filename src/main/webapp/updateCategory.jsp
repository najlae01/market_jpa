<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="category?action=updated" method="post">

		<input type="number" value="${id}" name="id" hidden="true"> <br />
		
		<label>Name :</label> <input type="text" value="${oldName}" name="name"> <br />

		<label>Description :</label> <input type="text" value="${oldDescription}" name="description"> <br />
		<input type="submit" name="save" value="Submit"> <br />
	</form>
</body>
</html>
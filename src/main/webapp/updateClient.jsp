<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="client?action=updated" method="post">

		<input type="number" value="${id}" name="id" hidden="true"> <br />
		
		<label>Name :</label> <input type="text" value="${oldName}" name="name"> <br />

		<label>Address :</label> <input type="text" value="${oldAddress}" name="address"> <br />
		<label>Phone :</label> <input type="text" value="${oldPhone}" name="phone"> <br />
		<label>City :</label> <input type="text" value="${oldCity}" name="city"> <br />
		<input type="submit" name="save" value="Add"> <br />
	</form>
</body>
</html>
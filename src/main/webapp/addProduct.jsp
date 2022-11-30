<%@page import="java.util.List"%>
<%@page import="fstt.org.market.entities.persistence.Category"%>
<%@page import="java.util.ListIterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	List<Category> list = (List<Category>) request.getAttribute("list");
	%>
	<form action="product?action=saved" method="post">

		<label>Name :</label> <input type="text" name="name"> <br />

		<label>Description :</label> <input type="text" name="description">
		<br /> <label>Price :</label> <input type="number" name="price">
		<br /> <label>Quantity In Stock :</label> <input type="number"
			name="quantity"> <br /> <label>Category Name :</label> <select
			name="category" id="category">
			<%
			for (int i = 0; i < list.size(); i++) {
			%>
			<option value="<%=list.get(i).getCategoryId()%>"><%=list.get(i).getCategoryName()%></option>
			<%
			}
			%>
		</select> <input type="submit" name="save" value="Add"> <br />
	</form>
</body>
</html>
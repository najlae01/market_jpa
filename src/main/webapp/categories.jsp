<%@page import="java.util.List"%>
<%@page import="fstt.org.market.entities.persistence.Category"%>
<%@page import="java.util.ListIterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category Management</title>
</head>
<body>
	<%
	List<Category> list = (List<Category>) request.getAttribute("list");
	%>

	<table>
		<thead>
			<tr>
				<th>Id</th>

				<th>Name</th>

				<th>Description</th>

				<th>Products</th>

				<th>Delete</th>

				<th>Update</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < list.size(); i++) {
			%>
			<tr>

				<td><%=list.get(i).getCategoryName()%></td>
				<td><%=list.get(i).getCategoryDescription()%></td>
				<td><a href="product?action=list&id=<%=list.get(i).getCategoryId()%>">
						View products </a></td>
				<td><a href="category?action=delete&id=<%=list.get(i).getCategoryId()%>">
						delete </a></td>
				<td><a href="category?action=update&id=<%=list.get(i).getCategoryId()%>">
						update </a></td>

			</tr>

			<%
			}
			%>
		</tbody>
	</table>
</body>
</html>
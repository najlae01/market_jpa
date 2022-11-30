<%@page import="java.util.List"%>
<%@page import="fstt.org.market.entities.persistence.Product"%>
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
	List<Product> list = (List<Product>) request.getAttribute("list");
	%>

	<table>
		<thead>
			<tr>
				<th>Id</th>

				<th>Name</th>

				<th>Description</th>

				<th>Price</th>

				<th>Quantity</th>
				
				<th>Category</th>

				<th>Delete</th>

				<th>Update</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < list.size(); i++) {
			%>
			<tr>

				<td><%=list.get(i).getProductId()%></td>
				<td><%=list.get(i).getProductName()%></td>
				<td><%=list.get(i).getProductDescription()%></td>
				<td><%=list.get(i).getProductPrice()%></td>
				<td><%=list.get(i).getProductStockQuantity()%></td>
				<td><%=list.get(i).getProductCategory().getCategoryName()%></td>
				<td><a href="product?action=delete&id=<%=list.get(i).getProductId()%>">
						delete </a></td>
				<td><a href="product?action=update&id=<%=list.get(i).getProductId()%>">
						update </a></td>

			</tr>

			<%
			}
			%>
		</tbody>
	</table>
</body>
</html>
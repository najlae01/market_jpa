<%@page import="java.util.List"%>
<%@page import="fstt.org.market.entities.persistence.OrderC"%>
<%@page import="fstt.org.market.entities.persistence.Orderline"%>
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
	List<OrderC> list = (List<OrderC>) request.getAttribute("list");
	%>

	<table>
		<thead>
			<tr>
				<th>Id</th>

				<th>Date</th>

				<th>Client</th>

				<th>OrderLines</th>

				<th>Delete</th>

				<th>Update</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < list.size(); i++) {
			%>
			<tr>

				<td><%=list.get(i).getOrderId()%></td>
				<td><%=list.get(i).getOrderDate()%></td>
				<td><%=list.get(i).getClient().getClientName()%></td>
				
				<td><a href="order?action=delete&id=<%=list.get(i).getOrderId()%>">
						delete </a></td>
				<td><a href="order?action=update&id=<%=list.get(i).getOrderId()%>">
						update </a></td>

			</tr>

			<%
			}
			%>
		</tbody>
	</table>
</body>
</html>
<%@page import="java.util.List"%>
<%@page import="fstt.org.market.entities.persistence.Product"%>
<%@page import="fstt.org.market.entities.persistence.OrderC"%>
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
	int oldProductId = Integer.parseInt(request.getAttribute("oldProductId").toString());
	String oldProductName = request.getAttribute("oldProductName").toString();
	
	int oldOrderId = Integer.parseInt(request.getAttribute("oldOrderId").toString());
	
	List<Product> products = (List<Product>) request.getAttribute("products");
	List<OrderC> orders = (List<OrderC>) request.getAttribute("orders");
	%>
	<form action="orderline?action=updated" method="post">
		<input type="number" value="${id}" name="id" hidden="true"> <br />

		<label>Quantity :</label> <input value="${oldQuantity}" type="number" name="quantity">

		<label>Product Name :</label> <select name="product" id="product">
			<%
			for (int i = 0; i < products.size(); i++) {
			%>
			<%
			if (products.get(i).getProductId() == oldProductId) {
			%>
			<option selected value="<%=oldProductId%>"><%=oldProductName%></option>
			<%
			continue;
			}
			%>
			<option value="<%=products.get(i).getProductId()%>"><%=products.get(i).getProductName()%></option>
			<%
			}
			%>
		</select>
		
		<label>Order Id :</label> <select name="order" id="order">
			<%
			for (int i = 0; i < orders.size(); i++) {
			%>
			<%
			if (orders.get(i).getOrderId() == oldOrderId) {
			%>
			<option selected value="<%=oldOrderId%>"><%=oldOrderId%></option>
			<%
			continue;
			}
			%>
			<option value="<%=orders.get(i).getOrderId()%>"><%=orders.get(i).getOrderId()%></option>
			<%
			}
			%>
		</select>
		
		 <input type="submit" name="save"
			value="Add"> <br />
	</form>
</body>
</html>
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
	Long oldCategoryId = Long.parseLong(request.getAttribute("oldCategoryId").toString());
	String oldCategoryName = request.getAttribute("oldCategoryName").toString();
	List<Category> list = (List<Category>) request.getAttribute("list");
	%>
	<form action="product?action=updated" method="post">

		<input type="number" value="${id}" name="id" hidden="true"> <br />

		<label>Name :</label> <input type="text" value="${oldName}"
			name="name"> <br /> <label>Description :</label> <input
			type="text" value="${oldDescription}" name="description"> <br />
		<label>Price :</label> <input type="number" value="${oldPrice}"
			name="price"> <br /> <label>Quantity :</label> <input
			type="number" value="${oldQuantity}" name="quantity"> <br />
		<label>Category :</label> <select name="category" id="category">
			<%
			for (int i = 0; i < list.size(); i++) {
			%>
			<%
			if (list.get(i).getCategoryId() == oldCategoryId) {
			%>
			<option selected value="<%=oldCategoryId%>"><%=oldCategoryName%></option>
			<%
			continue;
			}
			%>
			<option value="<%=list.get(i).getCategoryId()%>"><%=list.get(i).getCategoryName()%></option>
			<%
			}
			%>
		</select> <input type="submit" name="save" value="Save"> <br />
	</form>
</body>
</html>
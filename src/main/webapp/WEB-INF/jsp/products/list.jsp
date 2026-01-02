<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Products List</title>
</head>
<body>
<!-- integration du menu -->
<jsp:include page="../welcome.jsp" />

<div class="container">
	<table class="table">
		<thead>
		<tr>
			<th>Ref</th>
			<th>Name</th>
			<th>Stock</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${productsList}" var="product">
			<tr>
				<td>${product.ref}</td>
				<td>${product.name}</td>
				<td>${product.stock}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

<div class="container">
	<form action="produit" method="post">
		<div class="mb-3">
			<label for="inputRef" class="form-label">Reference</label>
			<input type="text" name="ref" class="form-control" id="inputRef" required>
		</div>
		<div class="mb-3">
			<label for="inputName" class="form-label">Name</label>
			<input type="text" name="name" class="form-control" id="inputName" required>
		</div>
		<div class="mb-3">
			<label for="inputStock" class="form-label">Stock</label>
			<input type="number" step="0.01" name="stock" class="form-control" id="inputStock" required>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>
</body>
</html>

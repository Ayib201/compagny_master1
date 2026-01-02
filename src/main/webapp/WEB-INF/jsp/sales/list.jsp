<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Purchase List</title>
</head>
<body>
<!-- integration du menu -->
<jsp:include page="../welcome.jsp" />

<div class="container">
	<table class="table">
		<thead>
		<tr>
			<th>Date Peremption</th>
			<th>Quantité</th>
			<th>Produit</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${salesList}" var="sale">
			<tr>
				<td>${sale.dateP}</td>
				<td>${sale.quantity}</td>
				<td>${sale.product_ref}</td> <!-- Assure-toi que product a une propriété name -->
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

<div class="container">
	<form action="sale" method="post">
		<div class="mb-3">
			<label for="inputDateP" class="form-label">Date Peremption</label>
			<input type="date" name="dateP" class="form-control" id="inputDateP" required>
		</div>

		<div class="mb-3">
			<label for="inputQuantity" class="form-label">Quantité</label>
			<input type="number" name="quantity" class="form-control" id="inputQuantity" required min="1" step="1">
		</div>

		<div class="mb-3">
			<label for="selectProduct" class="form-label">Produit</label>
			<select name="product_ref" id="selectProduct" class="form-control" required>
				<option value="" disabled selected>Choisissez un produit</option>
				<c:forEach items="${productsList}" var="product">
					<option value="${product.ref}">${product.name} (${product.ref})</option>
				</c:forEach>
			</select>
		</div>

		<button type="submit" class="btn btn-primary">Enregistrer la vente</button>
	</form>
</div>
</body>
</html>

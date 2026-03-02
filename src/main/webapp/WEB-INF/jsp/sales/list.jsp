<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sales List</title>
</head>
<body class="bg-light">

<div class="d-flex">

	<!-- SIDEBAR -->
	<jsp:include page="../welcome.jsp" />

	<!-- CONTENU PRINCIPAL -->
	<div class="flex-grow-1 p-4" style="margin-left:260px;">

		<h4 class="fw-bold mb-4">Sales</h4>

		<!-- TABLE -->
		<div class="card border-0 shadow-sm rounded-3 mb-4">
			<div class="card-body">
				<table class="table table-hover mb-0">
					<thead class="table-dark">
					<tr>
						<th>Date Péremption</th>
						<th>Quantité</th>
						<th>Produit</th>
						<th class="text-center">Actions</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${salesList}" var="sale">
						<tr>
							<td>${sale.dateP}</td>
							<td>${sale.quantity}</td>
							<td>${sale.product_ref}</td>
							<td class="text-center">
								<!-- Edit -->
								<a href="sale?action=edit&id=${sale.id}"
								   class="btn btn-sm btn-outline-warning me-1">
									<i class="bi bi-pencil"></i> Edit
								</a>
								<!-- Delete -->
								<a href="sale?action=delete&id=${sale.id}"
								   class="btn btn-sm btn-outline-danger"
								   onclick="return confirm('Confirmer la suppression ?')">
									<i class="bi bi-trash"></i> Delete
								</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!-- FORM -->
		<div class="card border-0 shadow-sm rounded-3">
			<div class="card-header bg-white fw-semibold border-0 pt-3">
				<i class="bi bi-plus-circle me-2 text-primary"></i>Enregistrer une vente
			</div>
			<div class="card-body">
				<form action="sale" method="post">
					<div class="row g-3">
						<div class="col-md-4">
							<label for="inputDateP" class="form-label">Date Péremption</label>
							<input type="date" name="dateP" class="form-control" id="inputDateP" required>
						</div>
						<div class="col-md-4">
							<label for="inputQuantity" class="form-label">Quantité</label>
							<input type="number" name="quantity" class="form-control" id="inputQuantity" required min="1" step="1">
						</div>
						<div class="col-md-4">
							<label for="selectProduct" class="form-label">Produit</label>
							<select name="product_ref" id="selectProduct" class="form-select" required>
								<option value="" disabled selected>Choisissez un produit</option>
								<c:forEach items="${productsList}" var="product">
									<option value="${product.ref}">${product.name} (${product.ref})</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-12">
							<button type="submit" class="btn btn-primary">
								<i class="bi bi-save me-1"></i> Enregistrer la vente
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>
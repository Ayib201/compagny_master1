<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Products List</title>
</head>
<body class="bg-light">

<div class="d-flex">

	<!-- SIDEBAR -->
	<jsp:include page="../welcome.jsp" />

	<!-- CONTENU PRINCIPAL -->
	<div class="flex-grow-1 p-4" style="margin-left:260px;">

		<h4 class="fw-bold mb-4">Products</h4>

		<!-- TABLE -->
		<div class="card border-0 shadow-sm rounded-3 mb-4">
			<div class="card-body">
				<table class="table table-hover mb-0">
					<thead class="table-dark">
					<tr>
						<th>Ref</th>
						<th>Name</th>
						<th>Stock</th>
						<th class="text-center">Actions</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${productsList}" var="product">
						<tr>
							<td>${product.ref}</td>
							<td>${product.name}</td>
							<td>${product.stock}</td>
							<td class="text-center">
								<a href="produit?action=edit&ref=${product.ref}"
								   class="btn btn-sm btn-outline-warning me-1">
									<i class="bi bi-pencil"></i> Edit
								</a>
								<a href="produit?action=delete&ref=${product.ref}"
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
				<i class="bi bi-plus-circle me-2 text-primary"></i>Ajouter un produit
			</div>
			<div class="card-body">
				<form action="produit" method="post">
					<div class="row g-3">
						<div class="col-md-4">
							<label for="inputRef" class="form-label">Reference</label>
							<input type="text" name="ref" class="form-control" id="inputRef" required>
						</div>
						<div class="col-md-4">
							<label for="inputName" class="form-label">Name</label>
							<input type="text" name="name" class="form-control" id="inputName" required>
						</div>
						<div class="col-md-4">
							<label for="inputStock" class="form-label">Stock</label>
							<input type="number" step="0.01" name="stock" class="form-control" id="inputStock" required>
						</div>
						<div class="col-12">
							<button type="submit" class="btn btn-primary">
								<i class="bi bi-save me-1"></i> Submit
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
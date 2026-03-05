<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Products List</title>
</head>
<body class="bg-light">

<div class="d-flex">

	<jsp:include page="../welcome.jsp" />

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
						<th>User</th>
						<th class="text-center">Actions</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${productsList}" var="product">
						<tr>
							<td>${fn:escapeXml(product.ref)}</td>
							<td>${fn:escapeXml(product.name)}</td>
							<td>${product.stock}</td>
							<td>${product.userEmail}</td>
							<td class="text-center">

								<!-- Edit -->
								<a href="produit?action=edit&ref=${fn:escapeXml(product.ref)}"
								   class="btn btn-sm btn-outline-warning me-1">
									<i class="bi bi-pencil"></i> Edit
								</a>

								<!-- Delete -->
								<form action="produit" method="post" class="d-inline"
									  onsubmit="return confirm('Confirmer la suppression de ${fn:escapeXml(product.ref)} ?')">
									<input type="hidden" name="action" value="delete">
									<input type="hidden" name="ref" value="${fn:escapeXml(product.ref)}">
									<button type="submit" class="btn btn-sm btn-outline-danger">
										<i class="bi bi-trash"></i> Delete
									</button>
								</form>

							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!-- FORM ADD / EDIT -->
		<div class="card border-0 shadow-sm rounded-3">
			<div class="card-header bg-white fw-semibold border-0 pt-3">
				<c:choose>
					<c:when test="${editProduct != null}">
						<i class="bi bi-pencil-square me-2 text-warning"></i>Edit Product
					</c:when>
					<c:otherwise>
						<i class="bi bi-plus-circle me-2 text-primary"></i>Ajouter un produit
					</c:otherwise>
				</c:choose>
			</div>
			<div class="card-body">
				<form action="produit" method="post">

					<c:if test="${editProduct != null}">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="ref" value="${fn:escapeXml(editProduct.ref)}">
					</c:if>

					<div class="row g-3">
						<div class="col-md-4">
							<label for="inputRef" class="form-label">Reference</label>
							<input type="text" name="ref" class="form-control" id="inputRef"
								   value="${fn:escapeXml(editProduct.ref)}"
							${editProduct != null ? 'readonly' : ''} required>
						</div>
						<div class="col-md-4">
							<label for="inputName" class="form-label">Name</label>
							<input type="text" name="name" class="form-control" id="inputName"
								   value="${fn:escapeXml(editProduct.name)}" required>
						</div>
						<div class="col-md-4">
							<label for="inputStock" class="form-label">Stock</label>
							<input type="number" step="1" name="stock" class="form-control" id="inputStock"
								   value="${editProduct != null ? editProduct.stock : ''}" required>
						</div>

						<c:if test="${not empty errorMessage}">
							<div class="col-12">
								<div class="alert alert-danger py-2 mb-0">${errorMessage}</div>
							</div>
						</c:if>
						<!-- après le champ stock, avant le errorMessage -->
						<div class="col-md-4">
							<label for="selectUser" class="form-label">Utilisateur</label>
							<select name="email" id="selectUser" class="form-select" required>
								<option value="">Choisissez un utilisateur</option>
								<c:forEach items="${usersList}" var="user">
									<option value="${fn:escapeXml(user.email)}"
										${editProduct != null && editProduct.userEmail eq user.email ? 'selected' : ''}>
											${fn:escapeXml(user.firstName)} ${fn:escapeXml(user.lastName)}
										(${fn:escapeXml(user.email)})
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-12 d-flex gap-2">
							<button type="submit" class="btn ${editProduct != null ? 'btn-warning' : 'btn-primary'}">
								<i class="bi ${editProduct != null ? 'bi-save' : 'bi-plus-circle'} me-1"></i>
								${editProduct != null ? 'Update' : 'Submit'}
							</button>
							<c:if test="${editProduct != null}">
								<a href="produit" class="btn btn-secondary">
									<i class="bi bi-x-circle me-1"></i>Cancel
								</a>
							</c:if>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
</div>

</body>
</html>
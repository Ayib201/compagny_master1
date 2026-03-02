<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sales List</title>
</head>
<body class="bg-light">
<div class="d-flex">

	<jsp:include page="../welcome.jsp" />

	<div class="flex-grow-1 p-4" style="margin-left:260px;">

		<h4 class="fw-bold mb-4">Sales</h4>

		<div class="card border-0 shadow-sm rounded-3 mb-4">
			<div class="card-body">
				<table class="table table-hover mb-0">
					<thead class="table-dark">
					<tr>
						<th>ID</th>
						<th>Date Péremption</th>
						<th>Quantité</th>
						<th>Produit</th>
						<th class="text-center">Actions</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${salesList}" var="sale">
						<tr>
							<td>${sale.id}</td>
							<td>${sale.dateP}</td>
							<td>${sale.quantity}</td>
							<td>${fn:escapeXml(sale.product_ref)}</td>
							<td class="text-center">
								<a href="sale?action=edit&id=${sale.id}"
								   class="btn btn-sm btn-outline-warning me-1">
									<i class="bi bi-pencil"></i> Edit
								</a>
								<form action="sale" method="post" class="d-inline"
									  onsubmit="return confirm('Confirmer la suppression ?')">
									<input type="hidden" name="action" value="delete">
									<input type="hidden" name="id" value="${sale.id}">
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

		<div class="card border-0 shadow-sm rounded-3">
			<div class="card-header bg-white fw-semibold border-0 pt-3">
				<c:choose>
					<c:when test="${editSale != null}">
						<i class="bi bi-pencil-square me-2 text-warning"></i>Edit Sale
					</c:when>
					<c:otherwise>
						<i class="bi bi-plus-circle me-2 text-primary"></i>Enregistrer une vente
					</c:otherwise>
				</c:choose>
			</div>
			<div class="card-body">
				<form action="sale" method="post">

					<c:if test="${editSale != null}">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="id" value="${editSale.id}">
					</c:if>

					<div class="row g-3">
						<div class="col-md-4">
							<label for="inputDateP" class="form-label">Date Péremption</label>
							<input type="date" name="dateP" class="form-control" id="inputDateP"
								   value="${editSale != null ? editSale.dateP : ''}" required>
						</div>
						<div class="col-md-4">
							<label for="inputQuantity" class="form-label">Quantité</label>
							<input type="number" name="quantity" class="form-control" id="inputQuantity"
								   value="${editSale != null ? editSale.quantity : ''}"
								   required min="1" step="1">
						</div>
						<div class="col-md-4">
							<label for="selectProduct" class="form-label">Produit</label>
							<select name="product_ref" id="selectProduct" class="form-select" required>
								<option value="">Choisissez un produit</option>
								<c:forEach items="${productsList}" var="product">
									<option value="${fn:escapeXml(product.ref)}"
										${editSale != null && editSale.product_ref == product.name ? 'selected' : ''}>
											${fn:escapeXml(product.name)} (${fn:escapeXml(product.ref)})
									</option>
								</c:forEach>
							</select>
						</div>

						<c:if test="${not empty errorMessage}">
							<div class="col-12">
								<div class="alert alert-danger py-2 mb-0">${errorMessage}</div>
							</div>
						</c:if>

						<div class="col-12 d-flex gap-2">
							<button type="submit" class="btn ${editSale != null ? 'btn-warning' : 'btn-primary'}">
								<i class="bi ${editSale != null ? 'bi-save' : 'bi-plus-circle'} me-1"></i>
								${editSale != null ? 'Update' : 'Enregistrer la vente'}
							</button>
							<c:if test="${editSale != null}">
								<a href="sale" class="btn btn-secondary">
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
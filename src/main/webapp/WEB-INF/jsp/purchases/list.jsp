<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Purchase List</title>
</head>
<body class="bg-light">
<div class="d-flex">

	<jsp:include page="../welcome.jsp" />

	<div class="flex-grow-1 p-4" style="margin-left:260px;">

		<h4 class="fw-bold mb-4">Purchases</h4>

		<!-- TABLE LIST -->
		<div class="card border-0 shadow-sm rounded-3 mb-4">
			<div class="card-body">
				<table class="table table-hover mb-0">
					<thead class="table-dark">
					<tr>
						<th>ID</th>
						<th>Date Péremption</th>
						<th>Quantité</th>
						<th>Produit</th>
						<th>User</th>
						<th class="text-center">Actions</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${purchasesList}" var="purchase">
						<tr>
							<td>${purchase.id}</td>
							<td>${purchase.dateP}</td>
							<td>${purchase.quantity}</td>
							<td>${fn:escapeXml(purchase.productRef)}</td>
							<td>${fn:escapeXml(purchase.userEmail)}</td>
							<td class="text-center">
								<a href="purchase?action=edit&id=${purchase.id}"
								   class="btn btn-sm btn-outline-warning me-1">
									<i class="bi bi-pencil"></i> Edit
								</a>

								<form action="purchase" method="post" class="d-inline"
									  onsubmit="return confirm('Confirmer la suppression ?')">
									<input type="hidden" name="action" value="delete">
									<input type="hidden" name="id" value="${purchase.id}">
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

		<!-- FORM -->
		<div class="card border-0 shadow-sm rounded-3">
			<div class="card-header bg-white fw-semibold border-0 pt-3">
				<c:choose>
					<c:when test="${editPurchase != null}">
						<i class="bi bi-pencil-square me-2 text-warning"></i>Edit Purchase
					</c:when>
					<c:otherwise>
						<i class="bi bi-plus-circle me-2 text-primary"></i>Enregistrer un achat
					</c:otherwise>
				</c:choose>
			</div>

			<div class="card-body">
				<form action="purchase" method="post">

					<c:if test="${editPurchase != null}">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="id" value="${editPurchase.id}">
					</c:if>

					<div class="row g-3">

						<!-- DATE -->
						<div class="col-md-4">
							<label for="inputDateP" class="form-label">Date Péremption</label>
							<input type="date"
								   name="dateP"
								   class="form-control"
								   id="inputDateP"
								   value="${editPurchase != null ? editPurchase.dateP : ''}"
								   required>
						</div>

						<!-- QUANTITY -->
						<div class="col-md-4">
							<label for="inputQuantity" class="form-label">Quantité</label>
							<input type="number"
								   name="quantity"
								   class="form-control"
								   id="inputQuantity"
								   value="${editPurchase != null ? editPurchase.quantity : ''}"
								   required
								   min="1"
								   step="1">
						</div>

						<!-- PRODUCT -->
						<div class="col-md-4">
							<label for="selectProduct" class="form-label">Produit ${editPurchase.productRef}</label>
							<select name="product_ref"
									id="selectProduct"
									class="form-select"
									required>
								<option value="">Choisissez un produit</option>
								<c:forEach items="${productsList}" var="product">
									<option value="${fn:escapeXml(product.ref)}"
										${editPurchase != null && editPurchase.productRef eq product.name ? 'selected' : ''}>
											${fn:escapeXml(product.name)} (${fn:escapeXml(product.ref)})
									</option>
								</c:forEach>

							</select>
						</div>

						<!-- ERROR MESSAGE -->
						<c:if test="${not empty errorMessage}">
							<div class="col-12">
								<div class="alert alert-danger py-2 mb-0">
										${errorMessage}
								</div>
							</div>
						</c:if>
						<!-- après le select produit, avant le errorMessage -->
						<div class="col-md-4">
							<label for="selectUser" class="form-label">Utilisateur</label>
							<select name="email" id="selectUser" class="form-select" required>
								<option value="">Choisissez un utilisateur</option>
								<c:forEach items="${usersList}" var="user">
									<option value="${fn:escapeXml(user.email)}"
										${editPurchase != null && editPurchase.userEmail eq user.email ? 'selected' : ''}>
											${fn:escapeXml(user.firstName)} ${fn:escapeXml(user.lastName)}
										(${fn:escapeXml(user.email)})
									</option>
								</c:forEach>
							</select>
						</div>
						<!-- BUTTONS -->
						<div class="col-12 d-flex gap-2">

							<button type="submit"
									class="btn ${editPurchase != null ? 'btn-warning' : 'btn-primary'}">
								<i class="bi ${editPurchase != null ? 'bi-save' : 'bi-plus-circle'} me-1"></i>
								${editPurchase != null ? 'Update' : 'Enregistrer l\'achat'}
							</button>

							<c:if test="${editPurchase != null}">
								<a href="purchase" class="btn btn-secondary">
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
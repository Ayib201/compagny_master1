<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Users List</title>
</head>
<body class="bg-light">

<div class="d-flex">

	<jsp:include page="../welcome.jsp" />

	<div class="flex-grow-1 p-4" style="margin-left:260px;">

		<h4 class="fw-bold mb-4">Users</h4>

		<!-- TABLE -->
		<div class="card border-0 shadow-sm rounded-3 mb-4">
			<div class="card-body">
				<table class="table table-hover mb-0">
					<thead class="table-dark">
					<tr>
						<th>Email</th>
						<th>First name</th>
						<th>Last name</th>
						<th class="text-center">Actions</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${usersList}" var="user">
						<tr>
							<td>${fn:escapeXml(user.email)}</td>
							<td>${fn:escapeXml(user.firstName)}</td>
							<td>${fn:escapeXml(user.lastName)}</td>
							<td class="text-center">

								<!-- Edit : GET avec email encodé -->
								<a href="admin?action=edit&id=${fn:escapeXml(user.email)}"
								   class="btn btn-sm btn-outline-warning me-1">
									<i class="bi bi-pencil"></i> Edit
								</a>

								<!-- Delete : POST pour éviter les problèmes d'URL -->
								<form action="admin" method="post" class="d-inline"
									  onsubmit="return confirm('Confirmer la suppression de ${fn:escapeXml(user.email)} ?')">
									<input type="hidden" name="action" value="delete">
									<input type="hidden" name="id" value="${fn:escapeXml(user.email)}">
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
					<c:when test="${editUser != null}">
						<i class="bi bi-pencil-square me-2 text-warning"></i>Edit User
					</c:when>
					<c:otherwise>
						<i class="bi bi-person-plus me-2 text-primary"></i>Add User
					</c:otherwise>
				</c:choose>
			</div>
			<div class="card-body">
				<form action="admin" method="post">

					<c:if test="${editUser != null}">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="id" value="${fn:escapeXml(editUser.email)}">
					</c:if>

					<div class="row g-3">
						<div class="col-md-6">
							<label for="inputFirstName" class="form-label">First name</label>
							<input type="text" name="firstName" class="form-control" id="inputFirstName"
								   value="${fn:escapeXml(editUser.firstName)}" required>
						</div>
						<div class="col-md-6">
							<label for="inputLastName" class="form-label">Last name</label>
							<input type="text" name="lastName" class="form-control" id="inputLastName"
								   value="${fn:escapeXml(editUser.lastName)}" required>
						</div>
						<div class="col-md-6">
							<label for="inputEmail" class="form-label">Email address</label>
							<input type="email" name="email" class="form-control" id="inputEmail"
								   value="${fn:escapeXml(editUser.email)}" required>
						</div>
						<div class="col-md-6">
							<label for="inputPassword" class="form-label">Password</label>
							<input type="password" name="password" class="form-control" id="inputPassword" required>
						</div>
						<div class="col-12 d-flex gap-2">
							<button type="submit" class="btn ${editUser != null ? 'btn-warning' : 'btn-primary'}">
								<i class="bi ${editUser != null ? 'bi-save' : 'bi-person-plus'} me-1"></i>
								${editUser != null ? 'Update' : 'Submit'}
							</button>
							<c:if test="${editUser != null}">
								<a href="admin" class="btn btn-secondary">
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
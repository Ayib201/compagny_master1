<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Users List</title>
</head>
<body class="bg-light">

<div class="d-flex">

	<!-- SIDEBAR -->
	<jsp:include page="../welcome.jsp" />

	<!-- CONTENU PRINCIPAL -->
	<div class="flex-grow-1 p-4" style="margin-left:260px;">

		<h4 class="fw-bold mb-4">Users</h4>

		<div class="card border-0 shadow-sm rounded-3 mb-4">
			<div class="card-body">
				<table class="table table-hover mb-0">
					<thead class="table-dark">
					<tr>
						<th>Email</th>
						<th>First name</th>
						<th>Last name</th>
						<th>Actions</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${usersList}" var="user">
						<tr>
							<td>${user.email}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td class="text-center">
								<!-- Edit -->
								<a href="admin?action=edit&id=${user.email}"
								   class="btn btn-sm btn-outline-warning me-1">
									<i class="bi bi-pencil"></i> Edit
								</a>
								<!-- Delete -->
								<a href="admin?action=delete&id=${user.email}"
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

		<div class="card border-0 shadow-sm rounded-3">
			<div class="card-header bg-white fw-semibold border-0 pt-3">Add User</div>
			<div class="card-body">
				<form action="admin" method="post">
					<div class="row g-3">
						<div class="col-md-6">
							<label for="inputFirstName" class="form-label">First name</label>
							<input type="text" name="firstName" class="form-control" id="inputFirstName" required>
						</div>
						<div class="col-md-6">
							<label for="inputLastName" class="form-label">Last name</label>
							<input type="text" name="lastName" class="form-control" id="inputLastName" required>
						</div>
						<div class="col-md-6">
							<label for="inputEmail" class="form-label">Email address</label>
							<input type="email" name="email" class="form-control" id="inputEmail" required>
						</div>
						<div class="col-md-6">
							<label for="inputPassword" class="form-label">Password</label>
							<input type="password" name="password" class="form-control" id="inputPassword" required>
						</div>
						<div class="col-12">
							<button type="submit" class="btn btn-primary">
								<i class="bi bi-person-plus me-1"></i> Submit
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
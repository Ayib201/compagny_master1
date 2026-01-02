<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Users List</title>
</head>
<body>
<!-- integration du menu -->
<jsp:include page="../welcome.jsp" />

<div class="container">
	<table class="table">
		<thead>
		<tr>
			<th>ID</th>
			<th>First name</th>
			<th>Last name</th>
			<th>Email</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${usersList}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.email}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

<div class="container">
	<form action="admin" method="post">
		<div class="mb-3">
			<label for="inputFirstName" class="form-label">First name</label>
			<input type="text" name="firstName" class="form-control" id="inputFirstName" required>
		</div>
		<div class="mb-3">
			<label for="inputLastName" class="form-label">Last name</label>
			<input type="text" name="lastName" class="form-control" id="inputLastName" required>
		</div>
		<div class="mb-3">
			<label for="inputEmail" class="form-label">Email address</label>
			<input type="email" name="email" class="form-control" id="inputEmail" required>
		</div>
		<div class="mb-3">
			<label for="inputPassword" class="form-label">Password</label>
			<input type="password" name="password" class="form-control" id="inputPassword" required>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Person List</title>
</head>
<body>
<script type="text/javascript">
	function deleteConfirm(name) {
		if (confirm("Do you want to remove?") == true)
			location.href = "DeletePersonAction.do?name=" + name;
		else
			return;
	}
</script>

	mode : ${mode}
	
	<jsp:include page="header.jsp" />

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">Person List</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row" align="center">
			<c:forEach var="person" items="${personList}">
				<div class="col-md-4">
					<h3> ${person.getName()} </h3>
					<p> ${person.getAge()}
					<p> ${person.getGender()}
					<c:if test="${mode == null}">
					<p><a href="DetailViewPersonAction.do?name=${person.getName()}" class="btn btn-secondary" role="button"> Detail &raquo;</a>
					</c:if>
					<c:if test="${mode eq 'delete'}">
					<p><a href="#" onclick="deleteConfirm('${person.getName()}')" class="btn btn-danger" role="button"> Delete &raquo;</a>
					</c:if>
					<c:if test="${mode eq 'edit'}">
					<p><a href="EditViewPersonAction.do?name=${person.getName()}" class="btn btn-success" role="button"> Edit &raquo;</a>
					</c:if>
				</div>
			</c:forEach>
		</div>
		<hr>
	</div>
</body>
</html>
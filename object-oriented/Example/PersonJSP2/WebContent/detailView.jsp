<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ page errorPage="exceptionNotFound.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Person Detail Information</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">Person Detail Information</h1>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h3> ${person.getName()} </h3>
				<h4> ${person.getAge()} </h4>
				<h4> ${person.getGender()} </h4>
			</div>
		</div>
	</div>
</body>
</html>
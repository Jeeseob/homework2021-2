<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Menu</title>
</head>
<body>
<nav class="navbar navbar-expand navbar-dark bg-dark">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="index.jsp">Home</a>
		</div>
		<p><a href="AddViewPersonAction.do" class="btn btn-secondary" role="button"> Add &raquo;</a>
		<p><a href="ListViewPersonAction.do?mode=edit" class="btn btn-secondary" role="button"> Edit &raquo;</a>
		<p><a href="ListViewPersonAction.do?mode=delete" class="btn btn-secondary" role="button"> Delete &raquo;</a>
	</div>
</nav>
</body>
</html>
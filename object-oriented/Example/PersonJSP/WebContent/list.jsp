<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ page import="dto.Person" %>
<%@ page import="dao.PersonManager" %>
<%@ page import="java.util.List" %>

<%
	PersonManager dao = PersonManager.getInstance();
	List<Person> pList = dao.getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Person List</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">Person List</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row" align="center">
			<%
				for (Person p : pList) {
			%>
				<div class="col-md-4">
					<h3><%= p.getName() %></h3>
					<p><%= p.getAge() %>
					<p><%= p.getGender() %>
					<p><a href="detailView.jsp?name=<%= p.getName() %>" class="btn btn-secondary" role="button"> Detail &raquo;</a>
				</div>
			<%
				}
			%>
		</div>
		<hr>
	</div>
</body>
</html>
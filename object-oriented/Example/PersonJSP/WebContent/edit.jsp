<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ page import="dto.Person" %>
<%@ page import="dao.PersonManager" %>
<%@ page import="java.util.List" %>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Person Edit</title>
<script type="text/javascript">
	function deleteConfirm(name) {
		if (confirm("Do you want to remove?") == true)
			location.href = "./delete.jsp?name=" + name;
		else
			return;
	}
</script>
</head>
<body>
<%
	String edit = request.getParameter("edit");
%>
	<jsp:include page="header.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">Person Edit</h1>
		</div>
	</div>
	<div class="container">
		<div class="row" align="center">
			<%
				PersonManager dao = PersonManager.getInstance();
				List<Person> pList = dao.getList();
			%>
			<%
				for (Person p : pList) {
			%>
			<div class="col-md-4">
					<h3><%= p.getName() %></h3>
					<p><%= p.getAge() %>
					<p><%= p.getGender() %>

				<p><%
						if (edit.equals("update")) {
					%>
					<a href="update.jsp?name=<%= p.getName() %>" class="btn btn-success" role="button"> Edit &raquo;></a>
					<%
						} else if (edit.equals("delete")) {
					%>
					<a href="#" onclick="deleteConfirm('<%= p.getName() %>')" class="btn btn-danger" role="button"> Delete &raquo;></a>
					<%
						}
					%>				
			</div>
			<%
				}
			%>	
		</div>
		<hr>
	</div>
</body>
</html>
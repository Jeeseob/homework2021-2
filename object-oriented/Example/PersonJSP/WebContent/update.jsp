<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="dto.Person"%>
<%@ page import="dao.PersonManager"%>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />
<title>Person Edit</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">Person Edit</h1>
		</div>
	</div>
	<%
		String name = request.getParameter("name");
		PersonManager dao = PersonManager.getInstance();
		Person person = dao.get(name);
	%>
	
	<div class="container">
		<div class="row">
			<div class="col-md-7">
				<form name="updatePerson" action="processUpdate.jsp" method="post">
					<div class="form-group row">
						<label class="col-sm-2">Person Name</label>
						<div class="col-sm-3">
							<input type="text" name="name" class="form-control" value='<%= person.getName() %>'>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2">Person Age</label>
						<div class="col-sm-3">
							<input type="text" name="age" class="form-control" value="<%= person.getAge() %>">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2">Person Gender</label>
						<div class="col-sm-3">
							<input type="text" name="gender" class="form-control" value="<%= person.getGender() %>">
						</div>
					</div>
					<div class="form-group row">
						<div class="col-sm-offset-2 col-sm-10 ">
							<input type="submit" class="btn btn-primary" value="Submit">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="dto.Gender"%>
<%@ page import="dto.Person"%>
<%@ page import="dao.PersonManager"%>

<%
	String name = request.getParameter("name");
	String ageStr = request.getParameter("age");
	String genderStr = request.getParameter("gender");

	int age;
	if (ageStr.isEmpty())
		age = 0;
	else
		age = Integer.valueOf(ageStr);

	Gender gender;
	if (genderStr.isEmpty())
		gender = null;
	else
		gender = Gender.valueOf(genderStr);

	Person aPerson = new Person();
	aPerson.setName(name);
	aPerson.setAge(age);
	aPerson.setGender(gender);
	
	PersonManager dao = PersonManager.getInstance();
	dao.replace(name, aPerson);
	
	response.sendRedirect("edit.jsp?edit=update");
%>
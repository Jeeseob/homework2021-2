<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="dto.Person"%>
<%@ page import="dao.PersonManager"%>

<%
	String name = request.getParameter("name");

PersonManager dao = PersonManager.getInstance();
	Person person = dao.get(name);
	if (person != null) {
		dao.remove(person);
	} 
	else {
		out.println("Not found");	
	}
	
	response.sendRedirect("edit.jsp?edit=delete");
%>

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.AddPersonAction;
import action.DeletePersonAction;
import action.EditPersonAction;
import action.ListPersonAction;
import action.FindPersonAction; 

public class PersonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		Action action = null;
		if (command.equals("/ListViewPersonAction.do")) { // list view
			action = new ListPersonAction(); // return personList w/ mode
			action.execute(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("listView.jsp");
			rd.forward(request, response);
		} else if (command.equals("/DetailViewPersonAction.do")) { // detail view
			action = new FindPersonAction(); // return person
			action.execute(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("detailView.jsp");
			rd.forward(request, response);
		} else if (command.equals("/AddViewPersonAction.do")) { // add view
			//RequestDispatcher rd = request.getRequestDispatcher("addView.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("updateView.jsp?mode=add");
			rd.forward(request, response);
		} else if (command.equals("/EditViewPersonAction.do")) { // edit view
			action = new FindPersonAction(); // return person
			action.execute(request, response);
			//RequestDispatcher rd = request.getRequestDispatcher("editView.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("updateView.jsp?mode=edit");
			rd.forward(request, response);
		} else if (command.equals("/AddPersonAction.do")) { // add
			action = new AddPersonAction(); // add person
			action.execute(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("/ListViewPersonAction.do");
			rd.forward(request, response);	
		} else if (command.equals("/EditPersonAction.do")) { // edit
			action = new EditPersonAction(); // edit person
			action.execute(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("/ListViewPersonAction.do?mode=edit");
			rd.forward(request, response);
		} else if (command.equals("/DeletePersonAction.do")) { // delete
			action = new DeletePersonAction(); // delete person
			action.execute(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("/ListViewPersonAction.do?mode=delete");
			rd.forward(request, response);
		} 
	}

}

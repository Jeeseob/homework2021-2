package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonManager;
import dto.Person;

public class FindPersonAction implements Action {
	   public void execute(HttpServletRequest request, HttpServletResponse response) {
			String name = request.getParameter("name");
			
	        PersonManager service = PersonManager.getInstance();       
	        Person person = service.get(name);
	        if (person == null){
		   		System.out.println("Person Not found");	
		   	}
	        request.setAttribute("person", person);
	        //List<Person> pList = service.getList();
	        //request.setAttribute("personList", pList);		

	        System.out.println("FindPersonAction");	
	    }
}

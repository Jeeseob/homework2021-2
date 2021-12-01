package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonManager;
import dto.Person;

public class DeletePersonAction implements Action {
	   public void execute(HttpServletRequest request, HttpServletResponse response) {
			String name = request.getParameter("name");
			
	        PersonManager service = PersonManager.getInstance();       
	        Person person = service.get(name);
	    	if (person != null) {
	    		service.remove(person);
	    	} 
	    	else {
	    		System.out.println("Person Not found");	
	    	}   	
	        System.out.println("Sucess Delete Person");
	    }
}

package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonManager;
import dto.Gender;
import dto.Person;

public class EditPersonAction implements Action {

	   public void execute(HttpServletRequest request, HttpServletResponse response) {
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
			
	        PersonManager service = PersonManager.getInstance();       
	        service.replace(name, aPerson);
	        
	        System.out.println("Sucess Edit Person");
	    }
}

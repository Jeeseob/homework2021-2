package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonManager;
import dto.Person;
import dto.Gender;

public class AddPersonAction implements Action {
	   public void execute(HttpServletRequest request, HttpServletResponse response) {
			String name = request.getParameter("name");
			String ageStr = request.getParameter("age");
			String genderStr = request.getParameter("gender");
			int age = Integer.valueOf(ageStr);
			Gender gender = Gender.valueOf(genderStr);			
			Person aPerson = new Person();
			aPerson.setName(name);
			aPerson.setAge(age);
			aPerson.setGender(gender);

			PersonManager service = PersonManager.getInstance();
			service.add(aPerson);
	        System.out.println("Sucess Add Person");
	    }
}

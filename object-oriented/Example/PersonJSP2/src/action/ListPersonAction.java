package action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonManager;
import dto.Person;

public class ListPersonAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mode = request.getParameter("mode");

		PersonManager service = PersonManager.getInstance();       
        List<Person> pList = service.getList();      		
        request.setAttribute("personList", pList);		
        request.setAttribute("mode", mode);

        System.out.println("ListPersonAction mode=" + mode);
        System.out.println("pList.size()=" + pList.size());
	}
}

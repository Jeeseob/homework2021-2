package dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dto.Gender;
import dto.Person;

public class PersonManager {
	private List<Person> pList = new ArrayList<Person>();

	private static PersonManager instance = new PersonManager();
	public static PersonManager getInstance(){
		return instance;
	}
	
	public PersonManager() {
		pList.add(new Person("Kyoung", 10, Gender.FEMALE));
		pList.add(new Person("Shin", 20, Gender.FEMALE));
		pList.add(new Person("Park", 30, Gender.FEMALE));
	}
	
	public List<Person> getList() {
		return pList;
	}

	public void setList(List<Person> list) {
		this.pList = list;
	}
	
	// add 
	public void add(Person person) {
		pList.add(person);
	}
	
	// remove all 
	public void removeAll() {
		Iterator<Person> it = pList.iterator();
		while (it.hasNext()) {
		    Person p = it.next();
			it.remove();
		}
	}
	
	// remove 
	public void remove(Person other) {
		Iterator<Person> it = pList.iterator();
		while (it.hasNext()) {
		    Person p = it.next();
		    if (p == other) {
				it.remove();
		    }
		}
	}
	
	// get 
	public Person get(Person other) {
		Iterator<Person> it = pList.iterator();
		while (it.hasNext()) {
			Person p = it.next();
		    if (p == other) {
		    	return p;
		    }
		}
		return null;
	}

	// get (by name)
	public Person get(String name) {
		Person person = null;
		for (Person p : pList) {
			if (p != null && p.getName().equals(name)) {
				person = p;
				break;
			}
		}
		return person;
	}

	// replace
	public void replace(String name, Person other) {
		int index = pList.indexOf(get(name));
		pList.set(index, other);
	}

}

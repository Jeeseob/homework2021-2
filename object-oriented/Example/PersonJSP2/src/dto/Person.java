package dto;

public class Person implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private Gender gender;
    
    public Person() { 
    	this("K", 10, Gender.FEMALE); 
	}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.gender = Gender.FEMALE;
    }

    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void setName(String name) { 
		this.name = name; 
	}
    
	public void setAge(int age) { 
		this.age = age; 
	}
    
	public void setGender(Gender gender) { 
		this.gender = gender; 
	}
    
	public String getName() { 
		return name; 
	}

    public int getAge() { 
		return age; 
	}

    public Gender getGender() { 
		return gender; 
	}

    public void print() { 
		System.out.println(this); 
	}

    @Override public String toString() { 
		return "Person Name=" + name + " Age=" + age + " Gender=" + gender; 
	}
}

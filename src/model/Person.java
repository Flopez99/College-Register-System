package model;

public class Person {
	private Name name;
	private String id;
	private String password;
	private String phoneNumber;
	private static int idCounter = 1;

	public Person(Name name) {
		this.name = name;
		id = String.valueOf(idCounter++);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Name getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getId() {
		return String.format("%08d", Integer.parseInt(id));
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", id=" + id + "]";
	}

	public void show() {
		System.out.println("Name is: " + name + ", Id: " + getId());
	}
}

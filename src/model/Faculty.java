package model;

public class Faculty extends Person {
	
	private FacultyTitle title;
	private double salary;
	private MiniFacultyCourseBag miniFacultyCourseBag;
	
	public Faculty(Name name, FacultyTitle title) { // name, salary, title, phone
		super(name);
		this.title = title;
		this.salary = salary;
	}

	
	public MiniFacultyCourseBag getMiniFacultyCourseBag() {
		return miniFacultyCourseBag;
	}


	public FacultyTitle getTitle() {
		return title;
	}

	public void setTitle(FacultyTitle title) {
		this.title = title;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}


	@Override
	public String toString() {
		return "Faculty [getName() = " + getName() + ", ID =" + getId() +", title = " + title + ", salary = " + String.format("%6.2f",salary);
	}

	public void setMiniFacultyCourseBag(MiniFacultyCourseBag miniFacultyCourseBag) {
		this.miniFacultyCourseBag = miniFacultyCourseBag;
		
	}
}

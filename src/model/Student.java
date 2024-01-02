package model;

public class Student extends Person {
	Majors major;
	private double gpa;
	private MiniStudentCourseBag miniStudentCourseBag;

	public Student(Name name, Majors major) {
		super(name);
		this.major = major;
	}

	public Majors getMajor() {
		return major;
	}

	public MiniStudentCourseBag getMiniStudentCourseBag() {
		return miniStudentCourseBag;
	}

	public void setMiniStudentCourseBag(MiniStudentCourseBag miniStudentCourseBag) {
		this.miniStudentCourseBag = miniStudentCourseBag;
	}

	public void setMajor(Majors major) {
		this.major = major;
	}

	public double getGpa() {
		return gpa;
	}
	public void calculateGpa() {
		System.out.println("Calculated");
		this.gpa = miniStudentCourseBag.calculateGPA();

	}

	@Override
	public String toString() {
		return "Student [Name = " + getName() + ", ID =" + getId() + ", major = " + major + ", gpa = " + gpa + "]";
	}
}

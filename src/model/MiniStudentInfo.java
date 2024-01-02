package model;

public class MiniStudentInfo {
	
	private LetterGrade letterGrade;
	private StudentCourseStatus courseStatus;
	private String courseNumber;
	private MasterCourseBag masterCourseBag;
	
	public MiniStudentInfo(String courseNumber) {
		
		this.courseNumber = courseNumber;
		courseStatus = StudentCourseStatus.TO_TAKE;
		letterGrade = LetterGrade.NO_GRADE;

	}
	
	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	
	public StudentCourseStatus getCourseStatus() {
		return courseStatus;
	}
	
	public double convertLetterGrade() {
		double point;
		
		switch(letterGrade.toString()) {
		
		case "A": point = 4.0;break;
		case "B+": point = 3.5;break;
		case "B": point = 3.0; break;
		case "C+": point = 2.5; break;
		case "C": point = 2.0; break;
		case "D+": point = 1.5; break;
		case "D": point = 1.0; break;
		default: point = 0; break;
		
		}
		return point;
	}
	public void setCourseStatus(StudentCourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}

	public void setLetterGrade(LetterGrade letterGrade) {
		this.letterGrade = letterGrade;
	}
	
	public LetterGrade getLetterGrade() {
		return letterGrade;
	}

	@Override
	public String toString() {
		return  courseNumber+ "";
	}

	public double getCreditsByCourseNumber() {
		double credits = MasterCourseBag.searchByCourseNumber(courseNumber).getNumberOfCredits();
		
		return credits;
	}
}

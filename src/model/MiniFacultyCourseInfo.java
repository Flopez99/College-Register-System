package model;

public class MiniFacultyCourseInfo {
	private static String courseNumber;
	private FacultyCourseStatus courseStatus;
	
	public MiniFacultyCourseInfo(String courseNumber, FacultyCourseStatus courseStatus) {
		this.courseNumber = courseNumber;
		this.courseStatus = courseStatus;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public FacultyCourseStatus getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(FacultyCourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}
	
}

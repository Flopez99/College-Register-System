package model;

public class MiniStudentCourseBag {
	private MiniStudentInfo[] miniStudentInfo;
	private int nElems;

	public MiniStudentCourseBag(int maxElms) {
		miniStudentInfo = new MiniStudentInfo[maxElms];
		nElems = 0;
	}

	public double calculateGPA() {
		double gradePoint = 0;
		int totalCredits = 0;

		for (int i = 0; i < nElems; i++) {

			gradePoint += miniStudentInfo[i].getCreditsByCourseNumber() * miniStudentInfo[i].convertLetterGrade();
			totalCredits += miniStudentInfo[i].getCreditsByCourseNumber();
		}

		return gradePoint / totalCredits;

	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(miniStudentInfo[i]);
		}
		System.out.println();
	}

	public MiniStudentInfo removeByCourseNumber(String id) {
		int i;
		for (i = 0; i < nElems; i++) {
			if (miniStudentInfo[i].getCourseNumber().contentEquals(id)) {
				break;
			}
		}

		if (i == nElems) {
			return null;
		} else {
			MiniStudentInfo temp = miniStudentInfo[i];
			for (int j = i; j < nElems - 1; j++) {
				miniStudentInfo[j] = miniStudentInfo[j + 1];
			}
			nElems--;
			return temp;
		}
	}

	public MiniStudentInfo[] getMiniStudentInfo() {
		return miniStudentInfo;
	}

	public void setMiniStudentInfo(MiniStudentInfo[] miniStudentInfo) {
		this.miniStudentInfo = miniStudentInfo;
	}

	public void insert(MiniStudentInfo newCourse) {
		miniStudentInfo[nElems++] = newCourse;
		System.out.println("Inserted " + nElems);
	}

	public MiniStudentInfo findByCourseNumber(String courseNumber) {
		for (int i = 0; i < nElems; i++) {
			if (miniStudentInfo[i].getCourseNumber().contentEquals(courseNumber)) {
				return miniStudentInfo[i];
			}
		}
		return null;
	}

	public int getnElems() {
		return nElems;
	}
	
	
}

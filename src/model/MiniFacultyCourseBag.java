package model;

public class MiniFacultyCourseBag {
	private MiniFacultyCourseInfo[] miniFacultyCourseInfo;
	private int nElems;

	public MiniFacultyCourseBag(int maxElms) {
		miniFacultyCourseInfo = new MiniFacultyCourseInfo[maxElms];
		nElems = 0;
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(miniFacultyCourseInfo[i]);
		}
		System.out.println();
	}

	public void insertByCourseNumber(MiniFacultyCourseInfo newCourse) {
		miniFacultyCourseInfo[nElems++] = newCourse;
	}

	public MiniFacultyCourseInfo findById(String courseNumber) {
		for (int i = 0; i < nElems; i++) {
			if (miniFacultyCourseInfo[i].getCourseNumber().contentEquals(courseNumber)) {
				return miniFacultyCourseInfo[i];
			}
		}
		return null;
	}

	public MiniFacultyCourseInfo[] getMiniFacultyCourseInfo() {
		return miniFacultyCourseInfo;
	}

	public void setMiniFacultyCourseInfo(MiniFacultyCourseInfo[] miniFacultyCourseInfo) {
		this.miniFacultyCourseInfo = miniFacultyCourseInfo;
	}

	public MiniFacultyCourseInfo[] getMiniFacultyInfo() {
		return null;
	}
}

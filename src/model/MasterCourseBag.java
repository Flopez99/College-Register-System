package model;

import java.io.Serializable;

public class MasterCourseBag implements Serializable  {
	private static Course[] courses;
	private static int nElems;
	
	public MasterCourseBag(int maxSize) {
		courses = new Course[maxSize];
		nElems = 0;
	}
	
	public void insert(Course course) {
		courses[nElems++] = course;
	}
	
	public void showCourses() {
		for(int i = 0; i < nElems; i++) {
			System.out.println(courses[i]);
		}
		System.out.println();
	}

	public Course removeByCourseNumber(String target) {
		int i;
		for (i = 0; i < nElems; i++) {
			if (courses[i].getCourseNumber().equals(target)) {
				break;
			}
		}
		if (i == nElems) {
			return null;
		} else {
			Course temp = courses[i];
			for (int j = i; j < nElems - 1; j++) {
				courses[j] = courses[j + 1];
			}
			nElems--;
			return temp;
		}
	}

	public static Course[] getCourses(){
		return courses;
	}
		
	public static Course searchByCourseNumber(String target) {
		for (int i = 0; i < nElems; i++) {
			if (courses[i].getCourseNumber().equals(target)) {
				return courses[i];
			}
		}
		return null;
	}
}

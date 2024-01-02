package model;

import java.io.Serializable;

public class ClassroomBag implements Serializable  {
	private  Classroom[] classroomArr;
	private int nElems;
	
	public ClassroomBag(int maxSize) {
		this.classroomArr = new Classroom[maxSize];
		nElems = 0;
	}
	public void insert(Classroom classroom) {
		classroomArr[nElems++] = classroom;
	}
	public Classroom findByClassNumber(String roomNumber) {
		for(int i = 0; i < nElems; i++) {
			if (classroomArr[i].getRoomNumber().contentEquals(roomNumber)) {
				return classroomArr[i];
			}
		}
		return null;
	}
	public Classroom removeByRoomNumber(String roomNumber) {
		int i;
		for(i = 0; i < nElems; i++) {
			if(classroomArr[i].getRoomNumber().contentEquals(roomNumber)) {
				break;
			}
		}
		if(i == nElems) {
			return null;
		}else {
			Classroom temp = classroomArr[i];
			for(int j = i; j < nElems - 1; j++) {
				classroomArr[i] = classroomArr[i+1];
			}
			nElems--;
			return temp;
		}
	}
	public void display() {
		for(int i = 0; i < nElems; i++) {
			System.out.println(classroomArr[i]);
		}
		System.out.println();
	}
	public Classroom[] getClassroomArr() {
		return classroomArr;
	}
	
}

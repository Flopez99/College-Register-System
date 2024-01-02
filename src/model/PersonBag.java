package model;

import java.io.Serializable;

public class PersonBag implements Serializable  {
	private Person[] personArr;
	private int nElems;
	
	public PersonBag(int maxSize) {
		this.personArr = new Person[maxSize];
		nElems = 0;
	}

	public void insert(Person person) {
		personArr[nElems++] = person;
	}
	public Person searchByID(String id) {
		System.out.println("Reached");
		for(int i = 0; i < nElems; i++) {
			if (personArr[i].getId().equals(id)) {
				return	personArr[i];
			}
		}
		return null;
	}
	public Person removeByID(String id) {
		int i;
		for(i = 0; i < nElems; i++) {
			if (personArr[i].getId().contentEquals(id)) {
				break;
			}
		}
		if(i == nElems) {
			return null;
		}else {
			Person temp =	personArr[i];
			for(int j = i; j < nElems - 1; j++) {
				personArr[i] =	personArr[i+1];
			}
			nElems--;
			return temp;
		}
	}
		public void display() {
			for(int i = 0; i < nElems; i++) {
				System.out.println(personArr[i]);
			}
			System.out.println();
	}

		public Person[] getPersonArr() {
			return personArr;
		}

		public void setPersonArr(Person[] personArr) {
			this.personArr = personArr;
		}
		
}

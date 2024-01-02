package model;

import java.io.Serializable;

public class College implements Serializable {

	private PersonBag personBag;
	private TextbookBag textbookBag;
	private MasterCourseBag courseBag;
	private ClassroomBag classroomBag;
	
	public College() {
		personBag = new PersonBag(CollegeIF.PERSONBAG_MAXSIZE);
		textbookBag = new TextbookBag(CollegeIF.TEXTBOOKBAG_MAXSIZE);
		courseBag = new MasterCourseBag(CollegeIF.COURSEBAG_MAXSIZE);
		classroomBag = new ClassroomBag(CollegeIF.CLASSROOMBAG_MAXSIZE);
	}

	
	public ClassroomBag getClassroomBag() {
		return classroomBag;
	}


	public void setClassroomBag(ClassroomBag classroomBag) {
		this.classroomBag = classroomBag;
	}


	public MasterCourseBag getCourseBag() {
		return courseBag;
	}

	public void setCourseBag(MasterCourseBag courseBag) {
		this.courseBag = courseBag;
	}

	public PersonBag getPersonBag() {
		return personBag;
	}

	public void setPersonBag(PersonBag personBag) {
		this.personBag = personBag;
	}

	public TextbookBag getTextbookBag() {
		return textbookBag;
	}

	public void setTextbookBag(TextbookBag textbookBag) {
		this.textbookBag = textbookBag;
	}
}

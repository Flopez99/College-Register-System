package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import model.Course;
import model.Faculty;
import model.FacultyTitle;
import model.Majors;
import model.Name;
import model.PersonBag;
import model.Student;

public class PeopleBagFiller {

	public static void fillWithFaculty(PersonBag theBag) throws FileNotFoundException {
		File personFile = new File("RawData/PeopleInfo/faculty.txt");
		Scanner scanner = new Scanner(personFile, "UTF-8");

		while (scanner.hasNextLine()) {
			String firstName = scanner.nextLine();
			String lastName = scanner.nextLine();
			String phoneNumber = scanner.nextLine();
			String password = scanner.nextLine();
			String title = scanner.nextLine();
			String salary = scanner.nextLine();
			Name name = new Name(firstName, lastName);

			Faculty faculty = new Faculty(name, FacultyTitle.valueOf(title));
			faculty.setPassword(password);
			faculty.setPhoneNumber(phoneNumber);
			faculty.setSalary(Double.valueOf(salary));
			theBag.insert(faculty);
		}

		scanner.close();
	}

	public static void fillWithStudents(PersonBag theBag) throws FileNotFoundException {
		File personFile = new File("RawData/PeopleInfo/students.txt");
		Scanner scanner = new Scanner(personFile, "UTF-8");

		while (scanner.hasNextLine()) {
			String firstName = scanner.nextLine();
			String lastName = scanner.nextLine();
			String phoneNumber = scanner.nextLine();
			String password = scanner.nextLine();
			String major = scanner.nextLine();
			scanner.nextLine();
			Name name = new Name(firstName, lastName);

			Student student = new Student(name, Majors.valueOf(major));
			student.setPassword(password);
			student.setPhoneNumber(phoneNumber);
			theBag.insert(student);
		}

		scanner.close();
	}

	public static void exportStudents(PersonBag theBag) throws IOException {
		Student[] arr = (Student[]) theBag.getPersonArr();
		PrintWriter pw = new PrintWriter("RawData/PeopleInfo/students.txt");
		for (int i = 0; i < arr.length; i++) {

			pw.write(arr[i].getName().getFirstName());
			pw.write(arr[i].getName().getLastName());
			pw.write(arr[i].getPhoneNumber());
			pw.write(arr[i].getPassword());
			pw.write(arr[i].getMajor() + "");
			pw.write("");
		}
		System.out.println("Done");
		pw.close();
	}

	public static void exportFaculty(PersonBag theBag) throws IOException {
		Faculty[] arr = (Faculty[]) theBag.getPersonArr();

		PrintWriter pw = new PrintWriter("RawData/PeopleInfo/faculty.txt");
		for (int i = 0; i < arr.length; i++) {

			pw.write(arr[i].getName().getFirstName());
			pw.write(arr[i].getName().getLastName());

			pw.write(arr[i].getPhoneNumber());
			pw.write(arr[i].getPassword());
			pw.write(arr[i].getTitle() + "");
			pw.write(arr[i].getSalary() + "");
			pw.write("");
		}
		System.out.println("Done");
		pw.close();
	}

}

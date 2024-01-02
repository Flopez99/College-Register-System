package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import model.Course;
import model.Majors;
import model.MasterCourseBag;
import model.MiniStudentCourseBag;
import model.MiniStudentInfo;

public class CourseBagFiller {

	public static void fillMasterCourseBag(MasterCourseBag theBag) throws FileNotFoundException {
		File courseFile = new File("RawData/courses.txt");
		Scanner scanner = new Scanner(courseFile, "UTF-8");

		while (scanner.hasNextLine()) {
			String courseNumber = scanner.nextLine();
			String courseTitle = scanner.nextLine();
			String description = scanner.nextLine();
			double credits = Double.parseDouble(scanner.nextLine());
			scanner.nextLine();
			Course course = new Course(courseNumber, courseTitle, description, credits);
			theBag.insert(course);
		}
		scanner.close();
	}

	public static MiniStudentCourseBag fillWithMajorCourses(Majors major) throws FileNotFoundException {
		MiniStudentCourseBag bag = new MiniStudentCourseBag(21);

		File file = new File("RawData/MajorCourses/" + major.toString() + ".txt");
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String courseNumber = scanner.nextLine();
			bag.insert(new MiniStudentInfo(courseNumber));
		}
		scanner.close();

		return bag;
	}

	public static void exportCourses(MasterCourseBag theBag) throws IOException {
		Course[] arr = theBag.getCourses();
		PrintWriter pw = new PrintWriter("RawData/CourseInfo/courses.txt");
		for (int i = 0; i < arr.length; i++) {

			pw.write(arr[i].getCourseNumber());
			pw.write(arr[i].getCourseTitle());
			pw.write(arr[i].getCourseDescription());
			pw.write(arr[i].getNumberOfCredits() + "");
			pw.write("");
		}
		pw.close();
		System.out.println("Done");
	}
}

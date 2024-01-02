package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import model.Building;
import model.Classroom;
import model.ClassroomBag;
public class ClassroomFiller {

	public static void fillClassrooms(ClassroomBag theBag) throws FileNotFoundException {
		File classroomFile = new File("RawData/Classrooms/classrooms.txt");
		Scanner scanner = new Scanner(classroomFile, "UTF-8");
		while (scanner.hasNextLine()) {
			String buildingName = scanner.nextLine();
			String classroomNumber = scanner.nextLine();
			scanner.nextLine();

			Classroom classroom = new Classroom(Building.valueOf(buildingName), classroomNumber);
			theBag.insert(classroom);
		}
		scanner.close();

	}

	public static void exportClassrooms(ClassroomBag theBag) throws IOException {
		Classroom[] arr = theBag.getClassroomArr();
		PrintWriter pw = new PrintWriter("RawData/Classrooms/classroom.txt");
		for (int i = 0; i < arr.length; i++) {

			pw.write(arr[i].getBuildingName().toString());
			pw.write(arr[i].getRoomNumber());
			pw.write("");
			System.out.println("Done");
		}
		pw.close();

	}
}

package border_pane;

import java.io.FileNotFoundException;
import java.io.IOException;

import helpers.ClassroomFiller;
import helpers.CourseBagFiller;
import helpers.PeopleBagFiller;
import helpers.TextbookBagFiller;
import helpers.WriteBinary;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import model.College;
import view.CoursePane;
import view.FacultyPane;
import view.StudentLoginPane;
import view.StudentPane;
import view.TextbookPane;

public class BorderMenu {
	private College college;

	private static BorderPane root;
	private static MenuBar menuBar;

	private Menu fileMenu;
	private Menu importMenu;
	private Menu exportMenu;
	private Menu editMenu;

	private static Text text;
	private MenuItem exitItem;
	private MenuItem backupItem;
	private MenuItem restoreItem;
	private MenuItem studentItem;
	private MenuItem facultyItem;
	private MenuItem courseItem;
	private MenuItem textbookItem;
	private MenuItem classroomItem;

	private MenuItem studentImportItem;
	private MenuItem facultyImportItem;
	private MenuItem textbookImportItem;
	private MenuItem courseImportItem;
	private MenuItem classroomImportItem;

	private MenuItem studentExportItem;
	private MenuItem facultyExportItem;
	private MenuItem textbookExportItem;
	private MenuItem courseExportItem;
	private MenuItem classroomExportItem;

	private static Pane studentPane;
	private Pane facultyPane;
	private Pane coursePane;
	private Pane textbookPane;

	private static HBox buttonBox;
	private static Button[] buttonArr;

	public BorderMenu(College college) {

		this.college = college;

		root = new BorderPane();
		menuBar = new MenuBar();
		fileMenu = new Menu("File");
		exitItem = new MenuItem("Exit");

		text = new Text("");
		text.setFont(Font.font("Verdana", FontPosture.REGULAR, 30));
		text.setFill(Color.BLACK);
		createImportMenu();
		createExportMenu();
		createEditMenu();

		Image img = new Image("file:OCCC3.jpg");
		ImageView imgView = new ImageView(img);
		root.setCenter(imgView);

		fileMenu.getItems().addAll(importMenu, exportMenu, new SeparatorMenuItem(), backupItem, restoreItem,
				new SeparatorMenuItem(), exitItem);

		handleEditEvent();
		handleFileEvent();

		menuBar.getMenus().addAll(fileMenu, editMenu);

		root.setTop(menuBar);

	}

	public void handleEditEvent() {
		buildButtonBox();
		buttonArr = Buttons.getBtnArray();

		studentItem.setOnAction(e -> {
			studentPane = new StudentPane(college).getPane();
			root.setCenter(studentPane);
			root.setBottom(buttonBox);

		});

		facultyItem.setOnAction(e -> {
			facultyPane = new FacultyPane(college).getPane();
			root.setCenter(facultyPane);
			root.setBottom(buttonBox);

		});
		courseItem.setOnAction(e -> {
			// coursePane = new CoursePane().getGridPane();
			root.setCenter(coursePane);
		});
		textbookItem.setOnAction(e -> {
			textbookPane = new TextbookPane(college).getGridPane();
			root.setCenter(textbookPane);
		});
	}

	public static void changeButtonBox() {
		buttonBox.getChildren().remove(0);
		buttonBox.getChildren().remove(0);
		buttonBox.getChildren().add(buttonArr[7]);
	}

	public static void buildButtonBox() {
		buttonBox = new Buttons().startButtonBox();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(0, 0, 50, 0));
	}

	public void handleFileEvent() {

		handleImportEvent();
		handleExportEvent();

		exitItem.setOnAction( q -> {
			try {
				WriteBinary.writeBinary(college);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		});
		backupItem.setOnAction(e -> {
			root.setCenter(text);
			try {
				WriteBinary.writeBinary(college);
				text.setText("Done Backing up!");
				root.setCenter(text);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		restoreItem.setOnAction(f -> {
			root.setCenter(text);

			try {
				WriteBinary.readBinary(college);
				text.setText("Done Restoring!");
				root.setCenter(text);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});
	}

	private void handleExportEvent() {

		textbookExportItem.setOnAction(e -> {
			root.setCenter(text);

			try {
				TextbookBagFiller.exportBag(college.getTextbookBag());
				text.setText("Exported Textbooks!");
				root.setCenter(text);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		courseExportItem.setOnAction(i -> {
			root.setCenter(text);

			try {
				CourseBagFiller.exportCourses(college.getCourseBag());
				text.setText("Exported Courses!");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			root.setCenter(text);

		});
		studentExportItem.setOnAction(f -> {
			root.setCenter(text);
			text.setText("Exported Students!");

			try {
				PeopleBagFiller.exportStudents(college.getPersonBag());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			root.setCenter(text);

		});
		facultyExportItem.setOnAction(a -> {
			root.setCenter(text);
			text.setText("Exported Faculty!");

			try {
				PeopleBagFiller.exportFaculty(college.getPersonBag());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			root.setCenter(text);

		});
		classroomExportItem.setOnAction(t -> {
			root.setCenter(text);
			text.setText("Exported Classrooms!");

			try {
				ClassroomFiller.exportClassrooms(college.getClassroomBag());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			root.setCenter(text);

		});
	}

	public void handleImportEvent() {

		textbookImportItem.setOnAction(e -> {
			root.setCenter(text);

			try {
				TextbookBagFiller.fillTextbookBag(college.getTextbookBag());
				text.setText("Imported Textbooks!");
				root.setCenter(text);
				college.getTextbookBag().sort();
				text.setText("Finished Sorting Textbooks!");
				root.setCenter(text);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		courseImportItem.setOnAction(f -> {
			root.setCenter(text);

			try {
				CourseBagFiller.fillMasterCourseBag(college.getCourseBag());
				text.setText("Imported Courses!");
				root.setCenter(text);
			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
			}
		});
		studentImportItem.setOnAction(i -> {
			root.setCenter(text);

			try {
				PeopleBagFiller.fillWithStudents(college.getPersonBag());
				text.setText("Done Importing Students!");
				root.setCenter(text);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		facultyImportItem.setOnAction(v -> {
			root.setCenter(text);

			try {
				PeopleBagFiller.fillWithFaculty(college.getPersonBag());
				text.setText("Done Importing Faculty!");
				root.setCenter(text);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});

		classroomImportItem.setOnAction(c -> {
			root.setCenter(text);
			try {
				ClassroomFiller.fillClassrooms(college.getClassroomBag());
				text.setText("Done Importing Classrooms!");
				root.setCenter(text);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
	}

	public void createEditMenu() {
		editMenu = new Menu("Edit");
		studentItem = new MenuItem("Student");
		facultyItem = new MenuItem("Faculty");
		courseItem = new MenuItem("Course");
		textbookItem = new MenuItem("Textbook");
		classroomItem = new MenuItem("Classroom");
		editMenu.getItems().addAll(studentItem, facultyItem, courseItem, textbookItem, classroomItem);
	}

	public void createExportMenu() {
		exportMenu = new Menu("Export");
		studentExportItem = new MenuItem("Student");
		facultyExportItem = new MenuItem("Faculty");
		textbookExportItem = new MenuItem("Textbook");
		courseExportItem = new MenuItem("Course");
		classroomExportItem = new MenuItem("Classroom");
		backupItem = new MenuItem("Backup");
		restoreItem = new MenuItem("Restore");

		exportMenu.getItems().addAll(studentExportItem, facultyExportItem, textbookExportItem, classroomExportItem,
				courseExportItem);
	}

	public void createImportMenu() {
		importMenu = new Menu("Import");
		studentImportItem = new MenuItem("Student");
		facultyImportItem = new MenuItem("Faculty");
		textbookImportItem = new MenuItem("Textbook");
		courseImportItem = new MenuItem("Course");
		classroomImportItem = new MenuItem("Classroom");

		importMenu.getItems().addAll(studentImportItem, facultyImportItem, textbookImportItem, classroomImportItem,
				courseImportItem);
	}

	public static void resetPane() {
		root.setTop(menuBar);
		root.setCenter(studentPane);
		buildButtonBox();
		root.setBottom(buttonBox);
	}

	public static void setCenter(Pane pane) {
		root.setCenter(pane);
	}

	public static Pane getPane() {
		return root;
	}

	public static void setBottom(Button btnArray) {
		btnArray.setAlignment(Pos.CENTER);
		btnArray.setPadding(new Insets(20));
		root.setBottom(btnArray);
	}
}

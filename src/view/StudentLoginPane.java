package view;

import java.io.FileNotFoundException;

import border_pane.BorderMenu;
import border_pane.Buttons;
import helpers.CourseBagFiller;
import helpers.Helper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import model.College;
import model.LetterGrade;
import model.Majors;
import model.MiniStudentInfo;
import model.Name;
import model.Person;
import model.Student;

public class StudentLoginPane {
	private College college;

	private Label myLabel;

	private ComboBox<String> gradeBox;

	private TextField firstNameField;
	private TextField lastNameField;
	private TextField phoneField;
	private PasswordField passwordField;
	private TextField idField;

	private Text text;
	private LetterGrade letterGrade;

	private VBox root;

	private Person person;
	private Student student;
	private GridPane loginGridPane;
	private GridPane studentGridPane;
	private MiniStudentInfo[] courseArr;

	private Button[] btnArray;

	public StudentLoginPane(College college) {
		btnArray = Buttons.getBtnArray();
		buildTextFields();
		buildHBoxes();
		buildLoginPane();

		buildVBox();
		this.college = college;
		handleEvent();
	}

	private void buildVBox() {
		System.out.println("Reached here");
		root = new VBox(25);
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(text);
		root.getChildren().add(loginGridPane);
	}

	public void handleEvent() {

		btnArray[0].setOnAction(e -> { // Insert
			BorderMenu.setCenter(new StudentPane(college).getPane());
		});

		btnArray[1].setOnAction(e -> { // Later implement errors pls

			college.getPersonBag().display();

			String id = idField.getText();
			String password = passwordField.getText();

			person = college.getPersonBag().searchByID(id);

			try {
				if (person.getPassword().equals(password)) {
					root.getChildren().remove(1);
					buildStudentGridPane();
					root.getChildren().add(1, studentGridPane);
					text.setText("Welcome " + person.getName());
					handleStudentEvents();
					BorderMenu.setBottom(btnArray[7]);
					
				}

			} catch (NullPointerException a) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Authentication Error");
				alert.setHeaderText("User Login Error");
				alert.setContentText("ID# or Password Is Wrong, Try Again");
				alert.showAndWait();
			}

		});

	}

	private void handleStudentEvents() {
		student = (Student) person;

		btnArray[2].setOnAction(e -> {
			btnArray[2].getScene().setRoot(showInformation());

			btnArray[8].setOnAction(f -> {
				updateStudentInfo();
			});

			btnArray[6].setOnAction(i -> {
				btnArray[6].getScene().setRoot(BorderMenu.getPane());
			});
		});
		btnArray[7].setOnAction(j -> {
			Pane pane = new BorderMenu(college).getPane();
			btnArray[7].getScene().setRoot(pane);

		});

		btnArray[5].setOnAction(l -> {
			Pane textPane = new TextbookPane(college).getGridPane();

			btnArray[5].getScene().setRoot(textPane);

			btnArray[6].setOnAction(i -> {
				btnArray[6].getScene().setRoot(BorderMenu.getPane());
			});
		});
		btnArray[3].setOnAction(c -> {
			Pane coursePane = null;
			try {
				coursePane = new CoursePane(CourseBagFiller.fillWithMajorCourses(student.getMajor()), student)
						.getGridPane();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

			btnArray[3].getScene().setRoot(coursePane);

			btnArray[6].setOnAction(i -> {
				btnArray[6].getScene().setRoot(BorderMenu.getPane());
			});
		});
		btnArray[4].setOnAction(q -> {
			btnArray[4].getScene().setRoot(showGrades());

			gradeBox.setOnAction(v -> {
				for (int i = 0; i < student.getMiniStudentCourseBag().getnElems(); i++) {
					letterGrade = (LetterGrade.valueOf(gradeBox.getSelectionModel().getSelectedItem()));

					student.getMiniStudentCourseBag().findByCourseNumber(courseArr[i].getCourseNumber())
							.setLetterGrade(letterGrade);

					System.out.println(student.getMiniStudentCourseBag()
							.findByCourseNumber(courseArr[i].getCourseNumber()).getLetterGrade());
				}
			});

			btnArray[6].setOnAction(m -> {
				btnArray[6].getScene().setRoot(BorderMenu.getPane());
				student.calculateGpa();
			});
		});
	}

	private VBox showGrades() {
		VBox root = new VBox(20);
		Label courseLabel = null;
		courseArr = student.getMiniStudentCourseBag().getMiniStudentInfo();
		GridPane gridPane = new GridPane();
		gradeBox = null;

		String[] letterGrades = LetterGrade.getLetterGrade(LetterGrade.class);

		for (int i = 0; i < letterGrades.length; i++) {
			letterGrades[i] = Helper.plusRemoval(letterGrades[i]);
		}

		ObservableList<String> list = FXCollections.observableArrayList(letterGrades);

		gridPane.setVgap(10);
		gridPane.setHgap(20);
		gridPane.setAlignment(Pos.CENTER);

		for (int i = 0; i < student.getMiniStudentCourseBag().getnElems(); i++) {
			courseLabel = new Label();
			gradeBox = new ComboBox();

			gradeBox.setMaxWidth(60);
			gradeBox.setItems(list);

			courseLabel.setText(courseArr[i].getCourseNumber());

			gridPane.add(courseLabel, 0, i);
			gridPane.add(gradeBox, 1, i);

			System.out.println(student.getMiniStudentCourseBag().findByCourseNumber(courseArr[i].getCourseNumber()));

		}

		gridPane.setAlignment(Pos.CENTER);
		root.getChildren().add(gridPane);
		root.getChildren().add(btnArray[6]);
		root.setAlignment(Pos.CENTER);

		return root;

	}

	private VBox showInformation() {
		VBox infoRoot = new VBox(30);
		HBox names = new HBox();
		HBox bBox = new HBox(10);

		buildStudentTextFields();

		names.getChildren().addAll(firstNameField, lastNameField);
		bBox.getChildren().addAll(btnArray[6], btnArray[8]);

		bBox.setAlignment(Pos.CENTER);

		Label name = new Label("1. Full Name: " + student.getName());
		Label id = new Label("2. ID Number: " + student.getId());
		Label phone = new Label("3. Phone Number: " + student.getPhoneNumber());
		Label password = new Label("4. Account Password: " + student.getPassword());
		Label major = new Label("5. Current Major: " + student.getMajor());
		Label gpa = new Label("6. GPA: " + String.format("%1.2f", student.getGpa()));

		GridPane infoPane = new GridPane();

		infoPane.setVgap(10);
		infoPane.setHgap(10);

		infoPane.add(name, 0, 0);
		infoPane.add(names, 0, 1);
		infoPane.add(id, 2, 0);
		infoPane.add(idField, 2, 1);
		infoPane.add(phone, 0, 2);
		infoPane.add(phoneField, 0, 3);
		infoPane.add(password, 2, 2);
		infoPane.add(passwordField, 2, 3);
		infoPane.add(major, 0, 4);
		infoPane.add(gpa, 2, 4);
		infoPane.add(bBox, 1, 6);
		infoPane.setGridLinesVisible(false);
		infoPane.setAlignment(Pos.CENTER);

		infoRoot.getChildren().add(infoPane);

		infoRoot.setAlignment(Pos.CENTER);

		return infoRoot;
	}

	private void buildStudentTextFields() {
		firstNameField = new TextField();
		lastNameField = new TextField();
		idField = new TextField();
		phoneField = new TextField();
		passwordField = new PasswordField();

		phoneField.setMaxWidth(100);
		passwordField.setMaxWidth(100);
		firstNameField.setMaxWidth(100);
		lastNameField.setMaxWidth(100);

		phoneField.setPromptText("New Phone #");
		passwordField.setPromptText("New Password");
		firstNameField.setPromptText("First Name");
		lastNameField.setPromptText("Last Name");
		idField.setPromptText("ID");

		firstNameField.setDisable(true);
		lastNameField.setDisable(true);
		phoneField.setDisable(true);
		passwordField.setDisable(true);
		idField.setDisable(true);
		idField.setDisable(true);
	}

	private void updateStudentInfo() {
		firstNameField.setDisable(false);
		lastNameField.setDisable(false);
		phoneField.setDisable(false);
		passwordField.setDisable(false);

		btnArray[8].setOnAction(e -> {

			Name name = new Name(firstNameField.getText(), lastNameField.getText());
			college.getPersonBag().searchByID(person.getId()).setName(name);
			college.getPersonBag().searchByID(person.getId()).setPhoneNumber(phoneField.getText());
			college.getPersonBag().searchByID(person.getId()).setPassword(passwordField.getText());

		});

	}

	private void buildStudentGridPane() {

		studentGridPane = new GridPane();

		studentGridPane.setVgap(20);
		studentGridPane.setHgap(20);

		studentGridPane.add(btnArray[2], 0, 1);
		studentGridPane.add(btnArray[3], 2, 1);
		studentGridPane.add(btnArray[4], 0, 2);
		studentGridPane.add(btnArray[5], 2, 2);
		studentGridPane.setPadding(new Insets(20, 0, 0, 0));
		studentGridPane.setAlignment(Pos.CENTER);

	}

	private void buildLoginPane() {
		loginGridPane = new GridPane();

		loginGridPane.setVgap(10);
		loginGridPane.setHgap(10);

		passwordField.setMaxWidth(500);

		loginGridPane.add(idField, 0, 0);
		loginGridPane.add(passwordField, 0, 2);
		loginGridPane.setPadding(new Insets(20, 0, 0, 0));
		loginGridPane.setAlignment(Pos.CENTER);

	}

	private void buildHBoxes() {

		text = new Text("Welcome To OCCC!");
		text.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
		text.setFill(Color.MEDIUMBLUE);

	}

	private void buildTextFields() {
		idField = new TextField();
		passwordField = new PasswordField();

		passwordField.setMaxWidth(100);

		passwordField.setPromptText("New Password");
		idField.setPromptText("ID");

		idField.setDisable(false);
	}

	public Pane getPane() {
		return root;
	}
}

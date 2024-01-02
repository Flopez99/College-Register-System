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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import model.College;
import model.Faculty;
import model.FacultyTitle;
import model.LetterGrade;
import model.MiniFacultyCourseInfo;
import model.MiniStudentInfo;
import model.Name;
import model.Person;
import model.Student;

public class FacultyLoginPane {
	private College college;

	private Label myLabel;

	private ComboBox<String> titleBox;

	private TextField firstNameField;
	private TextField lastNameField;
	private TextField phoneField;
	private PasswordField passwordField;
	private TextField idField;
	private TextField salaryField;
	
	private Text text;
	private FacultyTitle title;

	private VBox root;

	private Person person;
	private Faculty faculty;
	private GridPane loginGridPane;
	private GridPane facultyGridPane;
	private MiniFacultyCourseInfo[] courseArr;

	private Button[] btnArray;

	public FacultyLoginPane(College college) {
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
			BorderMenu.setCenter(new FacultyPane(college).getPane());
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
					root.getChildren().add(1, facultyGridPane);
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
		faculty = (Faculty) person;

		btnArray[2].setOnAction(e -> {
			btnArray[2].getScene().setRoot(showInformation());

			btnArray[8].setOnAction(f -> {
				updateFacultyInfo();
			});

			btnArray[6].setOnAction(i -> {
				btnArray[6].getScene().setRoot(BorderMenu.getPane());
			});
		});
		btnArray[7].setOnAction(j -> {
			BorderMenu.setCenter(new FacultyPane(college).getPane());
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
				coursePane = new FacultyCoursePane(faculty.getMiniFacultyCourseBag() , faculty).getGridPane();

			btnArray[3].getScene().setRoot(coursePane);

			btnArray[6].setOnAction(i -> {
				btnArray[6].getScene().setRoot(BorderMenu.getPane());
			});
		});
		btnArray[4].setOnAction(q -> {
			btnArray[4].getScene().setRoot(showGrades());
			
			btnArray[6].setOnAction(m -> {
				btnArray[6].getScene().setRoot(BorderMenu.getPane());
			});
		});
	}

	private VBox showGrades() {
//		VBox root = new VBox(20);
//		Label courseLabel = null;
//		courseArr = faculty.getMiniFacultyCourseBag().getMiniFacultyInfo();
//		GridPane gridPane = new GridPane();
//		title = null;
//
//		String[] letterGrades = LetterGrade.getLetterGrade(LetterGrade.class);
//
//		for (int i = 0; i < letterGrades.length; i++) {
//			letterGrades[i] = Helper.plusRemoval(letterGrades[i]);
//		}
//
//		ObservableList<String> list = FXCollections.observableArrayList(letterGrades);
//
//		gridPane.setVgap(10);
//		gridPane.setHgap(20);
//		gridPane.setAlignment(Pos.CENTER);
//
//		for (int i = 0; i < student.getMiniStudentCourseBag().getnElems(); i++) {
//			courseLabel = new Label();
//			gradeBox = new ComboBox();
//
//			gradeBox.setMaxWidth(60);
//			gradeBox.setItems(list);
//
//			courseLabel.setText(courseArr[i].getCourseNumber());
//
//			gridPane.add(courseLabel, 0, i);
//			gridPane.add(gradeBox, 1, i);
//
//			System.out.println(student.getMiniStudentCourseBag().findByCourseNumber(courseArr[i].getCourseNumber()));
//
//		}
//
//		gridPane.setAlignment(Pos.CENTER);
//		root.getChildren().add(gridPane);
//		root.getChildren().add(btnArray[6]);
//		root.setAlignment(Pos.CENTER);

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

		Label name = new Label("1. Full Name: " + faculty.getName());
		Label id = new Label("2. ID Number: " + faculty.getId());
		Label phone = new Label("3. Phone Number: " + faculty.getPhoneNumber());
		Label password = new Label("4. Account Password: " + faculty.getPassword());
		Label title = new Label("5. Title: " + faculty.getTitle());
		Label salary = new Label("6. Salary: " + faculty.getSalary());

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
		infoPane.add(title, 0, 4);
		infoPane.add(salary, 2, 4);
		infoPane.add(salaryField, 2, 5);
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
		salaryField = new TextField();
		
		phoneField.setMaxWidth(100);
		passwordField.setMaxWidth(100);
		firstNameField.setMaxWidth(100);
		lastNameField.setMaxWidth(100);
		salaryField.setMaxWidth(100);
		
		phoneField.setPromptText("New Phone #");
		passwordField.setPromptText("New Password");
		firstNameField.setPromptText("First Name");
		lastNameField.setPromptText("Last Name");
		idField.setPromptText("ID");
		salaryField.setPromptText("New Salary");
		
		firstNameField.setDisable(true);
		lastNameField.setDisable(true);
		phoneField.setDisable(true);
		passwordField.setDisable(true);
		salaryField.setDisable(true);
		idField.setDisable(true);
	}

	private void updateFacultyInfo() {
		firstNameField.setDisable(false);
		lastNameField.setDisable(false);
		phoneField.setDisable(false);
		passwordField.setDisable(false);
		salaryField.setDisable(false);

		btnArray[8].setOnAction(e -> {

			Name name = new Name(firstNameField.getText(), lastNameField.getText());
			college.getPersonBag().searchByID(person.getId()).setName(name);
			college.getPersonBag().searchByID(person.getId()).setPhoneNumber(phoneField.getText());
			college.getPersonBag().searchByID(person.getId()).setPassword(passwordField.getText());
			faculty.setSalary(Double.parseDouble(salaryField.getText()));
			
		});

	}

	private void buildStudentGridPane() {

		facultyGridPane = new GridPane();

		facultyGridPane.setVgap(20);
		facultyGridPane.setHgap(20);

		facultyGridPane.add(btnArray[2], 0, 1);
		facultyGridPane.add(btnArray[3], 2, 1);
		facultyGridPane.add(btnArray[4], 0, 2);
		facultyGridPane.add(btnArray[5], 2, 2);
		facultyGridPane.setPadding(new Insets(20, 0, 0, 0));
		facultyGridPane.setAlignment(Pos.CENTER);

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

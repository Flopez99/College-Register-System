package view;

import border_pane.BorderMenu;
import border_pane.Buttons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
import model.Majors;
import model.MiniFacultyCourseBag;
import model.MiniStudentCourseBag;
import model.Name;
import model.Student;

public class FacultyPane {
	private College college;

	private Label myLabel;

	private ComboBox<String> comboBox;

	private TextField firstNameField;
	private TextField lastNameField;
	private TextField idField;
	private TextField phoneField;
	private TextField salaryField;
	private PasswordField passwordField;

	private Text text;

	private FacultyTitle title;
	private VBox root;

	private HBox textFieldBox;
	private HBox labelBox;
	private HBox ePBox;

	private Faculty faculty;

	private GridPane signUpGridPane;

	private static Button[] btnArray;

	public FacultyPane(College college) {
		buildTextFields();
		buildHBoxes();
		buildSignUpPane();
		buildVBox();
		this.college = college;
		handleEvent();
	}

	private void buildVBox() {
		System.out.println("Reached here");
		root = new VBox(25);
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(text);
		root.getChildren().add(signUpGridPane);
		root.getChildren().add(salaryField);
		root.getChildren().add(comboBox);

	}

	public void handleEvent() {

		btnArray = Buttons.getBtnArray();

		btnArray[0].setOnAction(e -> { // Insert
			String fName = firstNameField.getText();
			String lName = lastNameField.getText();
			double salary = Double.parseDouble(salaryField.getText());
			
			title = (FacultyTitle.valueOf(comboBox.getSelectionModel().getSelectedItem()));

			Name name = new Name(fName, lName);
			faculty = new Faculty(name, title);
			
			faculty.setSalary(salary);
			faculty.setPassword(passwordField.getText());
			faculty.setPhoneNumber(phoneField.getText());
			faculty.setMiniFacultyCourseBag(new MiniFacultyCourseBag(21));
			
			college.getPersonBag().insert(faculty);
			firstNameField.clear();
			lastNameField.clear();
			passwordField.clear();
			phoneField.clear();
			salaryField.clear();
			System.out.println(faculty);
		});

		btnArray[1].setOnAction(g -> {
			BorderMenu.setCenter(new FacultyLoginPane(college).getPane());

		});

	}

	private void buildSignUpPane() {
		signUpGridPane = new GridPane();
		buildTitleComboBox();

		signUpGridPane.setVgap(10);
		signUpGridPane.setHgap(10);

		signUpGridPane.add(textFieldBox, 0, 0);
		signUpGridPane.add(ePBox, 0, 2);
		signUpGridPane.setPadding(new Insets(20, 0, 0, 0));
		signUpGridPane.setAlignment(Pos.CENTER);

	}

	private void buildTitleComboBox() {
		String[] titles = FacultyTitle.getTitles(FacultyTitle.class);

		ObservableList<String> list = FXCollections.observableArrayList(titles);
		comboBox = new ComboBox();
		comboBox.setValue("Title");
		comboBox.setItems(list);
	}

	private void buildHBoxes() {
		textFieldBox = new HBox(10);
		labelBox = new HBox(10);
		ePBox = new HBox(10);

		labelBox.setAlignment(Pos.TOP_CENTER);
		textFieldBox.setAlignment(Pos.CENTER);
		ePBox.setAlignment(Pos.CENTER);

		textFieldBox.getChildren().addAll(firstNameField, lastNameField);
		ePBox.getChildren().addAll(phoneField, passwordField);

		myLabel = new Label();

		text = new Text("Welcome To OCCC!");
		text.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
		text.setFill(Color.MEDIUMBLUE);

		labelBox.getChildren().add(myLabel);

	}

	private void buildTextFields() {
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
		
		phoneField.setPromptText("Phone Number");
		passwordField.setPromptText("New Password");
		firstNameField.setPromptText("First Name");
		lastNameField.setPromptText("Last Name");
		idField.setPromptText("ID");
		salaryField.setPromptText("Desired Salary");
		
		idField.setDisable(false);
	}

	public Pane getPane() {
		return root;
	}
}

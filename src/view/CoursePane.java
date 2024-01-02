package view;

import java.util.Arrays;

import border_pane.Buttons;
import helpers.CourseBagFiller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.College;
import model.Faculty;
import model.MiniStudentCourseBag;
import model.MiniStudentInfo;
import model.Name;
import model.Person;
import model.Student;
import model.StudentCourseStatus;

public class CoursePane {

	private static GridPane gridPane;

	private ColumnConstraints column1;
	private ColumnConstraints column2;
	private ColumnConstraints column3;

	private Label coursesToTakeLbl;
	private Label coursesTaking;
	
	private ObservableList<MiniStudentInfo> coursesToTakeList;
	private ObservableList<MiniStudentInfo> coursesTakingList;
	
	private ListView<MiniStudentInfo> coursesToTakeListView;
	private ListView<MiniStudentInfo> coursesTakingListView;
	
	private Button sendRightBtn;
	private Button sendLeftBtn;
	
	private StudentCourseStatus status;
	
	private Person person;
	private Student student;
	private MiniStudentCourseBag toTakeCourses;
	private MiniStudentCourseBag takingCourses;
	
	public CoursePane(MiniStudentCourseBag courseBag, Person person) {

		this.person = person;
		student = (Student)person;
		toTakeCourses = courseBag;
		makeGridPane();
		makeColumns();
		makeLabels();
		makeLists();
		handleEvents();
		makeVBox();

	}
	private void makeVBox() {
		Button []buttonArr = Buttons.getBtnArray();
		
		VBox vbox = new VBox(5);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(sendRightBtn, sendLeftBtn, buttonArr[6]);

		gridPane.add(vbox, 1, 1);
	}
	private void handleEvents() {
		takingCourses = new MiniStudentCourseBag(21);

		sendRightBtn = new Button("->");
		sendLeftBtn = new Button("<-");
		student.setMiniStudentCourseBag(new MiniStudentCourseBag(21));
		
		sendRightBtn.setOnAction(e -> {
			MiniStudentInfo potential = coursesToTakeListView.getSelectionModel().getSelectedItem();
			if (potential != null) {
				coursesToTakeListView.getSelectionModel().clearSelection();
				coursesToTakeList.remove(potential);
				coursesTakingList.add(potential);
				
				potential.setCourseStatus(status.TAKING);
				student.getMiniStudentCourseBag().insert(potential);
				toTakeCourses.removeByCourseNumber(potential.getCourseNumber());
					
			}
		});

		sendLeftBtn.setOnAction(e -> {
			MiniStudentInfo potential = coursesTakingListView.getSelectionModel().getSelectedItem();
			if (potential != null) {
				coursesTakingListView.getSelectionModel().clearSelection();
				coursesTakingList.remove(potential);
				coursesToTakeList.add(potential);
				
				potential.setCourseStatus(status.TAKEN);
				student.getMiniStudentCourseBag().removeByCourseNumber(potential.getCourseNumber());
				toTakeCourses.insert(potential);

			}
		});
	}
	public void makeLists() {
		
		coursesToTakeList = FXCollections.observableArrayList(Arrays.asList(toTakeCourses.getMiniStudentInfo()));
		coursesToTakeListView = new ListView<>(coursesToTakeList);
		gridPane.add(coursesToTakeListView, 0, 1);

		coursesTakingList = FXCollections.observableArrayList(Arrays.asList());
		coursesTakingListView = new ListView<>(coursesTakingList);
		gridPane.add(coursesTakingListView, 2, 1);		
		
	}
	public void makeLabels() {
		coursesToTakeLbl = new Label("Courses to Take");
		gridPane.setHalignment(coursesToTakeLbl, HPos.CENTER);
		gridPane.add(coursesToTakeLbl, 0, 0);

		coursesTaking = new Label("Courses Taking");
		gridPane.setHalignment(coursesTaking, HPos.CENTER);
		gridPane.add(coursesTaking, 2, 0);
	}
	
	public void makeGridPane() {
		gridPane = new GridPane();

		gridPane.setGridLinesVisible(false);
		gridPane.setPadding(new Insets(5));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
	}
	public void makeColumns() {
		column1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
		column2 = new ColumnConstraints(50);
		column3 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
		column1.setHgrow(Priority.ALWAYS);
		column3.setHgrow(Priority.ALWAYS);

		gridPane.getColumnConstraints().addAll(column1, column2, column3);
	}

	public static GridPane getGridPane() {
		return gridPane;
	}
	
	
}
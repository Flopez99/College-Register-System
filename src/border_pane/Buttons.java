package border_pane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Buttons {
	private HBox startButtonBox;
	private static Button signUpBtn;
	private static Button loginBtn;
	private static Button updateInfo;
	private static Button selectCourses;
	private static Button inputGrades;
	private static Button viewTextbooks;
	private static Button backButton;
	private static Button logoutButton;
	private static Button updateButton;
	
	public Buttons() {
		startButtonBox = new HBox(35);

		signUpBtn = new Button("SIGN UP");
		loginBtn = new Button("LOG IN");
		updateInfo = new Button("INFORMATION");
		selectCourses = new Button("PICK COURSES");
		inputGrades = new Button("INPUT GRADES");
		viewTextbooks = new Button("VIEW BOOKS");
		backButton = new Button("BACK");
		logoutButton = new Button("LOG OUT");
		updateButton = new Button("UPDATE");
		startButtonBox.setAlignment(Pos.CENTER);
		startButtonBox.getChildren().addAll(signUpBtn, loginBtn);
	}

	public HBox startButtonBox() {
		return startButtonBox;
	}

	public static Button[] getBtnArray() {
		Button[] btnArray = {signUpBtn, loginBtn, updateInfo, selectCourses, inputGrades, viewTextbooks, backButton, logoutButton, updateButton};
		return btnArray;
	}
}

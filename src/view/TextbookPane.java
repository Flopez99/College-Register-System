package view;

import java.util.Arrays;

import border_pane.Buttons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.College;
import model.Textbook;
import model.TextbookBag;

public class TextbookPane {
	private College college;

	private GridPane gridPane;

	private ColumnConstraints column1;
	private ColumnConstraints column3;

	private Label bookTitle;

	private TextField searchField;

	private Button searchBtn;
	private Button rentBtn;
	private Button backBtn;
	private Textbook foundBook;
	private ObservableList<Textbook> bookTitleList;

	private ListView<Textbook> bookTitleListView;

	public TextbookPane(College college) {

		this.college = college;
		makeGridPane();
		makeColumns();
		makeLabels();
		makeLists();
		handleEvents();
		makeVBox();

	}

	private void makeVBox() {
		VBox vbox = new VBox(10);
		buildTextFields();
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(searchField, searchBtn, rentBtn, backBtn);

		gridPane.add(vbox, 1, 1);
	}

	private void handleEvents() {
		searchBtn = new Button("SEARCH");
		rentBtn = new Button("RENT BOOK");
		Button[] btnArray = Buttons.getBtnArray();
		backBtn = btnArray[6];

		bookTitleListView.setOnMouseClicked(e -> {

			System.out.println(bookTitleList.get(bookTitleListView.getSelectionModel().getSelectedIndex()));

		});

		searchBtn.setOnAction(e -> {

			try {
				foundBook = college.getTextbookBag().findByISBN(searchField.getText());
			} catch (NullPointerException exception) {
				Alert missing = new Alert(AlertType.ERROR);
				missing.setTitle("Textbook Information");
				missing.setHeaderText("Missing Textbook Alert");
				missing.setContentText("We're Really Sorry, The Book You Searched For Is Missing");
				missing.showAndWait();
			}

			bookTitleListView.getSelectionModel().select(foundBook);
			System.out.println("Found " + foundBook);

			String formattedAuthors = Arrays.toString(foundBook.getAuthors()).replace("[", "").replace("]", "");

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Textbook Information");
			alert.setHeaderText(foundBook.getBookTitle());
			alert.setContentText(
					"Authors: " + formattedAuthors + "\n" + "Price: $" + String.format("%1.2f", foundBook.getPrice()));
			alert.showAndWait();

			rentBtn.setOnAction(f -> {
				searchField.clear();

				college.getTextbookBag().removeByISBN(foundBook.getIsbn());

			});

		});

	}

	private void buildTextFields() {
		searchField = new TextField();
		searchField.setMaxWidth(100);

		searchField.setPromptText("ISBN");
	}

	private void makeLists() {
		TextbookBag theBag = college.getTextbookBag();
		Textbook[] textbookArr = theBag.getTextbookArr();

		bookTitleList = FXCollections.observableArrayList(Arrays.asList(textbookArr));
		bookTitleListView = new ListView<>(bookTitleList);
		gridPane.add(bookTitleListView, 0, 1);

	}

	public void makeLabels() {
		bookTitle = new Label("-Textbooks-");

		gridPane.setHalignment(bookTitle, HPos.CENTER);
		gridPane.add(bookTitle, 0, 0);
	}

	public void makeGridPane() {
		gridPane = new GridPane();

		gridPane.setGridLinesVisible(false);
		gridPane.setPadding(new Insets(5));
		gridPane.setHgap(15);
		gridPane.setVgap(10);
	}

	public void makeColumns() {
		column1 = new ColumnConstraints(500, 300, Double.MAX_VALUE);
		column3 = new ColumnConstraints(130);
		column1.setHgrow(Priority.ALWAYS);
		column3.setHgrow(Priority.ALWAYS);

		gridPane.getColumnConstraints().addAll(column1, column3);
	}

	public GridPane getGridPane() {
		return gridPane;
	}
}

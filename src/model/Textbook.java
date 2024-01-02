package model;

public class Textbook {
	private String bookTitle;
	private String isbn;
	private Name[] authors;
	private double price;
	private String id;
	private static int idCounter = 1;

	public Textbook(String bookTitle, String isbn, Name[] authors, double price) {
		super();
		this.bookTitle = bookTitle;
		this.isbn = isbn;
		this.authors = authors;
		this.price = price;

		id = String.valueOf(idCounter++);
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Name[] getAuthors() {
		return authors;
	}

	public void setAuthors(Name[] authors) {
		this.authors = authors;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
//		return "Textbook [bookTitle=" + bookTitle + ", isbn=" + isbn + ", authors=" + Arrays.toString(authors)
//				+ ", price=" + String.format("%6.2f", price) + "]";
		return isbn + ":  " + bookTitle;
	}

}

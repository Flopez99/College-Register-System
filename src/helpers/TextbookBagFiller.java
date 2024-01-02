package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import model.Name;
import model.Textbook;
import model.TextbookBag;

public class TextbookBagFiller {
	
	public static void fillTextbookBag(TextbookBag theBag) throws FileNotFoundException {

		String textPath = "RawData/TextbookInfo/";
		
		File titleFile = new File(textPath + "titles.txt");
		File isbnFile = new File(textPath + "isbns.txt");
		Scanner scanner1 = new Scanner(titleFile, "UTF-8");
		Scanner scanner2 = new Scanner(isbnFile, "UTF-8");
		
		while (scanner1.hasNextLine() && scanner2.hasNextLine()) {
			String title = scanner1.nextLine();
			String isbn = scanner2.nextLine();
			Name[] authors = getAuthorArray(textPath + "firstNames.txt", textPath + "lastNames.txt");
			double price = getPrice();
			Textbook book = new Textbook(title, isbn, authors, price);
			theBag.insert(book);
		}
		scanner1.close();
		scanner2.close();
	}
	
	private static Name[] getAuthorArray(String firstnameFile, String lastnameFile) throws FileNotFoundException {
		Name[] nameArray = new Name[4];
		int randomNumber = (int) (Math.random() * 4) + 1;
		for (int i = 0; i < randomNumber; i++) {
			nameArray[i] = makeNames(firstnameFile, lastnameFile);
		}
		Name[] compactArray = Arrays.copyOf(nameArray, randomNumber);

		return compactArray;
	}
	
	public static double getPrice() {
		double price = Math.random() * 200;
		return price;
	}
	
	public static Name makeNames(String firstnameFile, String lastnameFile) throws FileNotFoundException {
		File firstNameFile = new File(firstnameFile);
		File lastNameFile = new File(lastnameFile);
		Scanner scanner1 = new Scanner(firstNameFile);
		Scanner scanner2 = new Scanner(lastNameFile);

		String[] firstNameArr = new String[2000];
		String[] lastNameArr = new String[2000];

		
		int i = 0;
		while (scanner1.hasNextLine() && scanner2.hasNextLine()) {
			firstNameArr[i] = scanner1.nextLine();
			lastNameArr[i] = scanner2.nextLine();
			i++;
		}

		Name name = new Name(firstNameArr[(int) (Math.random() * 2000)], lastNameArr[(int) (Math.random() * 2000)]);

		scanner1.close();
		scanner2.close();

		return name;
	}
	
	public static void exportBag(TextbookBag theBag) throws IOException {
		FileWriter fw = new FileWriter("RawData/TextbookInfo/titles", false);
		System.out.println("Exported");
	}
}

package model;

import java.io.Serializable;

public class TextbookBag implements Serializable {
	private Textbook[] textbookArr;
	private int nElems;
	
	public TextbookBag(int maxSize) {
		textbookArr = new Textbook[maxSize];
		nElems = 0;
	}
	
	public void insert(Textbook textbook) {
		textbookArr[nElems++] = textbook;
	}
	public Textbook findByISBN(String isbn) {
		for(int i = 0; i < nElems; i++) {
			if (textbookArr[i].getIsbn().contentEquals(isbn)) {
				return textbookArr[i];
			}
		}
		return null;
	}
	public Textbook removeByISBN(String isbn) {
		int i;
		for(i = 0; i < nElems; i++) {
			if(textbookArr[i].getIsbn().contentEquals(isbn)) {
				break;
			}
		}
		if(i == nElems) {
			return null;
		}else {
			Textbook temp = textbookArr[i];
			for(int j = i; j < nElems - 1; j++) {
				textbookArr[i] = textbookArr[i+1];
			}
			nElems--;
			return temp;
		}
	}
	public void display() {
		for(int i = 0; i < nElems; i++) {
			System.out.println(textbookArr[i]);
			System.out.println(i);
		}
		System.out.println(nElems);
	}
	public void sort() {
		  for (int i = 0; i < nElems; i++) 
	        {
	            for (int j = i + 1; j < nElems; j++) { 
	                if (textbookArr[i].getBookTitle().compareTo(textbookArr[j].getBookTitle()) > 0) 
	                {
	                    String temp = textbookArr[i].getBookTitle();
	                    textbookArr[i].setBookTitle(textbookArr[j].getBookTitle());
	                    textbookArr[j].setBookTitle(temp);
	                }
	            }
	        }
	}

	public Textbook[] getTextbookArr() {
		return textbookArr;
	}
}

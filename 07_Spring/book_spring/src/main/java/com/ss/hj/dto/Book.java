package com.ss.hj.dto;

import java.io.Serializable;

public class Book implements Serializable{
	
	private String isbn;
	private String title;
	private String author;
	private int price;
	private String content;
	private String image;
	
	public Book() {
		super();
	}
	
	public Book(String isbn, String title, String author, int price, String content, String image) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.content = content;
		this.image = image;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", price=" + price + ", content="
				+ content + ", image=" + image + "]";
	}
	
}
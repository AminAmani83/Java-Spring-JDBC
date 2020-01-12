package com.jac.lib.model;

public class Book {

	private int id;
	private String title;
	private int numberOfPages;
	private String subject;
	
	
	// Constructors
	public Book() {
	}
	
	public Book(String subject) {
		this.subject = subject;
	}

	public Book(int id, String title, int numberOfPages, String subject) {
		this.id = id;
		this.title = title;
		this.numberOfPages = numberOfPages;
		this.subject = subject;
	}


	// Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}

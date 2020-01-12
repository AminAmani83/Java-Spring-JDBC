package com.jac.lib.service;

import java.util.List;

import com.jac.lib.model.Book;

public interface BookService {

	List<Book> showBooksWithSubject(String sbj);
	boolean addBook(Book book);
	boolean removeBook(int bookId);
	boolean updateBook(Book book);
}

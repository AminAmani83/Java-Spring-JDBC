package com.jac.lib.dao;

import java.util.List;

import com.jac.lib.model.Book;

public interface BookDao {

	int insertBook(Book book);
	int removeBook(int bookId);
	int updateBook(Book book);
	List<Book> showBooksWithSubject(String sbj);

}

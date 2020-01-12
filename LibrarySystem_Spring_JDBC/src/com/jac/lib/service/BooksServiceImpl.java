package com.jac.lib.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jac.lib.dao.BookDao;
import com.jac.lib.model.Book;

@Service
public class BooksServiceImpl implements BookService {

	@Autowired
	BookDao bookDao;
	
	// Constructor
	public BooksServiceImpl() {
		
	}
	
	@Override
	public List<Book> showBooksWithSubject(String sbj) {
		return bookDao.showBooksWithSubject(sbj);
	}

	@Override
	public boolean addBook(Book book) {
		return (bookDao.insertBook(book) > 0);
	}

	@Override
	public boolean removeBook(int bookId) {
		return (bookDao.removeBook(bookId) > 0);
	}

	@Override
	public boolean updateBook(Book book) {
		return bookDao.updateBook(book) > 0;
	}

}

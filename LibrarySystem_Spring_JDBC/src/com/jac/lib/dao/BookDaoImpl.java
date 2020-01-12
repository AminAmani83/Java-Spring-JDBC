package com.jac.lib.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jac.lib.model.Book;

@Repository
public class BookDaoImpl implements BookDao {

	JdbcTemplate jdbcTemplate;

	private final String SQL_INSERT_BOOK = "insert into book(title, pageNumber, subject) values (?, ?, ?)";
	private final String SQL_DELETE_BOOK = "delete from book where id = ?";
	private final String SQL_UPDATE_BOOK = "update book set title = ?, pageNumber = ?, subject = ? where id = ?";
	private final String SQL_SHOW_BOOKS_WITH_SBJ = "select * from book where subject like ? order by id";

	// Constructor
	public BookDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int insertBook(Book book) {
		book.setSubject(book.getSubject().toLowerCase());
		return jdbcTemplate.update(SQL_INSERT_BOOK, book.getTitle(), book.getNumberOfPages(), book.getSubject());
	}

	@Override
	public int removeBook(int bookId) {
		return jdbcTemplate.update(SQL_DELETE_BOOK, bookId);
	}

	@Override
	public List<Book> showBooksWithSubject(String sbj) {
		return jdbcTemplate.query(SQL_SHOW_BOOKS_WITH_SBJ, new Object[] { sbj }, new BookMapper());
	}

	@Override
	public int updateBook(Book book) {
		return jdbcTemplate.update(SQL_UPDATE_BOOK, book.getTitle(), book.getNumberOfPages(), book.getSubject(),
				book.getId());
	}

}

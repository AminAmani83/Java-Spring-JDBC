package com.jac.lib.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jac.lib.model.Book;

public class BookMapper implements RowMapper<Book>{

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Book(rs.getInt("id"), rs.getString("title"), rs.getInt("pageNumber"), rs.getString("subject"));
	}

}

package com.jac.lib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jac.lib.model.Book;
import com.jac.lib.service.BookService;

@Controller
public class LibraryController {

	@Autowired
	private BookService service;

	// http://localhost:8080/LibrarySystem/list?sbj=all
	// http://localhost:8080/LibrarySystem/list?sbj=novel
	// http://localhost:8080/LibrarySystem/list?sbj=computer
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getCarInfo(@RequestParam("sbj") String subject) {
		System.out.println("Page Laoding... (Sbj: " + subject + ")");

		String subjectToDisplay = subject.substring(0, 1).toUpperCase() + subject.substring(1).toLowerCase();
		String newAddedBookSubject = (subject.equalsIgnoreCase("all")) ? "" : subject.toLowerCase();
		subject = (subject.equalsIgnoreCase("all")) ? "%" : subject.toLowerCase();

		ModelAndView mv = new ModelAndView("list-books");
		mv.addObject("myaddedbook", new Book(newAddedBookSubject)); // Bcs we have the form
		mv.addObject("myeditedbook", new Book()); // Bcs we have the form
		mv.addObject("booklistsubject", subjectToDisplay);
		mv.addObject("booklist", service.showBooksWithSubject(subject));

		return mv;
	}

	// http://localhost:8080/LibrarySystem/addbook?frompagesbj=novel
	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public ModelAndView addbook(@ModelAttribute("myaddedbook") Book book,
			@RequestParam("frompagesbj") String fromPageSubject) {
		System.out.println("Adding Book... (Id: " + book.getId() + ")");

		boolean result;
		
		// Form Input Validation
		if (book.getTitle().equals("") || book.getSubject().equals("")) {
			result = false;
		} else {
			// send the add book command
			result = service.addBook(book);			
		}
		
		String subjectToDisplay = fromPageSubject.substring(0, 1).toUpperCase()
				+ fromPageSubject.substring(1).toLowerCase();
		String subject = (fromPageSubject.equalsIgnoreCase("all")) ? "%" : fromPageSubject.toLowerCase();
		String newAddedBookSubject = (fromPageSubject.equalsIgnoreCase("all")) ? "" : fromPageSubject.toLowerCase();

		ModelAndView mv = new ModelAndView("list-books");
		mv.addObject("myaddedbook", new Book(newAddedBookSubject)); // Bcs we have the form
		mv.addObject("myeditedbook", new Book()); // Bcs we have the form
		mv.addObject("booklistsubject", subjectToDisplay);
		mv.addObject("booklist", service.showBooksWithSubject(subject));
		if (result) {
			mv.addObject("message", "Book successfully added");
		} else {
			mv.addObject("message", "Error adding book");
		}

		return mv;
	}

	// http://localhost:8080/LibrarySystem/editbook?frompagesbj=novel
	@RequestMapping(value = "/editbook", method = RequestMethod.POST)
	public ModelAndView editbook(@ModelAttribute("myaddedbook") Book book,
			@RequestParam("frompagesbj") String fromPageSubject) {
		System.out.println("Saving Book... (Id: " + book.getId() + ")");

		boolean result;
		
		// Form Input Validation
		if (book.getTitle().equals("") || book.getSubject().equals("")) {
			result = false;
		} else {
			// send the add book command
			result = service.updateBook(book);	
		}
		
		String subjectToDisplay = fromPageSubject.substring(0, 1).toUpperCase()
				+ fromPageSubject.substring(1).toLowerCase();
		String subject = (fromPageSubject.equalsIgnoreCase("all")) ? "%" : fromPageSubject.toLowerCase();
		String newAddedBookSubject = (fromPageSubject.equalsIgnoreCase("all")) ? "" : fromPageSubject.toLowerCase();

		ModelAndView mv = new ModelAndView("list-books");
		mv.addObject("myaddedbook", new Book(newAddedBookSubject)); // Bcs we have the form
		mv.addObject("myeditedbook", new Book()); // Bcs we have the form
		mv.addObject("booklistsubject", subjectToDisplay);
		mv.addObject("booklist", service.showBooksWithSubject(subject));
		if (result) {
			mv.addObject("message", "Book successfully edited");
		} else {
			mv.addObject("message", "Error editing book");
		}

		return mv;
	}

	// http://localhost:8080/LibrarySystem/removebook?id=1&frompagesbj=novel
	@RequestMapping(value = "/removebook", method = RequestMethod.GET)
	public ModelAndView removebook(@RequestParam("id") int bookId,
			@RequestParam("frompagesbj") String fromPageSubject) {
		System.out.println("Removing Book... (Id: " + bookId + ")");

		// send the remove book command
		boolean result = service.removeBook(bookId);

		String subjectToDisplay = fromPageSubject.substring(0, 1).toUpperCase()
				+ fromPageSubject.substring(1).toLowerCase();
		String subject = (fromPageSubject.equalsIgnoreCase("all")) ? "%" : fromPageSubject.toLowerCase();
		String newAddedBookSubject = (fromPageSubject.equalsIgnoreCase("all")) ? "" : fromPageSubject.toLowerCase();

		ModelAndView mv = new ModelAndView("list-books");
		mv.addObject("myaddedbook", new Book(newAddedBookSubject)); // Bcs we have the form
		mv.addObject("myeditedbook", new Book()); // Bcs we have the form
		mv.addObject("booklistsubject", subjectToDisplay);
		mv.addObject("booklist", service.showBooksWithSubject(subject));
		if (result) {
			mv.addObject("message", "Book successfully removed");
		} else {
			mv.addObject("message", "Error removing book");
		}

		return mv;
	}

}

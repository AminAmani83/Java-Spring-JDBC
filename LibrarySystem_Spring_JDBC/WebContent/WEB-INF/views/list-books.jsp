<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Library System - List of Books</title>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<!-- <link rel="stylesheet" href="./styles.css"> -->
	<style>
		/* Header */
		header {
			background-color: #333;
			overflow: hidden;
			padding: 0;
			margin: 0;
		}
		
		#logo {
			float: left;
			color: white;
			margin: 14px;
			font-weight: bold;
		}
		
		/* Navigation */
		nav {
			float: right;
		}
		
		nav ul {
			list-style-type: none;
			margin: 0;
			padding: 0;
			overflow: hidden;
			display: inline-block;
		}
		
		nav li {
			float: right;
			border-left: 1px solid #bbb;
		}
		
		nav li:last-child {
			border-left: none;
		}
		
		nav li a {
			display: block;
			color: white;
			text-align: center;
			padding: 14px 16px;
			text-decoration: none;
		}
		
		nav li a:hover {
			background-color: #111;
		}
		
		nav .active {
			background-color: #4CAF50;
		}
		
		/* Forms */
		.libform {
			float: left;
			width: 250px;
			padding: 5px;
		}
		
		.clear-float {
			clear: both;
		}
		
		#editform, #add-a-book {
			display: none;
		}
		
		#add-a-book {
			border: 1px solid black;
			padding: 10px;
			margin-top: 10px;
		}
	</style>
</head>
<body>
	<header>
		<div id="logo">My Library System</div>
		<nav>
			<ul>
				<li><a href="./list?sbj=novel"> Novel </a></li>
				<li><a href="./list?sbj=computer"> Computer </a></li>
				<li><a href="./list?sbj=all"> All </a></li>
			</ul>
		</nav>
	</header>
	<main>
		<div style="padding: 30px;">
			<h2>List of ${booklistsubject} Books</h2>
			<form:form action="./editbook?frompagesbj=${booklistsubject}" method="post"
				modelAttribute="myeditedbook">
				<table class="w3-table w3-striped w3-bordered">
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Pages</th>
						<th>Subject</th>
						<th>Actions</th>
						<th><a onclick="showAddNewBookForm()" href="#">+</a></th>
					</tr>
					<c:forEach var="book" items="${booklist}">
						<tr id="row${book.id}">
							<td class="book-id">${book.id}</td>
							<td class="book-title">${book.title}</td>
							<td class="book-pages">${book.numberOfPages}</td>
							<td class="book-subject">${book.subject}</td>
							<td class="book-delete"><a
								href="./removebook?id=${book.id}&frompagesbj=${booklistsubject}">Delete</a></td>
							<td class="book-update"><a href="#"
								onclick="rowBackup.updateBook(${book.id})">Update</a></td>
						</tr>
					</c:forEach>
					<tr id="editform"> <!-- Hidden Form -->
						<td class="book-id"><span id="edit-id-txt"></span><form:hidden path="id" id="edit-id" /></td>
						<td class="book-title"><form:input path="title"
								id="edit-title" /></td>
						<td class="book-pages"><form:input path="numberOfPages"
								id="edit-pagenumber" /></td>
						<td class="book-subject"><form:input path="subject"
								id="edit-subject" /></td>
						<td class="book-delete"><a href="#"
								onclick="rowBackup.cancelEdit()">Cancel</a></td>
						<td class="book-update"><input type="submit" value="Save">
						</td>
					</tr>
				</table>
			</form:form>

			${message}
			
			<div id="add-a-book"> <!-- Hidden By Default -->
				<h3>Add a new book</h3>
				<form:form action="./addbook?frompagesbj=${booklistsubject}" method="post" modelAttribute="myaddedbook">
					<div class="libform">Title</div>
					<div class="libform">Page #</div>
					<div class="libform">Subject</div>
					<div class="clear-float"></div>
					<div class="libform">
						<form:input path="title" />
					</div>
					<div class="libform">
						<form:input path="numberOfPages" />
					</div>
					<div class="libform">
					<c:choose> 
					  <c:when test="${booklistsubject == 'All'}">
					    <form:input path="subject" />
					  </c:when>
					  <c:otherwise>
					    <form:hidden path="subject" /> <!-- The real input field -->
					    <input type="text" disabled value="${booklistsubject.toLowerCase()}" /> <!-- Only for Display -->
					  </c:otherwise>
					</c:choose>
					</div>
					<div class="clear-float"></div>
					<div class="libform">
						<input type="submit" value="Save" /> 
						<input type="reset" value="Cancel" onclick="hideAddNewBookForm()" />
					</div>
					<div class="clear-float"></div>
				</form:form>
			</div>

		</div>
	</main>

	<!--  JavaScript for Update Links  -->
	<script>
	var rowBackup = {
	
		updateBook : function(bookId){
		    console.log("Update Button Clicked. Book ID: " + bookId);
		    
		    // if another update is in progress, cancel it
		    if ("trEelem" in rowBackup){
		    	this.cancelEdit();
		    }
		    
		    this.trEelem = document.getElementById("row"+bookId);
		    this.trEelemInnerHtmlBKP = this.trEelem.innerHTML;
		    this.tdBookId = this.trEelem.querySelector(".book-id");
		    this.tdBookTitle = this.trEelem.querySelector(".book-title");
		    this.tdBookPages = this.trEelem.querySelector(".book-pages");
		    this.tdBookSubject = this.trEelem.querySelector(".book-subject");
		    this.tdBookDelete = this.trEelem.querySelector(".book-delete");
		    this.tdBookUpdate = this.trEelem.querySelector(".book-update");
		    //console.log(this.tdBookTitle.innerText);
		    
		    // Add old values in the table/form
		    let editIdEelem = document.getElementById("edit-id");
		    editIdEelem.setAttribute("value", this.tdBookId.innerText);
		    let editIdSpantEelem = document.getElementById("edit-id-txt");
		    editIdSpantEelem.innerText = this.tdBookId.innerText;
		    
		    let editTitleEelem = document.getElementById("edit-title");
		    editTitleEelem.setAttribute("value", this.tdBookTitle.innerText);
		    
		    let editPageNumberEelem = document.getElementById("edit-pagenumber");
		    editPageNumberEelem.setAttribute("value", this.tdBookPages.innerText);
		    
		    let editSubjectEelem = document.getElementById("edit-subject");
		    editSubjectEelem.setAttribute("value", this.tdBookSubject.innerText);
		    
		    // backup the editform div and remove it to keep the ids unque
		    this.hiddenFormElem = document.getElementById("editform");
		    this.hiddenFormElemInnerHtmlBkp = this.hiddenFormElem.innerHTML;
		    this.hiddenFormElem.innerHTML = "";
		   
		    // copy editform div over the editing row
		    this.trEelem.innerHTML = this.hiddenFormElemInnerHtmlBkp;
		},
		
		cancelEdit : function(){
			this.trEelem.innerHTML = this.trEelemInnerHtmlBKP;
			this.hiddenFormElem.innerHTML = this.hiddenFormElemInnerHtmlBkp;
		},
	}
	
	
	const showAddNewBookForm = function(){
		document.getElementById("add-a-book").style.display = "block";
	}
	
	const hideAddNewBookForm = function(){
		document.getElementById("add-a-book").style.display = "none";
	}	
	</script>
	<!--  End of JS -->
	
</body>
</html>
<!-- Author: Amin Amani -->
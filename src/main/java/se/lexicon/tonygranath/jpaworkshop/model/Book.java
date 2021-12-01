package se.lexicon.tonygranath.jpaworkshop.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, name = "id")
	private int bookId;
	private String isbn;
	private String title;
	private int maxLoanDays;
	private boolean available = true;
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private Set<Author> authors = new HashSet<>();

	public Book(String isbn, String title, int maxLoanDays) {
		setIsbn(isbn);
		setTitle(title);
		this.maxLoanDays = maxLoanDays;
	}

	public Book() {}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int id) {
		bookId = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		if ((isbn == null) || isbn.equals(""))
			throw new IllegalArgumentException("isbn was null or empty.");
	this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if ((title == null) || title.equals(""))
			throw new IllegalArgumentException("title was null or empty.");
	this.title = title;
	}

	public int getMaxLoanDays() {
		return maxLoanDays;
	}

	public void setMaxLoanDays(int maxLoanDays) {
		this.maxLoanDays = maxLoanDays;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		if (authors == null)
			authors = new HashSet<>();
		this.authors = authors;
	}

	public void addAuthor(Author author) {
		if (author == null)
			throw new IllegalArgumentException("author was null.");
		authors.add(author);
		Set<Book> books = author.getWrittenBooks();
		books.add(this);
		author.setWrittenBooks(books);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return bookId == book.bookId && maxLoanDays == book.maxLoanDays && available == book.available && Objects.equals(isbn, book.isbn) && title.equals(book.title) && Objects.equals(authors, book.authors);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId, isbn, title, maxLoanDays, available);
	}
}

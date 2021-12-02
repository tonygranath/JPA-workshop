package se.lexicon.tonygranath.jpaworkshop.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, name = "id")
	private int authorId;
	private String firstName;
	private String lastName;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Book> writtenBooks = new HashSet<>();

	public Author(String firstName, String lastName) {
		setFirstName(firstName);
		setLastName(lastName);
	}

	public Author(String firstName, String lastName, Set<Book> books) {
		this(firstName, lastName);
		setWrittenBooks(books);
	}

	public Author() {}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if ((firstName == null) || firstName.equals(""))
			throw new IllegalArgumentException("firstName was null or empty.");
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if ((lastName == null) || lastName.equals(""))
			throw new IllegalArgumentException("lastName was null or empty.");
		this.lastName = lastName;
	}

	public Set<Book> getWrittenBooks() {
		return writtenBooks;
	}

	public void setWrittenBooks(Set<Book> writtenBooks) {
		if (writtenBooks == null)
			writtenBooks = new HashSet<>();
		this.writtenBooks = writtenBooks;
	}

	public void addBook(Book book) {
		if (book == null)
			throw new IllegalArgumentException("book was null");
		writtenBooks.add(book);
		Set<Author> authors = book.getAuthors();
		authors.add(this);
		book.setAuthors(authors);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Author author = (Author) o;
		return authorId == author.authorId && firstName.equals(author.firstName) && lastName.equals(author.lastName) && Objects.equals(writtenBooks, author.writtenBooks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, firstName, lastName);
	}
}

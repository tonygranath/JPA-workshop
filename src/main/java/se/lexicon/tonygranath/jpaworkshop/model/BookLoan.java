package se.lexicon.tonygranath.jpaworkshop.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class BookLoan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, name = "id")
	private int loanId;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private boolean returned;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_appuser_id")
	private AppUser borrower;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_book_id")
	private Book book;

	public BookLoan(LocalDate loanDate, AppUser borrower, Book book) {
		if (book.isAvailable()) {
			setLoanDate(loanDate);
			setDueDate(loanDate.plusDays(book.getMaxLoanDays()));
			this.returned = false;
			setBorrower(borrower);
			setBook(book);
		} else
			throw new RuntimeException("Book is not available.");
	}

	public BookLoan() {}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int id) {
		loanId = id;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		if (loanDate == null)
			throw new IllegalArgumentException("loanDate was null.");
		this.loanDate = loanDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		if (dueDate == null)
			throw new IllegalArgumentException("dueDate was null.");
		this.dueDate = dueDate;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public AppUser getBorrower() {
		return borrower;
	}

	public void setBorrower(AppUser borrower) {
		if (borrower == null)
			throw new IllegalArgumentException("borrower was null.");
		this.borrower = borrower;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		if (book == null)
			throw new IllegalArgumentException("book was null.");
		this.book = book;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BookLoan bookLoan = (BookLoan) o;
		return loanId == bookLoan.loanId && returned == bookLoan.returned && loanDate.equals(bookLoan.loanDate) && dueDate.equals(bookLoan.dueDate) && borrower.equals(bookLoan.borrower) && book.equals(bookLoan.book);
	}

	@Override
	public int hashCode() {
		return Objects.hash(loanId, loanDate, dueDate, returned, borrower, book);
	}
}

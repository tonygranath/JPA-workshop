package se.lexicon.tonygranath.jpaworkshop.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "appuser")
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, name = "id")
	private int appUserId;
	@Column(unique = true)
	private String username;
	private String password;
	private LocalDate regDate;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_details_id")
	private Details userDetails;
	@OneToMany(
			cascade = {
				CascadeType.DETACH,
				CascadeType.REFRESH,
				CascadeType.MERGE,
				CascadeType.PERSIST
			},
			fetch = FetchType.LAZY,
			orphanRemoval = true,
			mappedBy = "borrower"
	)
	private List<BookLoan> loans = new ArrayList<>();

	public AppUser(String username, String password, LocalDate regDate, Details userDetails) {
		setUsername(username);
		setPassword(password);
		setRegDate(regDate);
		this.userDetails = userDetails;
	}

	public AppUser() {}

	public BookLoan loan(Book book) {
		BookLoan loan = null;

		try {
			loan = new BookLoan(LocalDate.now(), this, book);
			loans.add(loan);
		} catch(RuntimeException e) {
			//do nothing
		}
		return loan;
	}

	public int getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(int appUserId) {
		this.appUserId = appUserId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if ((username == null) || username.equals(""))
			throw new IllegalArgumentException("username was null or empty.");
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if ((password == null) || password.equals(""))
			throw new IllegalArgumentException("password was null or empty.");
		this.password = password;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		if (regDate == null)
			throw new IllegalArgumentException("regDate was null.");
			this.regDate = regDate;
	}

	public Details getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(Details userDetails) {
		if (userDetails == null)
			throw new IllegalArgumentException("userDetails was null.");
		this.userDetails = userDetails;
	}

	public List<BookLoan> getLoans() {
		return loans;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AppUser appUser = (AppUser) o;
		return appUserId == appUser.appUserId && username.equals(appUser.username) && regDate.equals(appUser.regDate) && Objects.equals(userDetails, appUser.userDetails) && Objects.equals(loans, appUser.loans);
	}

	@Override
	public int hashCode() {
		return Objects.hash(appUserId, username, regDate, userDetails, loans);
	}
}

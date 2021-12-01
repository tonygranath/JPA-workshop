package se.lexicon.tonygranath.jpaworkshop.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Details {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, name = "id")
	private int detailsId;
	@Column(unique = true)
	private String email;
	private String name;
	private LocalDate birthDate;

	public Details(String email, String name, LocalDate birthDate) {
		setEmail(email);
		setName(name);
		setBirthDate(birthDate);
	}

	public Details() {}

	public int getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(int detailsId) {
		this.detailsId = detailsId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if ((email == null) || email.equals(""))
			throw new IllegalArgumentException("email was null or empty.");
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if ((name == null) || name.equals(""))
			throw new IllegalArgumentException("name was null or empty.");
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		if (birthDate == null)
			throw new IllegalArgumentException("birthDate was null.");
		this.birthDate = birthDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Details details = (Details) o;
		return detailsId == details.detailsId && email.equals(details.email) && name.equals(details.name) && birthDate.equals(details.birthDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(detailsId, email, name, birthDate);
	}
}

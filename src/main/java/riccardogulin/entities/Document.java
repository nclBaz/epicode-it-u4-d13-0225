package riccardogulin.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "documents")
public class Document {
	@Id
	@GeneratedValue
	@Column(name = "document_id")
	private UUID documentId;
	private String code;
	private String country;
	private LocalDate issueDate;
	private LocalDate expirationDate;

	@OneToOne
	// @OneToOne è obbligatoria se dichiaro un attributo di un tipo che rappresenta un'entità
	// User è un'entità quindi se non metto @OneToOne otterrò un'eccezione
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	// JoinColumn serve per personalizzare la FK
	// nullable = false significa che non possiamo creare un documento senza uno user
	// unique = true significa che non ci possono essere 2 documenti con lo stesso user
	private User owner;

	public Document() {
	}

	public Document(String code, String country, User owner) {
		this.code = code;
		this.country = country;
		this.issueDate = LocalDate.now();
		this.expirationDate = LocalDate.now().plusYears(10);
		this.owner = owner;
	}

	public UUID getDocumentId() {
		return documentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public User getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		return "Document{" +
				"documentId=" + documentId +
				", code='" + code + '\'' +
				", country='" + country + '\'' +
				", issueDate=" + issueDate +
				", expirationDate=" + expirationDate +
				", owner=" + owner +
				'}';
	}
}

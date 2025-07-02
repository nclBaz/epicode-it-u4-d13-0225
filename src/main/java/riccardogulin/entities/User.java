package riccardogulin.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue // <-- Se non metto nessuna strategy come opzione lui userà la modalità AUTO e quindi mi genererà dei codici UUID automaticamente
	@Column(name = "user_id")
	private UUID userId;
	private String name;
	private String surname;

	// 1 to 1 BIDIREZIONALE
	@OneToOne(mappedBy = "owner")
	// Se inserisco un riferimento su questo lato, con tanto di @OneToOne obbligatorio, a Document la relazione diventa bidirezionale
	// Ciò mi consente di, una volta che ho letto i dati di un utente dal db, di poter facilmente ottenere anche
	// i dati del suo documento tramite un getter getDocument, senza aver bisogno di fare dei Join o delle Select in più
	// mappedBy = "owner" deve puntare al NOME dell'attributo dell'altra classe/entity
	// N.B. La bidirezionalità non crea nessuna nuova colonna nella tabella users!!!!!!!!!!!!!!!!!!!
	private Document document;

	public User() {
	}

	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public Document getDocument() {
		return document;
	}

	public UUID getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", document=" + document +
				'}';
	}
}

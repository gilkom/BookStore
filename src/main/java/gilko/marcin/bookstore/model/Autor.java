package gilko.marcin.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BK_AUTOR")
public class Autor {
	private Long id;
	@NotBlank(message="Imię jest obowiazkowe!")
	@Size(min=2, max=30)
	private String imie;
	@NotBlank(message="Nazwisko jest obowiazkowe!")
	@Size(min=2, max=30)
	private String nazwisko;
	private String opis;
	
	public Autor() {
		
	}
	public Autor(Long id, String imie, String nazwisko, String opis) {
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.opis = opis;
	}
	@Id
	@Column(name = "ID_AUTORA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="IMIE_AUTORA")
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	@Column(name="NAZWISKO_AUTORA")
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	@Column(name="OPIS_AUTORA")
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
}

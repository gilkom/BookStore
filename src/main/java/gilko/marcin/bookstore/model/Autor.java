package gilko.marcin.bookstore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BK_AUTOR")
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_autora;
	@NotBlank(message="Imię jest obowiazkowe!")
	@Size(min=2, max=30)
	private String imie_autora;
	@NotBlank(message="Nazwisko jest obowiazkowe!")
	@Size(min=2, max=30)
	private String nazwisko_autora;
	private String opis_autora;
	
	@ManyToMany(mappedBy = "autorzy")
	private Set<Ksiazka> ksiazki = new HashSet<>();

	
	public Autor() {
		
	}
	public Autor(Long id, String imie, String nazwisko, String opis) {
		this.id_autora = id;
		this.imie_autora = imie;
		this.nazwisko_autora = nazwisko;
		this.opis_autora = opis;
	}
	
	
	public Long getId_autora() {
		return id_autora;
	}
	public void setId_autora(Long id) {
		this.id_autora = id;
	}

	public String getImie_autora() {
		return imie_autora;
	}
	public void setImie_autora(String imie) {
		this.imie_autora = imie;
	}

	public String getNazwisko_autora() {
		return nazwisko_autora;
	}
	public void setNazwisko_autora(String nazwisko) {
		this.nazwisko_autora = nazwisko;
	}

	public String getOpis_autora() {
		return opis_autora;
	}
	public void setOpis_autora(String opis) {
		this.opis_autora = opis;
	}
	public Set<Ksiazka> getKsiazka(){
		return ksiazki;
	}
	public void setKsiazka(Set<Ksiazka> ksiazka) {
		this.ksiazki = ksiazka;
		}
	
}

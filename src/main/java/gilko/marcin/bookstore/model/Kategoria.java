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
@Table(name = "BK_KATEGORIA")
public class Kategoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_kategorii;
	@NotBlank(message="Nazwa jest obowiazkowa!")
	@Size(min=2, max=30)
	private String nazwa_kategorii;
	private String opis_kategorii;
	
	@ManyToMany(mappedBy = "kategorie")
	private Set<Ksiazka> ksiazki = new HashSet<>();
	
	
	public Kategoria() {
	}
	public Kategoria(Long id, String name, String description) {
		this.id_kategorii = id;
		this.nazwa_kategorii= name;
		this.opis_kategorii = description;
	}
	
	
	public Long getId_kategorii() {
		return id_kategorii;
	}
	
	public void setId_kategorii(Long id) {
		this.id_kategorii = id;
	}

	public String getNazwa_kategorii() {
		return nazwa_kategorii;
	}
	public void setNazwa_kategorii(String name) {
		this.nazwa_kategorii = name;
	}

	public String getOpis_kategorii() {
		return opis_kategorii;
	}
	public void setOpis_kategorii(String description) {
		this.opis_kategorii = description;
	}
	
	public Set<Ksiazka> getKsiazka(){
		return ksiazki;
	}
	public void setKsiazka(Set<Ksiazka> ksiazka) {
		this.ksiazki = ksiazka;
		}
	@Override
	public String toString() {
		return "id_kategorii: " + id_kategorii + ",nazwa_kategorii: " + nazwa_kategorii;
	}
}

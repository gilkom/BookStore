package gilko.marcin.bookstore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BK_WYDAWNICTWO")
public class Wydawnictwo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_wydawnictwa;
	@NotBlank(message="Nazwa jest obowiazkowa!")
	@Size(min=2, max=30)
	private String nazwa_wydawnictwa;
	private String opis_wydawnictwa;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="wydawnictwo")
	private Set<Ksiazka> ksiazki = new HashSet<Ksiazka>();
	
	public Wydawnictwo() {
	}
	public Wydawnictwo(Long id, String name, String description) {
		this.id_wydawnictwa = id;
		this.nazwa_wydawnictwa= name;
		this.opis_wydawnictwa = description;
	}

	public Long getId_wydawnictwa() {
		return id_wydawnictwa;
	}
	
	public void setId_wydawnictwa(Long id) {
		this.id_wydawnictwa = id;
	}
	
	public String getNazwa_wydawnictwa() {
		return nazwa_wydawnictwa;
	}
	public void setNazwa_wydawnictwa(String name) {
		this.nazwa_wydawnictwa = name;
	}

	public String getOpis_wydawnictwa() {
		return opis_wydawnictwa;
	}
	public void setOpis_wydawnictwa(String description) {
		this.opis_wydawnictwa = description;
	}
}

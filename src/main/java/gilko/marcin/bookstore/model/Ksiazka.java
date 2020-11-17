package gilko.marcin.bookstore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "BK_KSIAZKA")
public class Ksiazka {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ksiazki;
	@NotBlank
	@Size(min=2, max=20)
	private String tytul_ksiazki;
	@NotNull
	@Size(min=2, max=20)
	private String oryginalny_tytul_ksiazki;
	@NotNull
	@DecimalMin("0.01")
	@Digits(integer=5, fraction=2)
	private Double cena_ksiazki;
	@NotBlank
	@Pattern(message="Wpisz poprawny ISBN(13 cyfr)", regexp="^\\d{13}$")
	private String isbn;
	@NotNull
	@Max(value=99999, message="Ilosc musi być większa od 0 lub mniejsza niż 99999!")
	private int ilosc_ksiazek;
	@NotNull
    @Min(value=1700, message="Rok musi być większy niż 1700")  
    @Max(value=2030, message="Rok musi być mniejszy niż 2030")  
	private int rok_wydania_ksiazki;
	@NotNull
    @Min(value=1, message="Liczba stron musi wynosić conajmniej 1")  
    @Max(value=3000, message="Liczba stron musi wynosić maksymalnie 3000")  
	private int liczba_stron_ksiazki;
	@NotNull
	private String oprawa_ksiazki;
	@NotNull
	@Size(min=2, max=3000)
	private String opis_ksiazki;
	private String  zdjecie_ksiazki;
	
	
	
	@ManyToOne(
			fetch = FetchType.LAZY, optional = false
			)
	@JoinColumn(name = "ID_WYDAWNICTWA", nullable = false)
	private Wydawnictwo wydawnictwo;
	
	

	@ManyToMany(
			cascade = {CascadeType.MERGE, CascadeType.PERSIST}
			)
	@JoinTable(
			name = "BK_KATEGORIA_KSIAZKI",
			joinColumns = @JoinColumn(name = "ID_KSIAZKI"),
			inverseJoinColumns = @JoinColumn(name = "ID_KATEGORII")
			)
	private Set<Kategoria> kategorie = new HashSet<>();
	
	public void addKategoria(Kategoria kategoria) {
		this.kategorie.add(kategoria);
		kategoria.getKsiazka().add(this);
	}

	public void removeKategoria(Kategoria kategoria) {
		this.kategorie.remove(kategoria);
		kategoria.getKsiazka().remove(this);
	}
	
	
	@ManyToMany(
			cascade = {CascadeType.MERGE, CascadeType.PERSIST}
			)
	@JoinTable(
			name = "BK_AUTOR_KSIAZKI",
			joinColumns = @JoinColumn(name = "ID_KSIAZKI"),
			inverseJoinColumns = @JoinColumn(name = "ID_AUTORA")
			)
	private Set<Autor> autorzy = new HashSet<>();
	
	public void addAutor(Autor autor) {
		this.autorzy.add(autor);
		autor.getKsiazka().add(this);
	}
	
	public void removeAutor(Autor autor) {
		this.autorzy.remove(autor);
		autor.getKsiazka().remove(this);
	}
	
	
	@OneToMany(mappedBy = "primaryKey.ksiazka",
			cascade = CascadeType.ALL)
	private Set<Opinia> opinie = new HashSet<>();
	
	
	public Ksiazka() {}
	
	public Ksiazka(Long id, String tytul, String org_tytul,Double cena_ksiazki, String isbn, int ilosc, int rok, int strony,
					String oprawa, String opis, String zdjecie) {
		this.id_ksiazki = id;
		this.tytul_ksiazki = tytul;
		this.oryginalny_tytul_ksiazki = tytul;
		this.cena_ksiazki = cena_ksiazki;
		this.isbn = isbn;
		this.ilosc_ksiazek = ilosc;
		this.rok_wydania_ksiazki = rok;
		this.liczba_stron_ksiazki = strony;
		this.oprawa_ksiazki = oprawa;
		this.opis_ksiazki = opis;
		this.zdjecie_ksiazki = zdjecie;
	}
	
	@Transient
	public String getZdjecie_ksiazkiImagePath() {
		if(zdjecie_ksiazki == null || zdjecie_ksiazki.isEmpty()) {
			return "/zdjecia-ksiazek/okladka.png";
		}else {
		return "/zdjecia-ksiazek/" + id_ksiazki + "/" + zdjecie_ksiazki;
		}
	}
	
	public Long getId_ksiazki() {
		return id_ksiazki;
	}

	public void setId_ksiazki(Long id) {
		this.id_ksiazki = id;
	}

	public String getTytul_ksiazki() {
		return tytul_ksiazki;
	}

	public void setTytul_ksiazki(String tytul) {
		this.tytul_ksiazki = tytul;
	}

	public String getOryginalny_tytul_ksiazki() {
		return oryginalny_tytul_ksiazki;
	}

	public void setOryginalny_tytul_ksiazki(String org_tytul) {
		this.oryginalny_tytul_ksiazki = org_tytul;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getIlosc_ksiazek() {
		return ilosc_ksiazek;
	}

	public void setIlosc_ksiazek(int ilosc) {
		this.ilosc_ksiazek = ilosc;
	}

	public int getRok_wydania_ksiazki() {
		return rok_wydania_ksiazki;
	}

	public void setRok_wydania_ksiazki(int rok) {
		this.rok_wydania_ksiazki = rok;
	}

	public int getLiczba_stron_ksiazki() {
		return liczba_stron_ksiazki;
	}

	public void setLiczba_stron_ksiazki(int strony) {
		this.liczba_stron_ksiazki = strony;
	}

	public String getOprawa_ksiazki() {
		return oprawa_ksiazki;
	}

	public void setOprawa_ksiazki(String oprawa) {
		this.oprawa_ksiazki = oprawa;
	}

	public String getOpis_ksiazki() {
		return opis_ksiazki;
	}

	public void setOpis_ksiazki(String opis) {
		this.opis_ksiazki = opis;
	}
	
	public String getZdjecie_ksiazki() {
		return zdjecie_ksiazki;
	}
	public void setZdjecie_ksiazki(String zdjecie) {
		this.zdjecie_ksiazki = zdjecie;
	}

	
	public Set<Kategoria> getKategoria(){
		return kategorie;
	}
	public void setKategoria(Set<Kategoria> kategoria) {
		this.kategorie = kategoria;
	}
	
	public Set<Autor> getAutor(){
		return autorzy;
	}
	
	public void setAutor(Set<Autor> autor) {
		this.autorzy = autor;
	}
	
	public Wydawnictwo getWydawnictwo() {
		return wydawnictwo;
	}
	
	public void setWydawnictwo(Wydawnictwo wydawnictwo) {
		this.wydawnictwo = wydawnictwo;
	}
	
	public Set<Opinia> getOpinia(){
		return opinie;
	}
	
	public void setOpinie(Set<Opinia> opinia) {
		this.opinie = opinia;
	}
	
	public void addOpinia(Opinia opinia) {
		this.opinie.add(opinia);
	}

	public Double getCena_ksiazki() {
		return cena_ksiazki;
	}

	public void setCena_ksiazki(Double cena_ksiazki) {
		this.cena_ksiazki = cena_ksiazki;
	}
	
}

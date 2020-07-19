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


@Entity
@Table(name = "BK_KSIAZKA")
public class Ksiazka {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ksiazki;
	private String tytul_ksiazki;
	private String oryginalny_tytul_ksiazki;
	private String isbn;
	private int ilosc_ksiazek;
	private int rok_wydania_ksiazki;
	private int liczba_stron_ksiazki;
	private String oprawa_ksiazki;
	private String opis_ksiazki;
	//private Long id_wydawnictwa;
	
	
	
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
	
	public Ksiazka(Long id, String tytul, String org_tytul, String isbn, int ilosc, int rok, int strony,
					String oprawa, String opis /*, Long id_wydawnictwa*/) {
		this.id_ksiazki = id;
		this.tytul_ksiazki = tytul;
		this.oryginalny_tytul_ksiazki = tytul;
		this.isbn = isbn;
		this.ilosc_ksiazek = ilosc;
		this.rok_wydania_ksiazki = rok;
		this.liczba_stron_ksiazki = strony;
		this.oprawa_ksiazki = oprawa;
		this.opis_ksiazki = opis;
		//this.id_wydawnictwa = id_wydawnictwa;
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

	/*
	  public Long getId_wydawnictwa() {
	 
		return id_wydawnictwa;
	}

	public void setId_wydawnictwa(Long id_wydawnictwa) {
		this.id_wydawnictwa = id_wydawnictwa;
	}
	*/
	
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
	
}

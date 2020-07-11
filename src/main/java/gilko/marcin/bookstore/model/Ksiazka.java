package gilko.marcin.bookstore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	private Long id_wydawnictwa;
	

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

	
	public Ksiazka() {}
	
	public Ksiazka(Long id, String tytul, String org_tytul, String isbn, int ilosc, int rok, int strony,
					String oprawa, String opis, Long id_wydawnictwa) {
		this.id_ksiazki = id;
		this.tytul_ksiazki = tytul;
		this.oryginalny_tytul_ksiazki = tytul;
		this.isbn = isbn;
		this.ilosc_ksiazek = ilosc;
		this.rok_wydania_ksiazki = rok;
		this.liczba_stron_ksiazki = strony;
		this.oprawa_ksiazki = oprawa;
		this.opis_ksiazki = opis;
		this.id_wydawnictwa = id_wydawnictwa;
	}
	
	
	//@Id
	//@Column(name="ID_KSIAZKI")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id_ksiazki;
	}

	public void setId(Long id) {
		this.id_ksiazki = id;
	}
	//@Column(name="TYTUL_KSIAZKI")
	public String getTytul() {
		return tytul_ksiazki;
	}

	public void setTytul(String tytul) {
		this.tytul_ksiazki = tytul;
	}
	//@Column(name="ORYGINALNY_TYTUL_KSIAZKI")
	public String getOrg_tytul() {
		return oryginalny_tytul_ksiazki;
	}

	public void setOrg_tytul(String org_tytul) {
		this.oryginalny_tytul_ksiazki = org_tytul;
	}
	//@Column(name="ISBN")
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	//@Column(name="ILOSC_KSIAZEK")
	public int getIlosc() {
		return ilosc_ksiazek;
	}

	public void setIlosc(int ilosc) {
		this.ilosc_ksiazek = ilosc;
	}
	//@Column(name="ROK_WYDANIA_KSIAZKI")
	public int getRok() {
		return rok_wydania_ksiazki;
	}

	public void setRok(int rok) {
		this.rok_wydania_ksiazki = rok;
	}
	//@Column(name="LICZBA_STRON_KSIAZKI")
	public int getStrony() {
		return liczba_stron_ksiazki;
	}

	public void setStrony(int strony) {
		this.liczba_stron_ksiazki = strony;
	}
	//@Column(name="OPRAWA_KSIAZKI")
	public String getOprawa() {
		return oprawa_ksiazki;
	}

	public void setOprawa(String oprawa) {
		this.oprawa_ksiazki = oprawa;
	}
	//@Column(name="OPIS_KSIAZKI")
	public String getOpis() {
		return opis_ksiazki;
	}

	public void setOpis(String opis) {
		this.opis_ksiazki = opis;
	}
	//@Column(name="ID_WYDAWNICTWA")
	public Long getId_wydawnictwa() {
		return id_wydawnictwa;
	}

	public void setId_wydawnictwa(Long id_wydawnictwa) {
		this.id_wydawnictwa = id_wydawnictwa;
	}
	
	public Set<Kategoria> getKategoria(){
		return kategorie;
	}
	public void setKategoria(Set<Kategoria> kategoria) {
		this.kategorie = kategoria;
		}
	
}

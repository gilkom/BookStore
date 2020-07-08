package gilko.marcin.bookstore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BK_KSIAZKA")
public class Ksiazka {
	private Long id;
	private String tytul;
	private String org_tytul;
	private String isbn;
	private int ilosc;
	private int rok;
	private int strony;
	private String oprawa;
	private String opis;
	private Long id_wydawnictwa;
	
	private Set<Kategoria> kategoria = new HashSet<Kategoria>();
	
	public Ksiazka() {}
	
	public Ksiazka(Long id, String tytul, String org_tytul, String isbn, int ilosc, int rok, int strony,
					String oprawa, String opis, Long id_wydawnictwa) {
		this.id = id;
		this.tytul = tytul;
		this.org_tytul = tytul;
		this.isbn = isbn;
		this.ilosc = ilosc;
		this.rok = rok;
		this.strony = strony;
		this.oprawa = oprawa;
		this.opis = opis;
		this.id_wydawnictwa = id_wydawnictwa;
	}
	
	public void addKategoria(Kategoria kategoria) {
		this.kategoria.add(kategoria);
	}
	
	@Id
	@Column(name="ID_KSIAZKI")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="TYTUL_KSIAZKI")
	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}
	@Column(name="ORYGINALNY_TYTUL_KSIAZKI")
	public String getOrg_tytul() {
		return org_tytul;
	}

	public void setOrg_tytul(String org_tytul) {
		this.org_tytul = org_tytul;
	}
	@Column(name="ISBN")
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	@Column(name="ILOSC_KSIAZEK")
	public int getIlosc() {
		return ilosc;
	}

	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}
	@Column(name="ROK_WYDANIA_KSIAZKI")
	public int getRok() {
		return rok;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}
	@Column(name="LICZBA_STRON_KSIAZKI")
	public int getStrony() {
		return strony;
	}

	public void setStrony(int strony) {
		this.strony = strony;
	}
	@Column(name="OPRAWA_KSIAZKI")
	public String getOprawa() {
		return oprawa;
	}

	public void setOprawa(String oprawa) {
		this.oprawa = oprawa;
	}
	@Column(name="OPIS_KSIAZKI")
	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	@Column(name="ID_WYDAWNICTWA")
	public Long getId_wydawnictwa() {
		return id_wydawnictwa;
	}

	public void setId_wydawnictwa(Long id_wydawnictwa) {
		this.id_wydawnictwa = id_wydawnictwa;
	}
	
	@ManyToMany(mappedBy = "ksiazka")
	public Set<Kategoria> getKategoria(){
		return kategoria;
	}
	
	public void setKategoria(Set<Kategoria> kategoria) {
		this.kategoria = kategoria;
	}
	
}

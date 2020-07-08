package gilko.marcin.bookstore.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BK_KSIAZKA")
public class Ksiazka {
	private Long id;
	private String tytul;
	private String org_tytul;
	private String isbn;
	private int ilosc;
	private Date rok;
	private int strony;
	private String oprawa;
	private String opis;
	private Long id_wydawnictwa;
	
	public Ksiazka() {}
	
	public Ksiazka(Long id, String tytul, String org_tytul, String isbn, int ilosc, Date rok, int strony,
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getOrg_tytul() {
		return org_tytul;
	}

	public void setOrg_tytul(String org_tytul) {
		this.org_tytul = org_tytul;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getIlosc() {
		return ilosc;
	}

	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}

	public Date getRok() {
		return rok;
	}

	public void setRok(Date rok) {
		this.rok = rok;
	}

	public int getStrony() {
		return strony;
	}

	public void setStrony(int strony) {
		this.strony = strony;
	}

	public String getOprawa() {
		return oprawa;
	}

	public void setOprawa(String oprawa) {
		this.oprawa = oprawa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Long getId_wydawnictwa() {
		return id_wydawnictwa;
	}

	public void setId_wydawnictwa(Long id_wydawnictwa) {
		this.id_wydawnictwa = id_wydawnictwa;
	}
	
}

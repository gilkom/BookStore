package gilko.marcin.bookstore.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class OpiniaId implements Serializable{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.ALL)	
	private Uzytkownik uzytkownik;
	@ManyToOne(cascade = CascadeType.ALL)
	private Ksiazka ksiazka;
	
	public OpiniaId() {
		
	}
	public OpiniaId(Uzytkownik uzytkownik, Ksiazka ksiazka) {
		this.uzytkownik = uzytkownik;
		this.ksiazka = ksiazka;
	}
	
	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}
	
	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}
	
	public Ksiazka getKsiazka() {
		return ksiazka;
	}

	public void setKsiazka(Ksiazka ksiazka) {
		this.ksiazka = ksiazka;
	}

}

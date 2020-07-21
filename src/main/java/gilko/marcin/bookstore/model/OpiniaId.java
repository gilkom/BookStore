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
	private Klient klient;
	@ManyToOne(cascade = CascadeType.ALL)
	private Ksiazka ksiazka;
	
	public OpiniaId() {
		
	}
	public OpiniaId(Klient klient, Ksiazka ksiazka) {
		this.klient = klient;
		this.ksiazka = ksiazka;
	}
	
	public Klient getKlient() {
		return klient;
	}
	
	public void setKlient(Klient klient) {
		this.klient = klient;
	}
	
	public Ksiazka getKsiazka() {
		return ksiazka;
	}

	public void setKsiazka(Ksiazka ksiazka) {
		this.ksiazka = ksiazka;
	}

}

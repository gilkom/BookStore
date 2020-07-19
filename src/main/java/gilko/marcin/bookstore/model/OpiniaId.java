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
	private Klient klient;
	private Ksiazka ksiazka;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Klient getKlient() {
		return klient;
	}
	
	public void setKlient(Klient klient) {
		this.klient = klient;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Ksiazka getKsiazka() {
		return ksiazka;
	}

	public void setKsiazka(Ksiazka ksiazka) {
		this.ksiazka = ksiazka;
	}

}

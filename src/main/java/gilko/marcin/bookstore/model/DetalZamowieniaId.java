package gilko.marcin.bookstore.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class DetalZamowieniaId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long pozycja_zamowienia;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Zamowienie zamowienie;
	
	public DetalZamowieniaId() {
		
	}
	
	public DetalZamowieniaId(Long pozycja_zamowienia, Zamowienie zamowienie) {
		this.pozycja_zamowienia = pozycja_zamowienia;
		this.zamowienie = zamowienie;
	}
	
	public Long getPozycja_zamowienia() {
		return pozycja_zamowienia;
	}
	
	public void setPozycja_zamowienia(Long pozycja_zamowienia) {
		this.pozycja_zamowienia = pozycja_zamowienia;
	}
	
	public Zamowienie getZamowienie() {
		return zamowienie;
	}
	
	public void setZamowienie(Zamowienie zamowienie) {
		this.zamowienie = zamowienie;
	}
		
}



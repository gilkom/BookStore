package gilko.marcin.bookstore.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "BK_DETAL_ZAMOWIENIA")
@AssociationOverrides({
	@AssociationOverride(name = "primaryKey.pozycja_zamowienia"),
	@AssociationOverride(name = "primaryKey.zamowienie", joinColumns = @JoinColumn(name = "ID_ZAMOWIENIA"))})
public class DetalZamowienia {
	
	@EmbeddedId
	private DetalZamowieniaId primary_key = new DetalZamowieniaId();
	
	private float cena_zakupu;
	private int ilosc_zamowiona;
	private float wartosc_pozycji;
	
	@ManyToOne(
			fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_KSIAZKI", nullable = false)
	private Ksiazka ksiazka;
	
	public DetalZamowienia() {}
	
	public DetalZamowienia(DetalZamowieniaId detalZamowieniaId, float cena_zakupu, int ilosc_zamowiona,
							float wartosc_pozycji) {
		this.primary_key = detalZamowieniaId;
		this.setCena_zakupu(cena_zakupu);
		this.setIlosc_zamowiona(ilosc_zamowiona);
		this.setWartosc_pozycji(wartosc_pozycji);
	}
	
	public DetalZamowieniaId getPrimary_key() {
		return primary_key;
	}
	
	public void setPrimary_key(DetalZamowieniaId detalZamowieniaId) {
		this.primary_key = detalZamowieniaId;
	}
	
	public Long getPozycja_zamowienia() {
		return getPrimary_key().getPozycjaZamowienia();
	}
	
	public void setPozycja_zamowienia(Long pozycja_zamowienia) {
		getPrimary_key().setPozycjaZamowienia(pozycja_zamowienia);
	}
	
	@Transient
	public Zamowienie getZamowienie() {
		return getPrimary_key().getZamowienie();
	}
	
	public void setZamowienie(Zamowienie zamowienie) {
		getPrimary_key().setZamowienie(zamowienie);
	}

	public float getCena_zakupu() {
		return cena_zakupu;
	}

	public void setCena_zakupu(float cena_zakupu) {
		this.cena_zakupu = cena_zakupu;
	}

	public int getIlosc_zamowiona() {
		return ilosc_zamowiona;
	}

	public void setIlosc_zamowiona(int ilosc_zamowiona) {
		this.ilosc_zamowiona = ilosc_zamowiona;
	}

	public float getWartosc_pozycji() {
		return wartosc_pozycji;
	}

	public void setWartosc_pozycji(float wartosc_pozycji) {
		this.wartosc_pozycji = wartosc_pozycji;
	}
	
	
}

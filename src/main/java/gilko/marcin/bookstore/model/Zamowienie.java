package gilko.marcin.bookstore.model;





import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BK_ZAMOWIENIE")
public class Zamowienie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_zamowienia;
	@Type(type = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data_zamowienia;
	private float wartosc_zamowienia;
	private String status_zamowienia;

	@ManyToOne(
			fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_uzytkownika", nullable = false)
	private Uzytkownik uzytkownik;
	
	@OneToMany(mappedBy = "primaryKey.zamowienie", cascade = CascadeType.ALL)
	private Set<DetalZamowienia> detaleZamowienia = new HashSet<DetalZamowienia>();
	
	public Zamowienie() {}
	
	public Zamowienie(Long id_zamowienia, Date data_zamowienia, float wartosc_zamowienia,
						String status_zamowienia){
		this.id_zamowienia = id_zamowienia;
		this.setData_zamowienia(data_zamowienia);
		this.setWartosc_zamowienia(wartosc_zamowienia);
		this.setStatus_zamowienia(status_zamowienia);
	}
	
	public Long getId_zamowienia() {
		return id_zamowienia;
	}
	public void setId_zamowienia(Long id_zamowienia) {
		this.id_zamowienia = id_zamowienia;
	}

	public Date getData_zamowienia() {
		return data_zamowienia;
	}

	public void setData_zamowienia(Date data_zamowienia) {
		this.data_zamowienia = data_zamowienia;
	}

	public float getWartosc_zamowienia() {
		return wartosc_zamowienia;
	}

	public void setWartosc_zamowienia(float wartosc_zamowienia) {
		this.wartosc_zamowienia = wartosc_zamowienia;
	}

	public String getStatus_zamowienia() {
		return status_zamowienia;
	}

	public void setStatus_zamowienia(String status_zamowienia) {
		this.status_zamowienia = status_zamowienia;
	}
	
	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}
	
	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}
	
	public Set<DetalZamowienia> getDetalZamowienia(){
		return detaleZamowienia;
	}
	
	public void setDetalZamowienia(Set<DetalZamowienia> detalZamowienia) {
		this.detaleZamowienia = detalZamowienia;
	}
	
	public void addDetalZamowienia(DetalZamowienia detalZamowienia) {
		this.detaleZamowienia.add(detalZamowienia);
	}
}

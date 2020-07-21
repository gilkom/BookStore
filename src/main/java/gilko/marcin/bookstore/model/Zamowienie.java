package gilko.marcin.bookstore.model;



import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "BK_ZAMOWIENIE")
public class Zamowienie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_zamowienia;
	private Date data_zamowienia;
	private float wartosc_zamowienia;
	private String status_zamowienia;
	
	@ManyToOne(
			fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_PRACOWNIKA", nullable = false)
	private Pracownik pracownik;
	
	@ManyToOne(
			fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_KLIENTA", nullable = false)
	private Klient klient;
	
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
	
	public Pracownik getPracownik() {
		return pracownik;
	}
	
	public void setPracownik(Pracownik pracownik) {
		this.pracownik = pracownik;
	}
	
	public Klient getKlient() {
		return klient;
	}
	
	public void setKlient(Klient klient) {
		this.klient = klient;
	}
}


package gilko.marcin.bookstore.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "BK_KLIENT")
public class Klient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_klienta;
	private String imie_klienta;
	private String nazwisko_klienta;
	private String nazwa_firmy;
	private String nip_klienta;
	private String ulica_klienta;
	private String miasto_klienta;
	private String kod_pocztowy_klienta;
	private String telefon_klienta;
	private String email_klienta;
	private String password;
	private int blokada_konta;
	private String rola;
	
	
	public Klient() {
		
	}
	public Klient(Long id_klienta, String imie_klienta, String nazwisko_klienta, String nazwa_firmy,
					String nip_klienta, String ulica_klienta, String miasto_klienta, String kod_pocztowy_klienta,
					String telefon_klienta, String email_klienta, String password, int blokada_konta, String rola) {
		this.id_klienta = id_klienta;
		this.imie_klienta = imie_klienta;
		this.nazwisko_klienta = nazwisko_klienta;
		this.nazwa_firmy = nazwa_firmy;
		this.nip_klienta = nip_klienta;
		this.ulica_klienta = ulica_klienta;
		this.miasto_klienta = miasto_klienta;
		this.kod_pocztowy_klienta = kod_pocztowy_klienta;
		this.telefon_klienta = telefon_klienta;
		this.email_klienta = email_klienta;
		this.blokada_konta = blokada_konta;
		this.rola = rola;
	}
	
	public Long getId_klienta() {
		return id_klienta;
	}
	public void setId_klienta(Long id_klienta) {
		this.id_klienta = id_klienta;
	}
	
	public String getImie_klienta() {
		return imie_klienta;
	}
	public void setImie_klienta(String imie_klienta) {
		this.imie_klienta = imie_klienta;
	}
	public String getNazwisko_klienta() {
		return nazwisko_klienta;
	}
	public void setNazwisko_klienta(String nazwisko_klienta) {
		this.nazwisko_klienta = nazwisko_klienta;
	}
	public String getNazwa_firmy() {
		return nazwa_firmy;
	}
	public void setNazwa_firmy(String nazwa_firmy) {
		this.nazwa_firmy = nazwa_firmy;
	}
	public String getNip_klienta() {
		return nip_klienta;
	}
	public void setNip_klienta(String nip_klienta) {
		this.nip_klienta = nip_klienta;
	}
	public String getUlica_klienta() {
		return ulica_klienta;
	}
	public void setUlica_klienta(String ulica_klienta) {
		this.ulica_klienta = ulica_klienta;
	}
	public String getMiasto_klienta() {
		return miasto_klienta;
	}
	public void setMiasto_klienta(String miasto_klienta) {
		this.miasto_klienta = miasto_klienta;
	}
	public String getKod_pocztowy_klienta() {
		return kod_pocztowy_klienta;
	}
	public void setKod_pocztowy_klienta(String kod_pocztowy_klienta) {
		this.kod_pocztowy_klienta = kod_pocztowy_klienta;
	}
	
	public String getTelefon_klienta() {
		return telefon_klienta;
	}
	public void setTelefon_klienta(String telefon_klienta) {
		this.telefon_klienta = telefon_klienta;
	}
	public String getEmail_klienta() {
		return email_klienta;
	}
	public void setEmail_klienta(String email_klienta) {
		this.email_klienta = email_klienta;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBlokada_konta() {
		return blokada_konta;
	}
	public void setBlokada_konta(int blokada_konta) {
		this.blokada_konta = blokada_konta;
	}
	public String getRola() {
		return rola;
	}
	public void setRola(String rola) {
		this.rola = rola;
	}
	
}

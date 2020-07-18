package gilko.marcin.bookstore.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "BK_PRACOWNIK")
public class Pracownik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_pracownika;
	private String imie_pracownika;
	private String nazwisko_pracownika;
	private String telefon_pracownika;
	private String email_pracownika;
	private String login_pracownika;
	private String password;
	private int blokada_konta;
	private Date data_rejestracji;
	private String rola;
	
	
	public Pracownik() {
		
	}
	public Pracownik(Long id_pracownika, String imie_pracownika, String nazwisko_pracownika, 
					String telefon_pracownika, String email_pracownika, String login_pracownika,
					String password, int blokada_konta, Date data_rejestracji, String rola) {
		this.id_pracownika = id_pracownika;
		this.imie_pracownika = imie_pracownika;
		this.nazwisko_pracownika = nazwisko_pracownika;
		this.telefon_pracownika = telefon_pracownika;
		this.email_pracownika = email_pracownika;
		this.login_pracownika = login_pracownika;
		this.password = password;
		this.blokada_konta = blokada_konta;
		this.data_rejestracji = data_rejestracji;
		this.rola = rola;
	}
	
	public Long getId_pracownika() {
		return id_pracownika;
	}
	public void setId_pracownika(Long id_pracownika) {
		this.id_pracownika = id_pracownika;
	}
	
	public String getImie_pracownika() {
		return imie_pracownika;
	}
	public void setImie_pracownika(String imie_pracownika) {
		this.imie_pracownika = imie_pracownika;
	}
	public String getNazwisko_pracownika() {
		return nazwisko_pracownika;
	}
	public void setNazwisko_pracownika(String nazwisko_pracownika) {
		this.nazwisko_pracownika = nazwisko_pracownika;
	}
	public String getTelefon_pracownika() {
		return telefon_pracownika;
	}
	public void setTelefon_pracownika(String telefon_pracownika) {
		this.telefon_pracownika = telefon_pracownika;
	}
	public String getEmail_pracownika() {
		return email_pracownika;
	}
	public void setEmail_pracownika(String email_pracownika) {
		this.email_pracownika = email_pracownika;
	}
	public String getLogin_pracownika() {
		return login_pracownika;
	}
	public void setLogin_pracownika(String login_pracownika) {
		this.login_pracownika = login_pracownika;
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
	public Date getData_rejestracji() {
		return data_rejestracji;
	}
	public void setData_rejestracji(Date data_rejestracji) {
		this.data_rejestracji = data_rejestracji;
	}
	public String getRola() {
		return rola;
	}
	public void setRola(String rola) {
		this.rola = rola;
	}
	
	
}

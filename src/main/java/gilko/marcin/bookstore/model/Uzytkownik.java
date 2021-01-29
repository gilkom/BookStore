
package gilko.marcin.bookstore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BK_UZYTKOWNIK")
public class Uzytkownik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_uzytkownika;
	@NotBlank
	@Size(min=2, max=20)
	private String imie_uzytkownika;
	@NotNull
	@Size(min=2, max=20)
	private String nazwisko_uzytkownika;
	//@NotBlank
	@Size(min=0, max=30)
	private String nazwa_firmy;
	//@NotBlank
	@Pattern(message="Wpisz poprawny NIP(10 cyfr)", regexp="^\\d{10}$|^$")
	private String nip_uzytkownika;
	@NotBlank
	@Size(min=2, max=30)
	private String ulica_uzytkownika;
	@NotBlank
	@Size(min=2, max=20)
	private String miasto_uzytkownika;
	@NotBlank
	@Pattern(message="Wpisz poprawny kod(wzór = '01026'", regexp="^\\d{5}$")
	private String kod_pocztowy_uzytkownika;
	@Pattern(message="Wpisz poprawny numer telefonu(10 cyfr)", regexp="^\\d{10}$|^$")
	private String telefon_uzytkownika;
	@NotBlank
	@Email(message="Wpisz poprawny email")
	private String email_uzytkownika;
	@NotBlank
	private String password;
	@NotNull
	private int blokada_konta;
	@NotBlank
	private String rola;
	
	
	@OneToMany(mappedBy = "primaryKey.uzytkownik",
			cascade = CascadeType.PERSIST)
	private Set<Opinia> opinie = new HashSet<Opinia>();
	
	
	public Uzytkownik() {
		
	}
	public Uzytkownik(Long id_uzytkownika, String imie_uzytkownika, String nazwisko_uzytkownika, String nazwa_firmy,
					String nip_uzytkownika, String ulica_uzytkownika, String miasto_uzytkownika, String kod_pocztowy_uzytkownika,
					String telefon_uzytkownika, String email_uzytkownika, String password, int blokada_konta, String rola) {
		this.id_uzytkownika = id_uzytkownika;
		this.imie_uzytkownika = imie_uzytkownika;
		this.nazwisko_uzytkownika = nazwisko_uzytkownika;
		this.nazwa_firmy = nazwa_firmy;
		this.nip_uzytkownika = nip_uzytkownika;
		this.ulica_uzytkownika = ulica_uzytkownika;
		this.miasto_uzytkownika = miasto_uzytkownika;
		this.kod_pocztowy_uzytkownika = kod_pocztowy_uzytkownika;
		this.telefon_uzytkownika = telefon_uzytkownika;
		this.email_uzytkownika = email_uzytkownika;
		this.blokada_konta = blokada_konta;
		this.rola = rola;
	}
	
	public Long getId_uzytkownika() {
		return id_uzytkownika;
	}
	public void setId_uzytkownika(Long id_uzytkownika) {
		this.id_uzytkownika = id_uzytkownika;
	}
	
	public String getImie_uzytkownika() {
		return imie_uzytkownika;
	}
	public void setImie_uzytkownika(String imie_uzytkownika) {
		this.imie_uzytkownika = imie_uzytkownika;
	}
	public String getNazwisko_uzytkownika() {
		return nazwisko_uzytkownika;
	}
	public void setNazwisko_uzytkownika(String nazwisko_uzytkownika) {
		this.nazwisko_uzytkownika = nazwisko_uzytkownika;
	}
	public String getNazwa_firmy() {
		return nazwa_firmy;
	}
	public void setNazwa_firmy(String nazwa_firmy) {
		this.nazwa_firmy = nazwa_firmy;
	}
	public String getNip_uzytkownika() {
		return nip_uzytkownika;
	}
	public void setNip_uzytkownika(String nip_uzytkownika) {
		this.nip_uzytkownika = nip_uzytkownika;
	}
	public String getUlica_uzytkownika() {
		return ulica_uzytkownika;
	}
	public void setUlica_uzytkownika(String ulica_uzytkownika) {
		this.ulica_uzytkownika = ulica_uzytkownika;
	}
	public String getMiasto_uzytkownika() {
		return miasto_uzytkownika;
	}
	public void setMiasto_uzytkownika(String miasto_uzytkownika) {
		this.miasto_uzytkownika = miasto_uzytkownika;
	}
	public String getKod_pocztowy_uzytkownika() {
		return kod_pocztowy_uzytkownika;
	}
	public void setKod_pocztowy_uzytkownika(String kod_pocztowy_uzytkownika) {
		this.kod_pocztowy_uzytkownika = kod_pocztowy_uzytkownika;
	}
	
	public String getTelefon_uzytkownika() {
		return telefon_uzytkownika;
	}
	public void setTelefon_uzytkownika(String telefon_uzytkownika) {
		this.telefon_uzytkownika = telefon_uzytkownika;
	}
	public String getEmail_uzytkownika() {
		return email_uzytkownika;
	}
	public void setEmail_uzytkownika(String email_uzytkownika) {
		this.email_uzytkownika = email_uzytkownika;
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
	
	public Set<Opinia> getOpinia(){
		return opinie;
	}
	
	public void setOpinia(Set<Opinia> opinia) {
		this.opinie = opinia;
	}
	
	public void addOpinia(Opinia opinia) {
		this.opinie.add(opinia);
	}
	
}

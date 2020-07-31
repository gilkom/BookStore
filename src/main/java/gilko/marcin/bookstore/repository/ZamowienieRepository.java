package gilko.marcin.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Zamowienie;

@Repository
public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long>{

	@Query(value = "SELECT z FROM Zamowienie z WHERE z.uzytkownik.id_uzytkownika = :id_uzytkownika AND z.status_zamowienia = 'KOSZYK'")
	public Zamowienie searchZamowienieByKoszyk(@Param("id_uzytkownika")Long id_uzytkownika);
	
	@Query(value = "SELECT z FROM Zamowienie z WHERE z.uzytkownik.id_uzytkownika = :id_uzytkownika AND z.status_zamowienia != 'KOSZYK'")
	public List<Zamowienie> searchZamowienieByNotKoszykById(@Param("id_uzytkownika")Long id_uzytkownika);
	
	@Query(value = "SELECT z FROM Zamowienie z WHERE z.status_zamowienia != 'KOSZYK'")
	public List<Zamowienie> searchZamowienieByNotKoszyk();
	
	@Query(value = "SELECT z FROM Zamowienie z WHERE z.status_zamowienia = 'ZAMÓWIONE'")
	public List<Zamowienie> searchZamowienieByZamowione();
	
	@Query(value = "SELECT z FROM Zamowienie z WHERE z.status_zamowienia = 'SKOMPLETOWANE'")
	public List<Zamowienie> searchZamowienieBySkompletowane();
	
	@Query(value = "SELECT z FROM Zamowienie z WHERE z.status_zamowienia = 'WYSŁANE'")
	public List<Zamowienie> searchZamowienieByWyslane();
	
	@Query(value = "SELECT z FROM Zamowienie z WHERE z.status_zamowienia = 'ZAMKNIĘTE'")
	public List<Zamowienie> searchZamowienieByZamkniete();
}

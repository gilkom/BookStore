package gilko.marcin.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.DetalZamowienia;
import gilko.marcin.bookstore.model.DetalZamowieniaId;

@Repository
public interface DetalZamowieniaRepository  extends JpaRepository<DetalZamowienia, DetalZamowieniaId>{
	DetalZamowienia findByPrimaryKey(DetalZamowieniaId detalZamowieniaId);
	
	@Query(value = "SELECT d FROM DetalZamowienia d WHERE d.primaryKey.zamowienie.uzytkownik.id_uzytkownika = :id_uzytkownika AND d.primaryKey.zamowienie.status_zamowienia = 'KOSZYK'")
	public List<DetalZamowienia> searchDetalByKoszyk(@Param("id_uzytkownika") Long id_uzytkownika);
	
	@Query(value = "SELECT d FROM DetalZamowienia d WHERE d.primaryKey.zamowienie.id_zamowienia = :id_zamowienia")
	public List<DetalZamowienia> searchDetalByIdZamowienia(@Param("id_zamowienia") Long id_zamowienia);

	//@Query(value = "SELECT d FROM Ksiazka ks WHERE ks.id_ksiazki IN (SELECT d.id_ksiazki FROM (SELECT d.id_ksiazki, SUM(d.ilosc_zamowiona) FROM DetalZamowienia d GROUP BY d.id_ksiazki ORDER BY SUM(d.ilosc_zamowiona)DESC) WHERE ROWNUM <=10)")
	@Query(value = "SELECT d.ksiazka.id_ksiazki FROM DetalZamowienia d")
	public List<Long> searchBestsellers();
}

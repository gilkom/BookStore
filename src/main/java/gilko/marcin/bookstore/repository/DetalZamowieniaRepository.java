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
	
	@Query(value = "SELECT d FROM DetalZamowienia d WHERE d.primaryKey.zamowienie.klient.id_klienta = :id_klienta AND d.primaryKey.zamowienie.status_zamowienia = 'KOSZYK'")
	public List<DetalZamowienia> searchDetalByKoszyk(@Param("id_klienta") Long id_klienta);
	
	@Query(value = "SELECT d FROM DetalZamowienia d WHERE d.primaryKey.zamowienie.id_zamowienia = :id_zamowienia")
	public List<DetalZamowienia> searchDetalByIdZamowienia(@Param("id_zamowienia") Long id_zamowienia);

}

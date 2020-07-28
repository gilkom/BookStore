package gilko.marcin.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Klient;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Long>{
	
	@Query(value = "SELECT k FROM Klient k WHERE k.email_klienta = :email_klienta")
	public Klient searchKlientByEmail(@Param("email_klienta")String email_klienta);
}

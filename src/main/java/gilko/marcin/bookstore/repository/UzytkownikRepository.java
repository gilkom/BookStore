package gilko.marcin.bookstore.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Uzytkownik;

@Repository
public interface UzytkownikRepository extends JpaRepository<Uzytkownik, Long>{
	
	@Query(value = "SELECT k FROM Uzytkownik k WHERE k.email_uzytkownika = :email_uzytkownika")
	public Uzytkownik searchUzytkownikByEmail(@Param("email_uzytkownika")String email_uzytkownika);
	
	
	@Query(value = "SELECT k FROM Uzytkownik k WHERE k.rola = 'ROLE_USER'")
	public List<Uzytkownik> searchByRoleUser();
}

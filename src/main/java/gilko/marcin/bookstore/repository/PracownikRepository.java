package gilko.marcin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Pracownik;

@Repository
public interface PracownikRepository extends JpaRepository<Pracownik, Long>{

}

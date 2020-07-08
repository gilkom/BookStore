package gilko.marcin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Ksiazka;

@Repository
public interface KsiazkaRepository extends JpaRepository<Ksiazka, Long>{

}

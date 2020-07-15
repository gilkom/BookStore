package gilko.marcin.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Kategoria;
import gilko.marcin.bookstore.model.Ksiazka;

@Repository
public interface KsiazkaRepository extends JpaRepository<Ksiazka, Long>{

	
}

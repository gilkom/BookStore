package gilko.marcin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}

package gilko.marcin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Bk_kategoria;

@Repository
public interface KategoriaRepository extends JpaRepository<Bk_kategoria, Long> {

}

package gilko.marcin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gilko.marcin.bookstore.model.Kategoria;

public interface KategoriaRepository extends JpaRepository<Kategoria, Long> {

}

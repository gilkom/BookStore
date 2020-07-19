package gilko.marcin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Klient;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Long>{

}

package gilko.marcin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Opinia;

@Repository
public interface OpiniaRepository extends JpaRepository<Opinia, Long>{

}

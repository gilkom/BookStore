package gilko.marcin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Wydawnictwo;



@Repository 
public interface WydawnictwoRepository extends JpaRepository<Wydawnictwo, Long> {

}

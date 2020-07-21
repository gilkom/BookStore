package gilko.marcin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Zamowienie;

@Repository
public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long>{

}

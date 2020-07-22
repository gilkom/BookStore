package gilko.marcin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.DetalZamowienia;
import gilko.marcin.bookstore.model.DetalZamowieniaId;

@Repository
public interface DetalZamowieniaRepository  extends JpaRepository<DetalZamowienia, DetalZamowieniaId>{
	DetalZamowienia findByPrimaryKey(DetalZamowieniaId detalZamowieniaId);

}

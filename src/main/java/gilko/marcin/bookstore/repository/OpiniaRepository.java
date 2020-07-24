package gilko.marcin.bookstore.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Opinia;
import gilko.marcin.bookstore.model.OpiniaId;

@Repository
public interface OpiniaRepository extends JpaRepository<Opinia, OpiniaId>{
	Opinia findByPrimaryKey(OpiniaId opiniaId);
	
	@Query("Select o from Opinia o where o.primaryKey.ksiazka.id_ksiazki = :id_ksiazki")
	List<Opinia> myQuery(Long id_ksiazki);
	
}

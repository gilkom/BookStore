package gilko.marcin.bookstore.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gilko.marcin.bookstore.model.Ksiazka;

@Repository
public interface KsiazkaRepository extends JpaRepository<Ksiazka, Long>{
	@Query(value ="SELECT k FROM Ksiazka k WHERE CONCAT(k.tytul_ksiazki, k.oryginalny_tytul_ksiazki) LIKE %:keyword%")
	public Page<Ksiazka> search(@Param("keyword") String keyword, Pageable pageable);
	
	@Query(value = "SELECT ks FROM Ksiazka ks JOIN ks.kategorie kat  WHERE  CONCAT(ks.tytul_ksiazki, ks.oryginalny_tytul_ksiazki) LIKE %:keyword% AND  :kategoria in k.kategorie.nazwa_kategorii")
	public Page<Ksiazka> searchKategoria(@Param("keyword") String keyword, @Param("kategoria") String kategoria, Pageable pageable);
	
}

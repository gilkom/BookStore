package gilko.marcin.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.bookstore.model.DetalZamowienia;
import gilko.marcin.bookstore.model.DetalZamowieniaId;
import gilko.marcin.bookstore.repository.DetalZamowieniaRepository;


@Service
@Transactional
public class DetalZamowieniaService {
	
	@Autowired
	private DetalZamowieniaRepository repo;
	
	public List<DetalZamowienia> list(){
		return repo.findAll();
	}
	
	public void save(DetalZamowienia detalZamowienia) {
		repo.save(detalZamowienia);
	}
	
	public DetalZamowienia get(DetalZamowieniaId detalZamowieniaId) {
		return repo.findByPrimaryKey(detalZamowieniaId);
	}
	
	public void delete(DetalZamowienia detalZamowienia) {
		repo.delete(detalZamowienia);
	}
	
	public List<DetalZamowienia> listKoszyk(Long id_uzytkownika){
		return repo.searchDetalByKoszyk(id_uzytkownika);
	}
	
	public List<DetalZamowienia> listByIdZamowienia(Long id_zamowienia){
		return repo.searchDetalByIdZamowienia(id_zamowienia);
	}
	
}

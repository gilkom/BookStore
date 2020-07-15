package gilko.marcin.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.bookstore.model.Kategoria;
import gilko.marcin.bookstore.model.Ksiazka;
import gilko.marcin.bookstore.repository.KsiazkaRepository;

@Service
@Transactional
public class KsiazkaService {
	@Autowired
	private KsiazkaRepository repo;
	
	public List<Ksiazka> list(){
		return repo.findAll();
	}
	
	public void save(Ksiazka ksiazka) {
		repo.save(ksiazka);
	}
	
	public Ksiazka get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	

}

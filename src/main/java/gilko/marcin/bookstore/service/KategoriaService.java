package gilko.marcin.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.bookstore.model.Kategoria;
import gilko.marcin.bookstore.repository.KategoriaRepository;

@Service
@Transactional
public class KategoriaService {
	@Autowired
	private KategoriaRepository repo;
	
	public List<Kategoria> list(){
		return repo.findAll();
	}
	
	public void save(Kategoria kategoria) {
	}
	
	public Kategoria get(int id) {
		return null;
	}
	
	public void update(Kategoria kategoria) {
		
	}
	
	public void delete(int id) {
		
	}

}

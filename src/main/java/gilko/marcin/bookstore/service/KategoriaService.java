package gilko.marcin.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.bookstore.model.Bk_kategoria;
import gilko.marcin.bookstore.repository.KategoriaRepository;

@Service
@Transactional
public class KategoriaService {
	@Autowired
	private KategoriaRepository repo;
	
	public List<Bk_kategoria> list(){
		return repo.findAll();
	}
	
	public void save(Bk_kategoria kategoria) {
	}
	
	public Bk_kategoria get(int id) {
		return null;
	}
	
	public void update(Bk_kategoria kategoria) {
		
	}
	
	public void delete(int id) {
		
	}

}

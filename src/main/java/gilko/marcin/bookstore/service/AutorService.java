package gilko.marcin.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.bookstore.model.Autor;
import gilko.marcin.bookstore.repository.AutorRepository;

@Service
@Transactional
public class AutorService {
	@Autowired
	private AutorRepository repo;
	
	public List<Autor> list(){
		return repo.findAll();
	}
	
	public void save(Autor autor) {
		repo.save(autor);
	}
	
	public Autor get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}

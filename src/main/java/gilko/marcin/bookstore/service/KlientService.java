package gilko.marcin.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.bookstore.model.Klient;
import gilko.marcin.bookstore.repository.KlientRepository;

@Service
@Transactional
public class KlientService {
	@Autowired
	private KlientRepository repo;
	
	public List<Klient> list(){
		return repo.findAll();
	}
	
	public void save(Klient klient) {
		repo.save(klient);
	}
	
	public Klient get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

}

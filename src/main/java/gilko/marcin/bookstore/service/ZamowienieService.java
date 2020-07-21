package gilko.marcin.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.bookstore.model.Zamowienie;
import gilko.marcin.bookstore.repository.ZamowienieRepository;

@Service
@Transactional
public class ZamowienieService {
	@Autowired
	private ZamowienieRepository repo;
	
	public List<Zamowienie> list(){
		return repo.findAll();
	}
	
	public void save(Zamowienie zamowienie) {
		repo.save(zamowienie);
	}
	
	public Zamowienie get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

}
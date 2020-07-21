package gilko.marcin.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.bookstore.model.Pracownik;
import gilko.marcin.bookstore.repository.PracownikRepository;

@Service
@Transactional
public class PracownikService {
	@Autowired
	private PracownikRepository repo;
	
	public List<Pracownik> list(){
		return repo.findAll();
	}
	

	public void save(Pracownik pracownik) {
		repo.save(pracownik);
	}
	
	public Pracownik get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}

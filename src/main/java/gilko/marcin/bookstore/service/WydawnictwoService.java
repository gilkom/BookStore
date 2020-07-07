package gilko.marcin.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.bookstore.model.Wydawnictwo;
import gilko.marcin.bookstore.repository.WydawnictwoRepository;

@Service
@Transactional
public class WydawnictwoService {
	@Autowired
	private WydawnictwoRepository repo;
	
	public List<Wydawnictwo> list(){
		return repo.findAll();
	}
	
	public void save(Wydawnictwo wydawnictwo) {
		repo.save(wydawnictwo);
	}
	
	public Wydawnictwo get(Long id) {
		return repo.findById(id).get();
	}
	
	public void update(Wydawnictwo wydawnictwo) {
		
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

}

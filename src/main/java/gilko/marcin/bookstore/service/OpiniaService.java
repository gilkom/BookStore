package gilko.marcin.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.bookstore.model.Opinia;
import gilko.marcin.bookstore.model.OpiniaId;
import gilko.marcin.bookstore.repository.OpiniaRepository;

@Service
@Transactional
public class OpiniaService {
	@Autowired
	private OpiniaRepository repo;
	
	public List<Opinia> list(){
		return repo.findAll();
	}
	
	public void save(Opinia opinia) {
		repo.save(opinia);
	}
	
	public Opinia get(OpiniaId opiniaId) {
		
		return repo.findByPrimaryKey(opiniaId);
	}
	
	public void delete(Opinia opinia) {
		repo.delete(opinia);
	}
}

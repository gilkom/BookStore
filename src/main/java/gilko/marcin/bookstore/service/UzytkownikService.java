package gilko.marcin.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.bookstore.model.Uzytkownik;
import gilko.marcin.bookstore.repository.UzytkownikRepository;

@Service
@Transactional
public class UzytkownikService{
	@Autowired
	private UzytkownikRepository repo;
	
	public List<Uzytkownik> list(){
		return repo.findAll();
	}
	
	public void save(Uzytkownik uzytkownik) {
		repo.save(uzytkownik);
	}
	
	public Uzytkownik get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Uzytkownik getByEmail(String email_uzytkownika) {
		return repo.searchUzytkownikByEmail(email_uzytkownika);
	}
	
	

}

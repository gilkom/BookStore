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
	
	public Zamowienie getZamowienieKoszyk(Long id_klienta) {
		return repo.searchZamowienieByKoszyk(id_klienta);
	}
	
	public List<Zamowienie> getZamowienieNotKoszykById(Long id_klienta) {
		return repo.searchZamowienieByNotKoszykById(id_klienta);
	}
	public List<Zamowienie> getZamowienieByNotKoszyk(){
		return repo.searchZamowienieByNotKoszyk();
	}

	public List<Zamowienie> getZamowienieByZamowione(){
		return repo.searchZamowienieByZamowione();
	}
	public List<Zamowienie> getZamowienieBySkompletowane(){
		return repo.searchZamowienieBySkompletowane();
	}
	public List<Zamowienie> getZamowienieByWyslane(){
		return repo.searchZamowienieByWyslane();
	}
	public List<Zamowienie> getZamowienieByZamkniete(){
		return repo.searchZamowienieByZamkniete();
	}
	
}

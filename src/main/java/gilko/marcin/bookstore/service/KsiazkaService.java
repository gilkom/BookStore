package gilko.marcin.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gilko.marcin.bookstore.model.Ksiazka;
import gilko.marcin.bookstore.repository.KsiazkaRepository;

@Service
@Transactional
public class KsiazkaService {
	@Autowired
	private KsiazkaRepository repo;
	
	public Page<Ksiazka> listAll(int pageNum, String sortField, String sortDir, String keyword){
		Pageable pageable = PageRequest.of(pageNum - 1,  5,
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
										: Sort.by(sortField).descending()
		);
		return repo.search(keyword, pageable);
	}
	
	public Page<Ksiazka> listAllKategoria(int pageNum, String sortField, String sortDir, String keyword,String kategoria){
		Pageable pageable = PageRequest.of(pageNum - 1,  5,
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
										: Sort.by(sortField).descending()
		);
		if(kategoria == "") {
			return repo.search(keyword, pageable);
		}else {		
			return repo.searchKategoria(keyword, kategoria, pageable);
		}
	}
	
	
	public List<Ksiazka> list(){
		return repo.findAll();
	}
	
	
	public void save(Ksiazka ksiazka) {
		repo.save(ksiazka);
	}
	
	public Ksiazka get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	

	
	

}

package gilko.marcin.bookstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import gilko.marcin.bookstore.model.Autor;
import gilko.marcin.bookstore.model.DetalZamowienia;
import gilko.marcin.bookstore.model.Kategoria;
import gilko.marcin.bookstore.model.Uzytkownik;
import gilko.marcin.bookstore.model.Ksiazka;
import gilko.marcin.bookstore.model.Opinia;
import gilko.marcin.bookstore.model.Wydawnictwo;
import gilko.marcin.bookstore.model.Zamowienie;
import gilko.marcin.bookstore.service.AutorService;
import gilko.marcin.bookstore.service.DetalZamowieniaService;
import gilko.marcin.bookstore.service.KategoriaService;
import gilko.marcin.bookstore.service.UzytkownikService;
import gilko.marcin.bookstore.service.KsiazkaService;
import gilko.marcin.bookstore.service.OpiniaService;
import gilko.marcin.bookstore.service.WydawnictwoService;
import gilko.marcin.bookstore.service.ZamowienieService;

@Controller
public class KsiazkaController {

	@Autowired
	private KsiazkaService service;
	@Autowired
	private KategoriaService katService;
	@Autowired
	private AutorService autService;
	@Autowired
	private WydawnictwoService wydService;
	@Autowired
	private OpiniaService opService;
	@Autowired
	private UzytkownikService klService;
	@Autowired
	private ZamowienieService zamService;
	@Autowired
	private DetalZamowieniaService detZamService;

	
	@RequestMapping("/lista_ksiazek")
	public String listaKsiazek(Model model) {
		/*List<Ksiazka> listKsiazka = service.list();
		model.addAttribute("listKsiazka", listKsiazka);
		
		List<Wydawnictwo> listWydawnictwo = wydService.list();
		model.addAttribute("listWydawnictwo", listWydawnictwo);
		return "lista_ksiazek";
		*/
		return listaKsiazekSort(model, 1, "tytul_ksiazki", "asc", "", "");
	}
	
	@RequestMapping("/lista_ksiazek/page/{pageNum}")
	public String listaKsiazekSort(Model model,
				@PathVariable(name="pageNum") int pageNum,
				@Param("sortField") String sortField,
				@Param("sortDir") String sortDir,
				@Param("keyword") String keyword,
				@Param("nazwaKategorii") String nazwaKategorii) {
	
		//Page<Ksiazka> page = service.listAll(pageNum, sortField, sortDir, keyword);
		Page<Ksiazka> page1 = service.listAllKategoria(pageNum, sortField, sortDir, keyword, nazwaKategorii);
		List<Ksiazka> listKsiazka = page1.getContent();
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page1.getTotalPages());
		model.addAttribute("totalItems", page1.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("nazwaKategorii", nazwaKategorii);
		model.addAttribute("listKsiazka", listKsiazka);
		
		List<Wydawnictwo> listWydawnictwo = wydService.list();
		model.addAttribute("listWydawnictwo", listWydawnictwo);
		
		List<Kategoria> listKategoria = katService.list();
		model.addAttribute("listKategoria", listKategoria);
		
		return "lista_ksiazek";
	}
	//-------Nowa Ksiazka----------------------
	@RequestMapping("/nowa_ksiazka")
	public String dodajKsiazke(Model model) {
		Ksiazka ksiazka = new Ksiazka();
		model.addAttribute("ksiazka", ksiazka);

		List<Kategoria> listKategoria = katService.list();
		model.addAttribute("listKategoria", listKategoria);
		
		List<Wydawnictwo> listWydawnictwo = wydService.list();
		model.addAttribute("listWydawnictwo", listWydawnictwo);
		
		List<Autor> listAutor = autService.list();
		model.addAttribute("listAutor", listAutor);
		
		Wydawnictwo wydawnictwo = new Wydawnictwo();
		model.addAttribute("wydawnictwo", wydawnictwo);
		
		return "nowa_ksiazka";
	}
	
	@RequestMapping(value="/nowa_ksiazka/save", method = RequestMethod.POST)
	public String zapiszNowaKsiazke(@Valid @ModelAttribute("ksiazka") Ksiazka ksiazka,
									BindingResult bindingResult,
									@RequestParam(value= "listaIdKategorii", required = false) Long[] listaIdKategorii, 
									@RequestParam(value= "listaIdAutorow", required = false) Long[] listaIdAutorow) {
		if(bindingResult.hasErrors()) {
			return "nowa_ksiazka";
		}else {
			//saving chosen categories and authors
			for(int i =0; i < listaIdKategorii.length; i++) {
				ksiazka.addKategoria(katService.get(listaIdKategorii[i]));
			}
			for(int j = 0; j < listaIdAutorow.length; j++) {
				ksiazka.addAutor(autService.get(listaIdAutorow[j]));
			}
			
			
			service.save(ksiazka);
			
			return "redirect:/lista_ksiazek";
		}
	}

	
	
	//-------Edytuj Ksiazke----------------------
	
	@RequestMapping(value="/edytuj_ksiazke/save", method=RequestMethod.POST)
	public String zapiszEdytowanaKsiazke(@Valid @ModelAttribute("ksiazka") Ksiazka ksiazka,
										BindingResult bindingResult,
										@RequestParam(value="zapisaneKategorie", required = false) Long[] zapisaneKategorie,
										@RequestParam(value="zapisaniAutorzy", required = false) Long[] zapisaniAutorzy) {
		if(bindingResult.hasErrors()) {
			return "edytuj_ksiazke";
		}else {
			for(int i =0; i < zapisaneKategorie.length; i++) {
				ksiazka.addKategoria(katService.get(zapisaneKategorie[i]));
			}
			for(int j= 0; j < zapisaniAutorzy.length; j++) {
				ksiazka.addAutor(autService.get(zapisaniAutorzy[j]));
			}

			
			service.save(ksiazka);

			return "redirect:/lista_ksiazek";
		}
	}
	
	@RequestMapping("/edytuj_ksiazke/{id}")
	public ModelAndView edytujKsiazke(@PathVariable(name="id") Long id) {
		ModelAndView mav = new ModelAndView("edytuj_ksiazke");
		Ksiazka ksiazka = service.get(id);
		mav.addObject("ksiazka", ksiazka);
		
		Set<Kategoria> zapisaneKategorie = ksiazka.getKategoria();
		mav.addObject("zapisaneKategorie", zapisaneKategorie);
		
		Set<Autor> zapisaniAutorzy = ksiazka.getAutor();
		mav.addObject("zapisaniAutorzy", zapisaniAutorzy);
		
		List<Kategoria> listKategoria = katService.list();
		mav.addObject("listKategoria", listKategoria);
		
		List<Wydawnictwo> listWydawnictwo = wydService.list();
		mav.addObject("listWydawnictwo", listWydawnictwo);
		
		List<Autor> listAutor = autService.list();
		mav.addObject("listAutor", listAutor);
		return mav;
	}
	
	//------------Edytuj Kategorie Ksiazki----------------------
	
	@RequestMapping(value="/edytuj_ksiazke/edytuj_kategorie_ksiazki", method=RequestMethod.POST)
	public String edytujKategorieKsiazki(@Valid @ModelAttribute("ksiazka") Ksiazka ksiazka,
										@RequestParam(value="zapisaneKategorie", required = false) Long[] zapisaneKategorie,
										@RequestParam(value="zapisaniAutorzy", required = false) Long[] zapisaniAutorzy,
										BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "edytuj_ksiazke";
		}else {
			for(int i =0; i < zapisaneKategorie.length; i++) {
				ksiazka.addKategoria(katService.get(zapisaneKategorie[i]));
			}
			for(int j= 0; j < zapisaniAutorzy.length; j++) {
				ksiazka.addAutor(autService.get(zapisaniAutorzy[j]));
			}
			service.save(ksiazka);
			Long id =ksiazka.getId_ksiazki();
			return "redirect:/edytuj_kategorie_ksiazki/" + id;
		}
	}

	
	@RequestMapping("/edytuj_kategorie_ksiazki/{id}")
	public ModelAndView edytujKategorieKsiazki(@PathVariable(name="id") Long id) {
		ModelAndView mav = new ModelAndView("edytuj_kategorie_ksiazki");
		Ksiazka ksiazka = service.get(id);
		mav.addObject("ksiazka", ksiazka);
		
		Set<Kategoria> zapisaneKategorie = ksiazka.getKategoria();
		mav.addObject("zapisaneKategorie", zapisaneKategorie);
		
		Set<Autor> zapisaniAutorzy = ksiazka.getAutor();
		mav.addObject("zapisaniAutorzy", zapisaniAutorzy);
		
		List<Kategoria> listKategoria = katService.list();
		mav.addObject("listKategoria", listKategoria);
		
		List<Wydawnictwo> listWydawnictwo = wydService.list();
		mav.addObject("listWydawnictwo", listWydawnictwo);
		
		List<Autor> listAutor = autService.list();
		mav.addObject("listAutor", listAutor);
		return mav;
	}
	
	@RequestMapping(value="/edytuj_ksiazke/edytuj_kategorie_ksiazki/save", method=RequestMethod.POST)
	public String zapiszEdytowanaKategorieKsiazki(@Valid @ModelAttribute("ksiazka") Ksiazka ksiazka,
										@RequestParam(value="zapisaneKategorie", required = false) Long[] zapisaneKategorie,
										@RequestParam(value= "listaIdKategorii", required = false) Long[] listaIdKategorii,
										@RequestParam(value="zapisaniAutorzy", required = false) Long[] zapisaniAutorzy,
										BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "edytuj_ksiazke";
		}else {
			for(int i =0; i < listaIdKategorii.length; i++) {
				ksiazka.addKategoria(katService.get(listaIdKategorii[i]));
			}
			for(int j= 0; j < zapisaniAutorzy.length; j++) {
				ksiazka.addAutor(autService.get(zapisaniAutorzy[j]));
			}
			service.save(ksiazka);
			Long id =ksiazka.getId_ksiazki();
			return "redirect:/edytuj_ksiazke/" + id;
		}
	}

	//-----------Edytuj Autorow Ksiazki------------------
	
	
		@RequestMapping(value="/edytuj_ksiazke/edytuj_autorow_ksiazki", method=RequestMethod.POST)
		public String edytujAutorowKsiazki(@Valid @ModelAttribute("ksiazka") Ksiazka ksiazka,
											@RequestParam(value="zapisaneKategorie", required = false) Long[] zapisaneKategorie,
											@RequestParam(value="zapisaniAutorzy", required = false) Long[] zapisaniAutorzy,
											BindingResult bindingResult) {
			if(bindingResult.hasErrors()) {
				return "edytuj_ksiazke";
			}else {
				for(int i =0; i < zapisaneKategorie.length; i++) {
					ksiazka.addKategoria(katService.get(zapisaneKategorie[i]));
				}
				for(int j= 0; j < zapisaniAutorzy.length; j++) {
					ksiazka.addAutor(autService.get(zapisaniAutorzy[j]));
				}
				service.save(ksiazka);
				Long id =ksiazka.getId_ksiazki();
				return "redirect:/edytuj_autorow_ksiazki/" + id;
			}
		}

		
		@RequestMapping("/edytuj_autorow_ksiazki/{id}")
		public ModelAndView edytujAutorowKsiazki(@PathVariable(name="id") Long id) {
			ModelAndView mav = new ModelAndView("edytuj_autorow_ksiazki");
			Ksiazka ksiazka = service.get(id);
			mav.addObject("ksiazka", ksiazka);
			
			Set<Kategoria> zapisaneKategorie = ksiazka.getKategoria();
			mav.addObject("zapisaneKategorie", zapisaneKategorie);
			
			Set<Autor> zapisaniAutorzy = ksiazka.getAutor();
			mav.addObject("zapisaniAutorzy", zapisaniAutorzy);
			
			List<Kategoria> listKategoria = katService.list();
			mav.addObject("listKategoria", listKategoria);
			
			List<Wydawnictwo> listWydawnictwo = wydService.list();
			mav.addObject("listWydawnictwo", listWydawnictwo);
			
			List<Autor> listAutor = autService.list();
			mav.addObject("listAutor", listAutor);
			return mav;
		}
		
		@RequestMapping(value="/edytuj_ksiazke/edytuj_autorow_ksiazki/save", method=RequestMethod.POST)
		public String zapiszEdytowanegoAutoraKsiazki(@Valid @ModelAttribute("ksiazka") Ksiazka ksiazka,
											@RequestParam(value="zapisaneKategorie", required = false) Long[] zapisaneKategorie,
											@RequestParam(value= "listaIdAutorow", required = false) Long[] listaIdAutorow,
											@RequestParam(value="zapisaniAutorzy", required = false) Long[] zapisaniAutorzy,
											BindingResult bindingResult) {
			if(bindingResult.hasErrors()) {
				return "edytuj_ksiazke";
			}else {
				for(int i =0; i < zapisaneKategorie.length; i++) {
					ksiazka.addKategoria(katService.get(zapisaneKategorie[i]));
				}
				for(int j= 0; j < listaIdAutorow.length; j++) {
					ksiazka.addAutor(autService.get(listaIdAutorow[j]));
				}
				service.save(ksiazka);
				Long id =ksiazka.getId_ksiazki();
				return "redirect:/edytuj_ksiazke/" + id;
			}
		}
	
	
	@RequestMapping("/usun_ksiazke/{id}")
	public String usunKsiazke(@PathVariable(name="id")Long id) {
		service.delete(id);
		return "redirect:/lista_ksiazek";
	}
	
	
	@RequestMapping("/wyswietl_ksiazke/{id}")
	public ModelAndView wyswietlKsiazke(@PathVariable(name="id") Long id) {
		ModelAndView mav = new ModelAndView("wyswietl_ksiazke");
		Ksiazka ksiazka = service.get(id);
		mav.addObject("ksiazka", ksiazka);
		

		Double srednia = opService.getAverageOpinia(id);
		mav.addObject("srednia", srednia);
		
		Opinia opinia = new Opinia();
		opinia.setKsiazka(ksiazka);
		mav.addObject("opinia", opinia);
		
		List<Opinia> listOpinia = opService.getBook(id);
		mav.addObject("listOpinia", listOpinia);
		
		Set<Kategoria> zapisaneKategorie = ksiazka.getKategoria();
		mav.addObject("zapisaneKategorie", zapisaneKategorie);
		
		Set<Autor> zapisaniAutorzy = ksiazka.getAutor();
		mav.addObject("zapisaniAutorzy", zapisaniAutorzy);
		
		List<Kategoria> listKategoria = katService.list();
		mav.addObject("listKategoria", listKategoria);
		
		List<Wydawnictwo> listWydawnictwo = wydService.list();
		mav.addObject("listWydawnictwo", listWydawnictwo);
		
		List<Autor> listAutor = autService.list();
		mav.addObject("listAutor", listAutor);
		return mav;
	}
	@RequestMapping("/wyswietl_ksiazke/dodaj_opinie/{id}")
	public ModelAndView wyswietlKsiazkeDodajOpinie(@PathVariable(name="id") Long id, Authentication auth) {
		ModelAndView mav = new ModelAndView("dodaj_opinie");
		
		Ksiazka ksiazka = service.get(id);
		
		String email = auth.getName();
		Uzytkownik uzytkownik = klService.getByEmail(email);

		Opinia opinia = new Opinia();
		opinia.setKsiazka(ksiazka);
		opinia.setUzytkownik(uzytkownik);
		
		mav.addObject("opinia", opinia);

		return mav;
	}
	
	@RequestMapping(value = "/wyswietl_ksiazke/dodaj_opinie/save_comment", method = RequestMethod.POST)
	public String wyswietlKsiazkeZapiszOpinie(@Valid @ModelAttribute("opinia") Opinia opinia, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "dodaj_opinie";
		}else {
			opService.save(opinia);
			Long id = opinia.getKsiazka().getId_ksiazki();
			
			return "redirect:/wyswietl_ksiazke/" + id;
		}
	}
	
	@RequestMapping(value= "/wyswietl_ksiazke/dodaj_do_koszyka", method= RequestMethod.POST)
	public String dodajDoKoszyka(@RequestParam(value="idKsiazki") Long idKsiazki,
								 @RequestParam(value="cenaZakupu") float cenaZakupu,
								 @RequestParam(value="iloscZamowiona") int iloscZamowiona,
								 Authentication auth) {
		
		String email = auth.getName();
		Long id_uzytkownika = klService.getByEmail(email).getId_uzytkownika();
		Zamowienie zamowienie = new Zamowienie();
		
		
		
		if(zamService.getZamowienieKoszyk(id_uzytkownika) == null) {

			zamowienie.setUzytkownik(klService.get(id_uzytkownika));
			zamService.save(zamowienie);
		}else {
			zamowienie = zamService.getZamowienieKoszyk(id_uzytkownika);
		}
		
		DetalZamowienia detalZamowienia = new DetalZamowienia();
		detalZamowienia.setZamowienie(zamowienie);
		detalZamowienia.setIlosc_zamowiona(iloscZamowiona);
		detalZamowienia.setKsiazka(service.get(idKsiazki));
		
		detZamService.save(detalZamowienia);

		return "redirect:/wyswietl_ksiazke/" + idKsiazki;
	}
	
	@RequestMapping("/")
	public String listaBestsellerow(Model model) {
		List<Long> idBestsellerow = detZamService.listIdBestsellers();
		List<Ksiazka> listKsiazka = new ArrayList<>();
		
		
		for(int i = 0; i < idBestsellerow.size(); i++) {
			
			Ksiazka ksiazka = service.get(idBestsellerow.get(i));
			listKsiazka.add(ksiazka);
			
			
		}
		model.addAttribute("listKsiazka", listKsiazka);
		return "index";
	}
}

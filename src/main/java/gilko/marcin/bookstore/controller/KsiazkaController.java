package gilko.marcin.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import gilko.marcin.bookstore.model.Autor;
import gilko.marcin.bookstore.model.Kategoria;
import gilko.marcin.bookstore.model.Ksiazka;
import gilko.marcin.bookstore.model.Wydawnictwo;
import gilko.marcin.bookstore.service.AutorService;
import gilko.marcin.bookstore.service.KategoriaService;
import gilko.marcin.bookstore.service.KsiazkaService;
import gilko.marcin.bookstore.service.WydawnictwoService;

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
	
	@RequestMapping("/lista_ksiazek")
	public String listaKsiazek(Model model) {
		List<Ksiazka> listKsiazka = service.list();
		model.addAttribute("listKsiazka", listKsiazka);
		return "lista_ksiazek";
	}
	
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
		
		return "nowa_ksiazka";
	}
	
	@RequestMapping(value="/nowa_ksiazka/save", method = RequestMethod.POST)
	public String zapiszNowaKsiazke(@Valid @ModelAttribute("ksiazka") Ksiazka ksiazka,
									@RequestParam(value= "listaIdKategorii", required = false) Long[] listaIdKategorii, 
									@RequestParam(value= "listaIdAutorow", required = false) Long[] listaIdAutorow, 
									BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "nowa_ksiazka";
		}else {
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
	
	@RequestMapping(value="/edytuj_ksiazke/save", method=RequestMethod.POST)
	public String zapiszEdytowanaKsiazke(@Valid @ModelAttribute("ksiazka") Ksiazka ksiazka, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "edytuj_ksiazke";
		}else {
			service.save(ksiazka);
			return "redirect:/lista_ksiazek";
		}
	}
	
	@RequestMapping("/edytuj_ksiazke/{id}")
	public ModelAndView edytujKsiazke(@PathVariable(name="id") Long id) {
		ModelAndView mav = new ModelAndView("edytuj_ksiazke");
		Ksiazka ksiazka = service.get(id);
		mav.addObject("ksiazka", ksiazka);
		
		System.out.println("kategoria: " + ksiazka.getKategoria());
		
		List<Kategoria> listKategoria = katService.list();
		mav.addObject("listKategoria", listKategoria);
		
		List<Wydawnictwo> listWydawnictwo = wydService.list();
		mav.addObject("listWydawnictwo", listWydawnictwo);
		
		List<Autor> listAutor = autService.list();
		mav.addObject("listAutor", listAutor);
		return mav;
	}
	
	@RequestMapping("/usun_ksiazke/{id}")
	public String usunKsiazke(@PathVariable(name="id")Long id) {
		service.delete(id);
		return "redirect:/lista_ksiazek";
	}
}

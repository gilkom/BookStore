package gilko.marcin.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gilko.marcin.bookstore.model.DetalZamowienia;
import gilko.marcin.bookstore.model.DetalZamowieniaId;
import gilko.marcin.bookstore.model.Uzytkownik;
import gilko.marcin.bookstore.model.Zamowienie;
import gilko.marcin.bookstore.service.DetalZamowieniaService;
import gilko.marcin.bookstore.service.UzytkownikService;
import gilko.marcin.bookstore.service.ZamowienieService;

@Controller
public class KoszykController {
	
	@Autowired
	private ZamowienieService zamService;
	@Autowired
	private UzytkownikService klService;
	@Autowired 
	DetalZamowieniaService detZamService;

	@RequestMapping("/koszyk")
	public String koszyk(Model model, Authentication auth) {
		
		List<DetalZamowienia> listDetalZamowienia = detZamService.listKoszyk(klService.getByEmail(auth.getName()).getId_uzytkownika());
		model.addAttribute("listDetalZamowienia", listDetalZamowienia);
		
		return "koszyk";
	}
	
	
	@RequestMapping("/wyswietl_zamowienie/{id}")
	public ModelAndView wyswietlZamowienie(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("wyswietl_zamowienie");
		
		Zamowienie zamowienie = zamService.get(id);
		
		List<DetalZamowienia> listDetalZamowienia = detZamService.listByIdZamowienia(id);
		mav.addObject("listDetalZamowienia",listDetalZamowienia);
		mav.addObject("zamowienie", zamowienie);
		
		return mav;
	}
	
	
	@RequestMapping("/edytuj_koszyk/{id_zamowienia}/{pozycja_zamowienia}")
	public ModelAndView edytujKoszyk(@PathVariable(name="id_zamowienia") Long id_zamowienia, @PathVariable(name="pozycja_zamowienia") Long pozycja_zamowienia) {
		ModelAndView mav = new ModelAndView("edytuj_koszyk");
		DetalZamowieniaId detalZamowieniaId = new DetalZamowieniaId(pozycja_zamowienia, zamService.get(id_zamowienia));
		DetalZamowienia detalZamowienia = detZamService.get(detalZamowieniaId);
		
		mav.addObject("detalZamowienia", detalZamowienia);
		return mav;
	}
	
	@RequestMapping(value = "/edytuj_koszyk/save", method=RequestMethod.POST)
	public String zapiszEdytowanyKoszyk(@Valid @ModelAttribute("detalZamowienia") DetalZamowienia detalZamowienia, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/edytuj_koszyk";
		}else {
			detZamService.save(detalZamowienia);
			return "redirect:/koszyk";
		}
	}
	@RequestMapping("usun_z_koszyka/{id_zamowienia}/{pozycja_zamowienia}")
	public String usunZKoszyka(@PathVariable(name="id_zamowienia") Long id_zamowienia, @PathVariable(name="pozycja_zamowienia") Long pozycja_zamowienia) {
		DetalZamowieniaId detalZamowieniaId = new DetalZamowieniaId(pozycja_zamowienia, zamService.get(id_zamowienia));
		DetalZamowienia detalZamowienia = detZamService.get(detalZamowieniaId);
		
		detZamService.delete(detalZamowienia);
		return "redirect:/koszyk";		
	}
	
	

	@RequestMapping("/moje_zamowienia")
	public String listaZamowienuzytkownika(Model model, Authentication auth) {
		
		List<Zamowienie> listZamowienie = zamService.getZamowienieNotKoszykById( klService.getByEmail(auth.getName()).getId_uzytkownika());
		model.addAttribute("listZamowienie", listZamowienie);
		return "moje_zamowienia";
	}
	
	@RequestMapping(value = "/zloz_zamowienie", method= RequestMethod.GET)
	public  String zlozZamowienie(Authentication auth) {
		Zamowienie zamowienie = zamService.getZamowienieKoszyk( klService.getByEmail(auth.getName()).getId_uzytkownika());
		
		
		if(zamowienie == null || zamowienie.getWartosc_zamowienia() == 0) {
			return "/koszyk";
		}else {
		//Zamowienie zamowienie = service.getZamowienieKoszyk( klService.getByEmail(auth.getName()).getId_uzytkownika());
		
		zamowienie.setStatus_zamowienia("ZAMÓWIONE");
		zamService.save(zamowienie);
		
		return "redirect:/moje_zamowienia";
		}
	}
	
	
	@RequestMapping("/edytuj_dane_adresowe")
	public ModelAndView edytujDaneAdresowe(Authentication auth) {
		ModelAndView mav = new ModelAndView("edytuj_dane_adresowe");
		
		Uzytkownik uzytkownik = klService.getByEmail(auth.getName());
		mav.addObject("uzytkownik", uzytkownik);
		return mav;
	}
	
	@RequestMapping(value = "/edytuj_dane_adresowe/save", method = RequestMethod.POST)
	public String zapiszDaneAdresowe(@Valid @ModelAttribute("uzytkownik") Uzytkownik uzytkownik, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/edytuj_dane_adresowe";
		}else {
			klService.save(uzytkownik);
			return "redirect:/koszyk";
		}
	}
	
}

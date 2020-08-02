package gilko.marcin.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gilko.marcin.bookstore.model.Uzytkownik;
import gilko.marcin.bookstore.service.UzytkownikService;

@Controller
public class UzytkownikController {

	@Autowired
	private UzytkownikService service;
	
	@RequestMapping("/lista_uzytkownikow")
	public String listaUzytkownikow(Model model) {
		List<Uzytkownik> listUzytkownik = service.list();
		model.addAttribute("listUzytkownik", listUzytkownik);
		return "lista_uzytkownikow";
	}
	
	@RequestMapping("/lista_klientow")
	public String listaKlientow(Model model) {
		List<Uzytkownik> listKlient = service.getByRoleUser();
		model.addAttribute("listUzytkownik", listKlient);
		return "lista_klientow";
	}
	
	@RequestMapping("/nowy_uzytkownik")
	public String dodajUzytkownika(Model model) {
		Uzytkownik uzytkownik = new Uzytkownik();
		uzytkownik.setRola("ROLE_USER");
		uzytkownik.setBlokada_konta(1);
		model.addAttribute("uzytkownik", uzytkownik);
		return "nowy_uzytkownik";
	}
	
	@RequestMapping(value = "/nowy_uzytkownik/save", method = RequestMethod.POST)
	public String zapiszNowegoUzytkownika(@Valid @ModelAttribute("uzytkownik") Uzytkownik uzytkownik, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "nowy_uzytkownik";
		}else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String rawPass = uzytkownik.getPassword();
			String encodedPass = encoder.encode(rawPass);
			uzytkownik.setPassword(encodedPass);
			service.save(uzytkownik);
			return "redirect:/lista_uzytkownikow";
		}
	}
	
	@RequestMapping("/rejestracja")
	public String zarejestruj(Model model) {
		Uzytkownik uzytkownik = new Uzytkownik();
		uzytkownik.setRola("ROLE_USER");
		uzytkownik.setBlokada_konta(1);
		model.addAttribute("uzytkownik", uzytkownik);
		return "rejestracja";
	}
	
	@RequestMapping(value = "/rejestracja/save", method = RequestMethod.POST)
	public String zapiszZarejestrowanegoKlienta(@Valid @ModelAttribute("uzytkownik") Uzytkownik uzytkownik, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "rejestracja";
		}else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String rawPass = uzytkownik.getPassword();
			String encodedPass = encoder.encode(rawPass);
			uzytkownik.setPassword(encodedPass);
			service.save(uzytkownik);
			return "redirect:/login";
		}
	}
	
	@RequestMapping("/edytuj_uzytkownika/{id}")
	public ModelAndView edytujUzytkownika(@PathVariable(name="id") Long id) {
		ModelAndView mav = new ModelAndView("edytuj_uzytkownika");
		Uzytkownik uzytkownik = service.get(id);
		mav.addObject("uzytkownik", uzytkownik);
		return mav;
	}
	
	@RequestMapping(value = "/edytuj_uzytkownika/save", method = RequestMethod.POST)
	public String zapiszEdytowanegoUzytkownika(@Valid @ModelAttribute("uzytkownik") Uzytkownik uzytkownik, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/edytuj_uzytkownika";
		}else {
			service.save(uzytkownik);
			return "redirect:/lista_uzytkownikow";
		}
	}
	

	@RequestMapping("/usun_uzytkownika/{id}")
	public String usunUzytkownika(@PathVariable(name="id") Long id) {
		service.delete(id);
		return "redirect:/lista_uzytkownikow";
	}
	
	
}

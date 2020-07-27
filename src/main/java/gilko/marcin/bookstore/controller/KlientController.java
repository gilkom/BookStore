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

import gilko.marcin.bookstore.model.Klient;
import gilko.marcin.bookstore.service.KlientService;

@Controller
public class KlientController {

	@Autowired
	private KlientService service;
	
	@RequestMapping("/lista_klientow")
	public String listaKlientow(Model model) {
		List<Klient> listKlient = service.list();
		model.addAttribute("listKlient", listKlient);
		return "lista_klientow";
	}
	
	@RequestMapping("/nowy_klient")
	public String dodajKlienta(Model model) {
		Klient klient = new Klient();
		model.addAttribute("klient", klient);
		return "nowy_klient";
	}
	
	@RequestMapping(value = "/nowy_klient/save", method = RequestMethod.POST)
	public String zapiszNowegoKlienta(@Valid @ModelAttribute("klient") Klient klient, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "nowy_klient";
		}else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String rawPass = klient.getPassword();
			String encodedPass = encoder.encode(rawPass);
			klient.setPassword(encodedPass);
			service.save(klient);
			return "redirect:/lista_klientow";
		}
	}
	
	@RequestMapping("/edytuj_klienta/{id}")
	public ModelAndView edytujKlienta(@PathVariable(name="id") Long id) {
		ModelAndView mav = new ModelAndView("edytuj_klienta");
		Klient klient = service.get(id);
		mav.addObject("klient", klient);
		return mav;
	}
	
	@RequestMapping(value = "/edytuj_klienta/save", method = RequestMethod.POST)
	public String zapiszEdytowanegoKlienta(@Valid @ModelAttribute("klient") Klient klient, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/edytuj_klienta";
		}else {
			service.save(klient);
			return "redirect:/lista_klientow";
		}
	}
	
	@RequestMapping("/usun_klienta/{id}")
	public String usunKlienta(@PathVariable(name="id") Long id) {
		service.delete(id);
		return "redirect:/lista_klientow";
	}
	
	
}

package gilko.marcin.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gilko.marcin.bookstore.model.Uzytkownik;
import gilko.marcin.bookstore.service.UzytkownikService;

@Controller
public class RejestracjaController {
	
	@Autowired
	private UzytkownikService service;
	
	@RequestMapping("/rejestracja")
	public String zarejestruj(Model model) {
		Uzytkownik uzytkownik = new Uzytkownik();
		uzytkownik.setRola("USER_ROLA");
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

}

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
import org.springframework.web.servlet.ModelAndView;

import gilko.marcin.bookstore.model.Pracownik;
import gilko.marcin.bookstore.service.PracownikService;

@Controller
public class PracownikController {

	@Autowired
	private PracownikService service;
	
	@RequestMapping("/lista_pracownikow")
	public String listaPracownikow(Model model) {
		List<Pracownik> listPracownik = service.list();
		model.addAttribute("listPracownik", listPracownik);
		return "lista_pracownikow";
	}
	
	@RequestMapping("/nowy_pracownik")
	public String dodajPracownika(Model model) {
		Pracownik pracownik = new Pracownik();
		model.addAttribute("pracownik", pracownik);
		return "nowy_pracownik";
	}
	
	@RequestMapping(value = "/nowy_pracownik/save", method = RequestMethod.POST)
	public String zapiszNowegoPracownika(@Valid @ModelAttribute("pracownik") Pracownik pracownik, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "nowy_pracownik";
		}else {
			service.save(pracownik);
			return "redirect:/lista_pracownikow";
		}
	}
	
	@RequestMapping("/edytuj_pracownika/{id}")
	public ModelAndView edytujPracownika(@PathVariable(name="id") Long id) {
		ModelAndView mav = new ModelAndView("edytuj_pracownika");
		Pracownik pracownik = service.get(id);
		mav.addObject("pracownik", pracownik);
		return mav;
	}
	
	@RequestMapping(value = "/edytuj_pracownika/save", method = RequestMethod.POST)
	public String zapiszEdytowanegoPracownik(@Valid @ModelAttribute("pracownik")Pracownik pracownik, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/edytuj_pracownika";
		}else {
			service.save(null);
			return "redirect:/lista_pracownikow";
		}
	}
	
	@RequestMapping("/usun_pracownika/{id}")
	public String usunPracownika(@PathVariable(name="id") Long id) {
		service.delete(id);
		return "redirect:/lista_pracownikow";
	}
	
	
}

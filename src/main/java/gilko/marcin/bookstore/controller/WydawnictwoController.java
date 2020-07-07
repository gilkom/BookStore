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

import gilko.marcin.bookstore.model.Wydawnictwo;
import gilko.marcin.bookstore.service.WydawnictwoService;

@Controller
public class WydawnictwoController {

	@Autowired
	private WydawnictwoService service;
	
	@RequestMapping("/lista_wydawnictw")
	public String listaWydawnictw(Model model) {
		List<Wydawnictwo> listWydawnictwo = service.list();
		model.addAttribute("listWydawnictwo", listWydawnictwo);
		return "lista_wydawnictw";
	}
	@RequestMapping("/nowe_wydawnictwo")
	public String dodajWydawnictwo(Model model) {
		Wydawnictwo wydawnictwo = new Wydawnictwo();
		model.addAttribute("wydawnictwo", wydawnictwo);
		
		return "nowe_wydawnictwo";
	}
	@RequestMapping(value = "/nowe_wydawnictwo/save", method = RequestMethod.POST)
	public String zapiszNoweWydawnictwo(@Valid @ModelAttribute("wydawnictwo") Wydawnictwo wydawnictwo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "nowe_wydawnictwo";
		}else {
		service.save(wydawnictwo);
		return "redirect:/lista_wydawnictw";
		}
	}
	@RequestMapping(value = "/edytuj_wydawnictwo/save", method = RequestMethod.POST)
	public String zapiszEdytowaneWydawnictwo(@Valid @ModelAttribute("wydawnictwo") Wydawnictwo wydawnictwo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "edytuj_wydawnictwo";
		}else {
		service.save(wydawnictwo);
		return "redirect:/lista_wydawnictw";
		}
	}
	
	@RequestMapping("/edytuj_wydawnictwo/{id}")
	public ModelAndView edytujWydawnictwo(@PathVariable(name="id")Long id) {
		ModelAndView mav = new ModelAndView("edytuj_wydawnictwo");
		Wydawnictwo wydawnictwo = service.get(id);
		mav.addObject("wydawnictwo", wydawnictwo);
		return mav;
	}
	@RequestMapping("usun_wydawnictwo/{id}")
	public String usunWydawnictwo(@PathVariable(name="id")Long id) {
		service.delete(id);
		return "redirect:/lista_wydawnictw";
	}
	
}

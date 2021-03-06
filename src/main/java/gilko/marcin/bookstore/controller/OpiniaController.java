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

import gilko.marcin.bookstore.model.Ksiazka;
import gilko.marcin.bookstore.model.Opinia;
import gilko.marcin.bookstore.model.OpiniaId;
import gilko.marcin.bookstore.service.UzytkownikService;
import gilko.marcin.bookstore.service.KsiazkaService;
import gilko.marcin.bookstore.service.OpiniaService;

@Controller
public class OpiniaController {
	@Autowired
	private OpiniaService opService;
	@Autowired
	private UzytkownikService klService;
	@Autowired
	private KsiazkaService ksService;
	
	@RequestMapping("/lista_opinii")
	public String listaOpinii(Model model) {
		List<Ksiazka> listKsiazka = ksService.list();
		List<Opinia> listOpinia = opService.list();
		model.addAttribute("listKsiazka", listKsiazka);
		model.addAttribute("listOpinia", listOpinia);
		return "lista_opinii";
	}
	@RequestMapping("/nowa_opinia")
	public String dodajOpinie(Model model) {
		Opinia opinia = new Opinia();
		model.addAttribute("opinia", opinia);
		return "nowa_opinia";
	}
	
	@RequestMapping(value ="/nowa_opinia/save", method =RequestMethod.POST)
	public String zapiszNowaOpinie(@Valid @ModelAttribute("opinia") Opinia opinia, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "nowa_opinia";
		}else {
			opService.save(opinia);
			return "redirect:/lista_opinii";
		}
	}
	
	@RequestMapping("/edytuj_opinie/{id_uzytkownika}/{id_ksiazki}")
	public ModelAndView edytujOpinie(@PathVariable(name="id_uzytkownika") Long id_uzytkownika, @PathVariable(name="id_ksiazki") Long id_ksiazki) {
		ModelAndView mav = new ModelAndView("edytuj_opinie");
		OpiniaId opiniaId= new OpiniaId(klService.get(id_uzytkownika), ksService.get(id_ksiazki));
		Opinia opinia = opService.get(opiniaId);
		
		mav.addObject("opinia", opinia);
		return mav;
	}
	
	@RequestMapping(value = "/edytuj_opinie/save", method=RequestMethod.POST)
	public String zapiszEdytowanaOpinie(@Valid @ModelAttribute("opinia") Opinia opinia, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/edytuj_opinie";
		}else {
			opService.save(opinia);
			return "redirect:/lista_opinii";
		}
	}
	
	
	@RequestMapping("usun_opinie/{id_uzytkownika}/{id_ksiazki}")
	public String usunOpinie(@PathVariable(name="id_uzytkownika") Long id_uzytkownika, @PathVariable(name="id_ksiazki") Long id_ksiazki) {
		OpiniaId opiniaId= new OpiniaId(klService.get(id_uzytkownika), ksService.get(id_ksiazki));
		Opinia opinia = opService.get(opiniaId);
		
		opService.delete(opinia);
		return "redirect:/lista_opinii";
		
	}
}

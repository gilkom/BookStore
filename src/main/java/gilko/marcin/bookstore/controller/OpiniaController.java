package gilko.marcin.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gilko.marcin.bookstore.model.Ksiazka;
import gilko.marcin.bookstore.model.Opinia;
import gilko.marcin.bookstore.service.KlientService;
import gilko.marcin.bookstore.service.KsiazkaService;
import gilko.marcin.bookstore.service.OpiniaService;

@Controller
public class OpiniaController {
	@Autowired
	private OpiniaService opService;
	@Autowired
	private KlientService klService;
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
}

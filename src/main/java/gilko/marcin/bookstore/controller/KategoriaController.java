package gilko.marcin.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gilko.marcin.bookstore.model.Kategoria;
import gilko.marcin.bookstore.service.KategoriaService;

@Controller
public class KategoriaController {

	@Autowired
	private KategoriaService service;
	
	@RequestMapping("/")
	public String listaKategorii(Model model) {
		List<Kategoria> listKategoria = service.list();
		model.addAttribute("listKategoria", listKategoria);
		return "index";
	}
	@RequestMapping("/nowa_kategoria")
	public String dodajKategorie(Model model) {
		Kategoria kategoria = new Kategoria();
		model.addAttribute("kategoria", kategoria);
		
		return "nowa_kategoria";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String zapiszKategorie(@ModelAttribute("kategoria") Kategoria kategoria) {
		service.save(kategoria);
		return "redirect:/";
	}
	
	@RequestMapping("/edytuj_kategorie/{id}")
	public ModelAndView edytujKategorie(@PathVariable(name="id")Long id) {
		ModelAndView mav = new ModelAndView("edytuj_kategorie");
		Kategoria kategoria = service.get(id);
		mav.addObject("kategoria", kategoria);
		return mav;
	}
	@RequestMapping("usun_kategorie/{id}")
	public String usunKategorie(@PathVariable(name="id")Long id) {
		service.delete(id);
		return "redirect:/";
	}
	
}

package gilko.marcin.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gilko.marcin.bookstore.model.Bk_kategoria;
import gilko.marcin.bookstore.service.KategoriaService;

@Controller
public class KategoriaController {

	@Autowired
	private KategoriaService service;
	
	@RequestMapping("/")
	public String viewKategoriaPage(Model model) {
		List<Bk_kategoria> listKategoria = service.list();
		model.addAttribute("listKategoria", listKategoria);
		return "index";
	}
	@RequestMapping("/new_kategoria")
	public String showNewKategoriaForm(Model model) {
		Bk_kategoria bk_kategoria = new Bk_kategoria();
		model.addAttribute("bk_kategoria", bk_kategoria);
		
		return "new_kategoria";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("bk_kategoria") Bk_kategoria kategoria) {
		service.save(kategoria);
		return "redirect:/";
	}
	
}

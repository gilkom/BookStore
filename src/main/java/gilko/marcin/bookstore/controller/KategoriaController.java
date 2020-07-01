package gilko.marcin.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

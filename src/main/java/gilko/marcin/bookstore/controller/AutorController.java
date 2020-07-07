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

import gilko.marcin.bookstore.model.Autor;
import gilko.marcin.bookstore.service.AutorService;

@Controller
public class AutorController {

	@Autowired
	private AutorService service;
	
	@RequestMapping("/lista_autorow")
	public String listaAutorow(Model model) {
		List<Autor> listAutor = service.list();
		model.addAttribute("listAutor", listAutor);
		return "lista_autorow";
	}
	
	@RequestMapping("/nowy_autor")
	public String dodajAutora(Model model) {
		Autor autor = new Autor();
		model.addAttribute("autor", autor);
		return "nowy_autor";
	}
	
	@RequestMapping(value="/nowy_autor/save", method = RequestMethod.POST)
	public String zapiszNowegoAutora(@Valid @ModelAttribute("autor") Autor autor, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "nowy_autor";
		}else {
			service.save(autor);
			return "redirect:/lista_autorow";
		}
	}
	
	@RequestMapping(value="/edytuj_autora/save", method=RequestMethod.POST)
	public String zapiszEdytowanegoAutora(@Valid @ModelAttribute("autor") Autor autor, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "edytuj_autora";
		}else {
			service.save(autor);
			return "redirect:/lista_autorow";
		}
	}
	
	@RequestMapping("/edytuj_autora/{id}")
	public ModelAndView edytujAutora(@PathVariable(name="id") Long id) {
		ModelAndView mav = new ModelAndView("edytuj_autora");
		Autor autor = service.get(id);
		mav.addObject("autor", autor);
		return mav;
	}
	
	@RequestMapping("/usun_autora/{id}")
	public String usunAutora(@PathVariable(name="id")Long id) {
		service.delete(id);
		return "redirect:/lista_autorow";
	}
}

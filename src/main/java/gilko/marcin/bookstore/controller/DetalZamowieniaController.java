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

import gilko.marcin.bookstore.model.DetalZamowienia;
import gilko.marcin.bookstore.model.DetalZamowieniaId;
import gilko.marcin.bookstore.service.DetalZamowieniaService;
import gilko.marcin.bookstore.service.UzytkownikService;
import gilko.marcin.bookstore.service.ZamowienieService;


@Controller
public class DetalZamowieniaController {
	
	@Autowired 
	DetalZamowieniaService service;
	
	@Autowired
	ZamowienieService zamService;
	@Autowired
	UzytkownikService klService;
	
	
	@RequestMapping("/lista_detali_zamowienia")
	public String listaDetaliZamowienia(Model model) {
		List<DetalZamowienia> listDetalZamowienia = service.list();
		model.addAttribute("listDetalZamowienia", listDetalZamowienia);
		return "lista_detali_zamowienia";
	}

	
	@RequestMapping("nowy_detal_zamowienia")
	public String dodajDetalZamowienia(Model model) {
		DetalZamowienia detalZamowienia = new DetalZamowienia();
		model.addAttribute("detalZamowienia", detalZamowienia);
		return "nowy_detal_zamowienia";
	}
	
	@RequestMapping(value = "/nowy_detal_zamowienia/save",method = RequestMethod.POST)
	public String zapiszNowyDetalZamowienia(@Valid @ModelAttribute("detalZamowienia") DetalZamowienia detalZamowienia,
											BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "nowy_detal_zamowienia";
		}else {
			service.save(detalZamowienia);
			return "redirect:/lista_detali_zamowienia";
		}
	}
	
	@RequestMapping("/edytuj_detal_zamowienia/{id_zamowienia}/{pozycja_zamowienia}")
	public ModelAndView edytujDetalZamowienia(@PathVariable(name="id_zamowienia") Long id_zamowienia, @PathVariable(name="pozycja_zamowienia") Long pozycja_zamowienia) {
		ModelAndView mav = new ModelAndView("edytuj_detal_zamowienia");
		DetalZamowieniaId detalZamowieniaId = new DetalZamowieniaId(pozycja_zamowienia, zamService.get(id_zamowienia));
		DetalZamowienia detalZamowienia = service.get(detalZamowieniaId);
		
		mav.addObject("detalZamowienia", detalZamowienia);
		return mav;
	}
	
	@RequestMapping(value = "/edytuj_detal_zamowienia/save", method=RequestMethod.POST)
	public String zapiszEdytowanyDetalZamowienia(@Valid @ModelAttribute("detalZamowienia") DetalZamowienia detalZamowienia, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/edytuj_detal_zamowienia";
		}else {
			service.save(detalZamowienia);
			return "redirect:/lista_detali_zamowienia";
		}
	}
	
	
	@RequestMapping("usun_detal_zamowienia/{id_zamowienia}/{pozycja_zamowienia}")
	public String usunDetalZamowienia(@PathVariable(name="id_zamowienia") Long id_zamowienia, @PathVariable(name="pozycja_zamowienia") Long pozycja_zamowienia) {
		DetalZamowieniaId detalZamowieniaId = new DetalZamowieniaId(pozycja_zamowienia, zamService.get(id_zamowienia));
		DetalZamowienia detalZamowienia = service.get(detalZamowieniaId);
		
		service.delete(detalZamowienia);
		return "redirect:/lista_detali_zamowienia";		
	}
}





package gilko.marcin.bookstore.controller;

import java.util.List;
import java.util.Random;

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
import gilko.marcin.bookstore.model.Zamowienie;
import gilko.marcin.bookstore.service.PracownikService;
import gilko.marcin.bookstore.service.ZamowienieService;

@Controller
public class ZamowienieController {
	
	@Autowired
	private ZamowienieService service;
	
	@Autowired
	private PracownikService prService;
	

	
	@RequestMapping("/lista_zamowien")
	public String listaZamowien(Model model) {
		List<Zamowienie> listZamowienie = service.list();
		model.addAttribute("listZamowienie", listZamowienie);
		return "lista_zamowien";
	}

	
	//-----------Nowe Zamowienie-------------
	
	@RequestMapping("/nowe_zamowienie")
	public String dodajZamowienie(Model model) {
		Zamowienie zamowienie = new Zamowienie();
		model.addAttribute("zamowienie", zamowienie);

		return "nowe_zamowienie";
	}
	
	
	
	@RequestMapping(value="/nowe_zamowienie/save", method = RequestMethod.POST)
	public String zapiszNoweZamowienie(@Valid @ModelAttribute("zamowienie") Zamowienie zamowienie,
										BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "nowe_zamowienie";
		}else {
			
			service.save(zamowienie);
			return "redirect:/lista_zamowien";
		}
	}
	
	//------------Edytuj Zamowienie---------------
	
	@RequestMapping("/edytuj_zamowienie/{id}")
	public ModelAndView edytujZamowienie(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edytuj_zamowienie");
		
		Zamowienie zamowienie = service.get(id);
		mav.addObject(zamowienie);
		
		return mav;
	}
	
	@RequestMapping(value="/edytuj_zamowienie/save", method = RequestMethod.POST)
	public String zapiszEdytowaneZamowienie(@Valid @ModelAttribute("zamowienie") Zamowienie zamowienie,
											BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "edytuj_zamowienie";
		}else {
			service.save(zamowienie);
			return "redirect:/lista_zamowien";
		}
	}
	
	@RequestMapping("/usun_zamowienie/{id}")
	public String usunZamowienie(@PathVariable(name="id") Long id) {
		service.delete(id);
		return "redirect:/lista_zamowien";
	}
}




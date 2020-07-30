package gilko.marcin.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import gilko.marcin.bookstore.model.DetalZamowienia;
import gilko.marcin.bookstore.model.Zamowienie;
import gilko.marcin.bookstore.service.DetalZamowieniaService;
import gilko.marcin.bookstore.service.ZamowienieService;

@Controller
public class ObslugaZamowienController {

	@Autowired
	private ZamowienieService zamService;
	@Autowired
	private DetalZamowieniaService detZamService;
	
	@RequestMapping("/lista_zamowien_obsluga")
	public String listaZamowienNieobsluzonych(Model model) {
		List<Zamowienie> listZamowienie =zamService.getZamowienieByNotKoszyk();
		model.addAttribute("listZamowienie", listZamowienie);
				
		return "lista_zamowien_obsluga";
	}
	
	@RequestMapping("/wyswietl_zamowienie_obsluga/{id}")
	public ModelAndView wyswietlZamowienieObsluga(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("wyswietl_zamowienie_obsluga");
		
		Zamowienie zamowienie = zamService.get(id);
		
		List<DetalZamowienia> listDetalZamowienia = detZamService.listByIdZamowienia(id);
		mav.addObject("listDetalZamowienia",listDetalZamowienia);
		mav.addObject("zamowienie", zamowienie);
		
		return mav;
	}
	
	@RequestMapping("/lista_zamowien_zamowione")
	public String listaZamowienZamowione(Model model) {
		List<Zamowienie> listZamowienie =zamService.getZamowienieByZamowione();
		model.addAttribute("listZamowienie", listZamowienie);
				
		return "lista_zamowien_zamowione";
	}
	@RequestMapping("/wyswietl_zamowienie_zamowione/{id}")
	public ModelAndView wyswietlZamowienieZamowione(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("wyswietl_zamowienie_zamowione");
		
		Zamowienie zamowienie = zamService.get(id);
		
		List<DetalZamowienia> listDetalZamowienia = detZamService.listByIdZamowienia(id);
		mav.addObject("listDetalZamowienia",listDetalZamowienia);
		mav.addObject("zamowienie", zamowienie);
		
		return mav;
	}
	
	@RequestMapping(value = "wyswietl_zamowienie_zamowione/skompletuj", method=RequestMethod.POST)
	public String skompletujZamowienie(@RequestParam(value="id_zamowienia")Long id_zamowienia) {
		Zamowienie zam = zamService.get(id_zamowienia);
		zam.setStatus_zamowienia("SKOMPLETOWANE");
		zamService.save(zam);
		return "redirect:/lista_zamowien_zamowione";
	}
	
	@RequestMapping("/lista_zamowien_skompletowane")
	public String listaZamowienSkompletowane(Model model) {
		List<Zamowienie> listZamowienie =zamService.getZamowienieBySkompletowane();
		model.addAttribute("listZamowienie", listZamowienie);
				
		return "lista_zamowien_skompletowane";
	}
	
	@RequestMapping("/wyswietl_zamowienie_skompletowane/{id}")
	public ModelAndView wyswietlZamowieniSkompletowane(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("wyswietl_zamowienie_skompletowane");
		
		Zamowienie zamowienie = zamService.get(id);
		
		List<DetalZamowienia> listDetalZamowienia = detZamService.listByIdZamowienia(id);
		mav.addObject("listDetalZamowienia",listDetalZamowienia);
		mav.addObject("zamowienie", zamowienie);
		
		return mav;
	}
	
	@RequestMapping(value = "wyswietl_zamowienie_skompletowane/wyslij", method=RequestMethod.POST)
	public String wyslijSkompletowane(@RequestParam(value="id_zamowienia")Long id_zamowienia) {
		Zamowienie zam = zamService.get(id_zamowienia);
		zam.setStatus_zamowienia("WYSŁANE");
		zamService.save(zam);
		return "redirect:/lista_zamowien_skompletowane";
	}
	
	@RequestMapping("/lista_zamowien_wyslane")
	public String listaZamowienWyslane(Model model) {
		List<Zamowienie> listZamowienie =zamService.getZamowienieByWyslane();
		model.addAttribute("listZamowienie", listZamowienie);
				
		return "lista_zamowien_wyslane";
	}
	
	@RequestMapping("/wyswietl_zamowienie_wyslane/{id}")
	public ModelAndView wyswietlZamowienieWyslane(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("wyswietl_zamowienie_wyslane");
		
		Zamowienie zamowienie = zamService.get(id);
		
		List<DetalZamowienia> listDetalZamowienia = detZamService.listByIdZamowienia(id);
		mav.addObject("listDetalZamowienia",listDetalZamowienia);
		mav.addObject("zamowienie", zamowienie);
		
		return mav;
	}
	
	@RequestMapping(value = "wyswietl_zamowienie_wyslane/zamknij", method=RequestMethod.POST)
	public String zamknijWyslane(@RequestParam(value="id_zamowienia")Long id_zamowienia) {
		Zamowienie zam = zamService.get(id_zamowienia);
		zam.setStatus_zamowienia("ZAMKNIĘTE");
		zamService.save(zam);
		return "redirect:/lista_zamowien_wyslane";
	}
	
	@RequestMapping("/lista_zamowien_zamkniete")
	public String listaZamowienZamkniete(Model model) {
		List<Zamowienie> listZamowienie =zamService.getZamowienieByZamkniete();
		model.addAttribute("listZamowienie", listZamowienie);
				
		return "lista_zamowien_zamkniete";
	}
	
	@RequestMapping("/wyswietl_zamowienie_zamkniete/{id}")
	public ModelAndView wyswietlZamowienieZamkniete(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("wyswietl_zamowienie_zamkniete");
		
		Zamowienie zamowienie = zamService.get(id);
		
		List<DetalZamowienia> listDetalZamowienia = detZamService.listByIdZamowienia(id);
		mav.addObject("listDetalZamowienia",listDetalZamowienia);
		mav.addObject("zamowienie", zamowienie);
		
		return mav;
	}
}

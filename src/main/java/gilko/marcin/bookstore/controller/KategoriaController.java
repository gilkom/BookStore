package gilko.marcin.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import gilko.marcin.bookstore.service.KategoriaService;

@Controller
public class KategoriaController {

	@Autowired
	private KategoriaService service;
}

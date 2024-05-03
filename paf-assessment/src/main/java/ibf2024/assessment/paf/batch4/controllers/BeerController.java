package ibf2024.assessment.paf.batch4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.repositories.BeerRepository;

@Controller
public class BeerController {

	@Autowired
	BeerRepository beerRepo;

	// Task 2 - view 0
	@GetMapping(path = {"/","/index"})
	public ModelAndView getView0() {
		ModelAndView mav = new ModelAndView("view0");
		List<Style> styles = beerRepo.getStyles();
		mav.addObject("styles", styles);
		return mav;
	}
	
	//TODO Task 3 - view 1
	

	//TODO Task 4 - view 2

	
	//TODO Task 5 - view 2, place order

}

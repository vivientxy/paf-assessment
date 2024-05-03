package ibf2024.assessment.paf.batch4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ibf2024.assessment.paf.batch4.models.Beer;
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
	
	// Task 3 - view 1
	@GetMapping("/beer/style/{id}")
	public ModelAndView getView1(@PathVariable Integer id, @RequestParam String styleName) {
		ModelAndView mav = new ModelAndView("view1");
		List<Beer> beers = beerRepo.getBreweriesByBeer(id);
		mav.addObject("styleName", styleName);
		mav.addObject("beers", beers);
		return mav;
	}

	//TODO Task 4 - view 2

	
	//TODO Task 5 - view 2, place order

}

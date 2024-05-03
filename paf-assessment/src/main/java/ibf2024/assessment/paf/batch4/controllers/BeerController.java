package ibf2024.assessment.paf.batch4.controllers;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.repositories.BeerRepository;
import ibf2024.assessment.paf.batch4.services.BeerService;

@Controller
public class BeerController {

	@Autowired
	private BeerRepository beerRepo;

	@Autowired
	private BeerService beerSvc;

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
	public ModelAndView getView1(@PathVariable Integer id, @RequestParam(required = false) String styleName) {
		ModelAndView mav = new ModelAndView("view1");
		List<Beer> beers = beerRepo.getBreweriesByBeer(id);
		mav.addObject("styleName", styleName);
		mav.addObject("beers", beers);
		return mav;
	}

	//Task 4 - view 2
	@GetMapping("/brewery/{id}")
	public ModelAndView getView2(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("view2");
		Optional<Brewery> brewery = beerRepo.getBeersFromBrewery(id);
		boolean breweryIsNull = true;
		if (!brewery.isEmpty()) {
			breweryIsNull = false;
			mav.addObject("brewery", brewery.get());
		}
		mav.addObject("breweryIsNull", breweryIsNull);
		return mav;
	}

	
	// Task 5 - view 2, place order
	@PostMapping("/brewery/{id}/order")
	public ModelAndView getView3(@PathVariable Integer id, @RequestBody MultiValueMap<String,String> mvm) {
		ModelAndView mav = new ModelAndView("view3");
		// >>>> mvm: {beerId=[3985, 3987, 3981, 3979, 3988, 3984, 3983, 3986, 3982, 3978, 3980], quantity=[10, , , , , , , , , , ]}

		Integer totalQuantity = mvm.get("quantity").stream()
  				.filter(Predicate.not(String::isEmpty))
				.mapToInt(i -> Integer.parseInt(i))
				.sum();

		if (totalQuantity == 0) {
			mav.setViewName("redirect:/brewery/" + id);
			return mav;
		}

		String orderId = beerSvc.placeOrder(id, mvm);
		mav.addObject("orderId", orderId);

		return mav;
	}
}

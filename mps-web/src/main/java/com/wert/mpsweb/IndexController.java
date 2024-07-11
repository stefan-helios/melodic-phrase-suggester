package com.wert.mpsweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wert.mpsweb.mps.Phrase;

// A @Controller can be used to return HTML pages
@Controller
public class IndexController {

	// Model parameter is magically filled in by the framework, and gives
	// us an object to pass parameters to the HTML template
	// Maps to root: https://localhost:8080
	@GetMapping("/")
	public String index(@RequestParam(value = "measures", defaultValue = "16") String measures, Model model) {
		// Generate a phrase and add it to the model
		var phrase = new Phrase(Integer.parseInt(measures));
		model.addAttribute("notes", phrase.toWebModel());
		model.addAttribute("measure_count", measures);
		// Return the name of the html template we want to use
		return "index";
	}
}
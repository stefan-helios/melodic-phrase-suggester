package com.wert.mpsweb;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.com.wert.mpsweb.mps.Phrase;

@RestController
public class PhraseController {

	private Logger logger = LoggerFactory.getLogger("controller.Phrase");

	private static final String template = "Hello, %s!";

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		logger.info("testing hello");
		return String.format(template, name);
	}

	@GetMapping("/generate")
	public NoteModel[] generate(@RequestParam(value = "measures", defaultValue = "16") String measures) {
		logger.info("generate called, measures = " + measures);
		var phrase = new Phrase(Integer.parseInt(measures));
		var model = phrase.toWebModel();
		logger.info("generated phrase with " + model.length + " notes", model.length);
		for (NoteModel note : model) {
			logger.info(note.note() + " " + note.duration());
		}
		return model;
	}
}
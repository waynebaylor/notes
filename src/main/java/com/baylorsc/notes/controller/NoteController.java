package com.baylorsc.notes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class NoteController extends AuthController
{
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create() {
		
		return "redirect:/home/view";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET) 
	public ModelAndView view() {
		ModelAndView m = new ModelAndView("noteView");
		
		return m;
	}
	
	@RequestMapping(value="/edit/view", method=RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView m = new ModelAndView("editNoteView");
		
		return m;
	}
	
	@RequestMapping(value="/edit/submit", method=RequestMethod.POST)
	public String editSubmit() {
		return "redirect:/home/view";
	}
}

package com.baylorsc.notes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController extends AuthController
{
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public ModelAndView view() {
		ModelAndView m = new ModelAndView("homeView");
		
		return m;
	}
	
}

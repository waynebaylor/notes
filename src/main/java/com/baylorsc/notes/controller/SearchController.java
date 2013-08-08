package com.baylorsc.notes.controller;

import java.security.Principal;

import org.jboss.logging.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/search")
public class SearchController 
{
	@RequestMapping(value="/tag", method=RequestMethod.GET)
	public ModelAndView tagSearch(Principal principal, @Param String name) {
		ModelAndView m = new ModelAndView();
		
		return m;
	}
}

package com.baylorsc.notes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baylorsc.notes.manager.UserManager;
import com.baylorsc.notes.model.User;

@Controller
@RequestMapping("/register")
public class RegisterController 
{
	@Autowired
	private StandardPasswordEncoder pwEncoder;
	
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public ModelAndView view(Model model) {
		// copy any flash attributes that were set.
		ModelAndView m = new ModelAndView("registerView");
		m.addAllObjects(model.asMap());
		
		return m;
	}
	
	@RequestMapping(value="/submit", method=RequestMethod.POST)
	public ModelAndView submit(@Valid User user, BindingResult result, RedirectAttributes flashAttrs) {
		ModelAndView m = new ModelAndView();
		
		if(result.hasErrors()) {
			m.addObject("result", result);
			
			m.setViewName("registerView");
		}
		else if(this.userManager.userExists(user)) {
			flashAttrs.addFlashAttribute("errorMessage", "Username <strong>"+user.getUsername()+"</strong> already exists.");
			
			m.setViewName("redirect:/register/view");
		}
		else {
			String pwHash = this.pwEncoder.encode(user.getPassword());
			user.setPassword(pwHash);
			this.userManager.createUser(user);

			flashAttrs.addFlashAttribute("successMessage", "You are registered!");
			flashAttrs.addFlashAttribute("j_username", user.getUsername());
			
			m.setViewName("redirect:/login/view");
		}
		
		return m;
	}
}

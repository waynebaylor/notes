package com.baylorsc.notes.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baylorsc.notes.manager.NoteManager;
import com.baylorsc.notes.manager.UserManager;
import com.baylorsc.notes.model.Note;
import com.baylorsc.notes.model.User;

@Controller
@RequestMapping("/note")
public class NoteController extends AuthController
{
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private NoteManager noteManager;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView create(Principal principal, @Valid Note note, BindingResult result, RedirectAttributes flashAttrs) {
		ModelAndView m = new ModelAndView("redirect:/home/view");
		
		if(result.hasErrors()) {
			m.addObject("result", result);
			
			flashAttrs.addFlashAttribute("errorMessage", "Save failed. Please correct the errors below.");
			
			m.setViewName("homeView");
		}
		else {
			User currentUser = this.userManager.findUser(principal.getName());
			this.noteManager.createNote(currentUser.getId(), note.getContent());
			
			flashAttrs.addFlashAttribute("successMessage","Note created.");
			
			m.setViewName("redirect:/home/view");
		}
		
		return m;
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

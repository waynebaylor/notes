package com.baylorsc.notes.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baylorsc.notes.manager.NoteManager;
import com.baylorsc.notes.manager.UserManager;
import com.baylorsc.notes.model.Note;
import com.baylorsc.notes.model.User;

@Controller
@RequestMapping("/home")
public class HomeController extends AuthController
{
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private NoteManager noteManager;
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public ModelAndView view(Principal principal) {
		ModelAndView m = new ModelAndView("homeView");
		
		User currentUser = this.userManager.findUser(principal.getName());
		List<Note> notes = this.noteManager.findAllNotes(currentUser.getId());
		m.addObject("notes", notes);
		
		return m;
	}
	
}

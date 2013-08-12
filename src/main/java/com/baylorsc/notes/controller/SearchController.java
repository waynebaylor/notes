package com.baylorsc.notes.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baylorsc.notes.manager.NoteManager;
import com.baylorsc.notes.model.Note;
import com.baylorsc.notes.model.User;

@Controller
@RequestMapping("/search")
public class SearchController extends AuthController
{
	@Autowired
	private NoteManager noteManager;
	
	@RequestMapping(value="/tag", method=RequestMethod.GET)
	public ModelAndView tagSearch(@Param String[] names) {
		ModelAndView m = new ModelAndView("searchView");
		
		User currentUser = this.getCurrentUser();
		List<Note> notes = this.noteManager.findWithAnyTag(currentUser, names);
		
		m.addObject("q", StringUtils.join(names, " "));
		m.addObject("notes", notes);
		
		return m;
	}
}

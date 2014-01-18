package com.baylorsc.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baylorsc.notes.manager.NoteManager;
import com.baylorsc.notes.model.Note;
import com.baylorsc.notes.model.User;
import com.github.rjeschke.txtmark.Processor;

@Controller
@RequestMapping("/home")
public class HomeController extends AuthController
{
	@Autowired
	private NoteManager noteManager;
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public ModelAndView view() {
		ModelAndView m = new ModelAndView("homeView");
		
		User currentUser = this.getCurrentUser();
		List<Note> notes = this.noteManager.findAllNotes(currentUser.getId());
		
		for (Note note : notes) {
			// Markdown escape to keep #tags from turning into headers
			String escapeNote = note.getContent().replaceAll("#([A-Za-z0-9-]+)", "\\\\#$1");
			String markdownContent = Processor.process(escapeNote);
			// Use regular expressions to remove html tags <**>
			String strippedMarkdown = markdownContent.replaceAll("<.*?>", "");				
			note.setContent(strippedMarkdown);			
			// End Stephanie stuff	
		}
		
		m.addObject("notes", notes);
		
		return m;
	}
	
}

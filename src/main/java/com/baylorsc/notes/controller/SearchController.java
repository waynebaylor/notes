package com.baylorsc.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.baylorsc.notes.manager.NoteManager;
import com.baylorsc.notes.model.Note;
import com.baylorsc.notes.model.User;
import com.github.rjeschke.txtmark.Processor;

@Controller
@RequestMapping("/search")
public class SearchController extends AuthController
{
	@Autowired
	private NoteManager noteManager;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView index(@RequestParam String q) {
		ModelAndView m = new ModelAndView("searchView");
		
		User currentUser = this.getCurrentUser();
		
		List<Note> notes;
		if(q.startsWith("#")) {
			notes = this.noteManager.findWithAnyTag(currentUser, q.replaceAll("#", ""));
		}
		else {
			notes = this.noteManager.findContainingPhrase(currentUser, q);	
		}
		
		for (Note note : notes) {
            // Markdown escape to keep #tags from turning into headers
            String escapeNote = note.getContent().replaceAll("#([A-Za-z0-9-]+)", "\\\\#$1");
            String markdownContent = Processor.process(escapeNote);
            // Use regular expressions to remove html tags <**>
            String strippedMarkdown = markdownContent.replaceAll("<.*?>", "");  
            // pick out the first line of text.
            String firstLine = strippedMarkdown.substring(0, strippedMarkdown.indexOf("\n"));
            
            note.setContent(firstLine);         
        }
		
		m.addObject("q", q);
		m.addObject("notes", notes);
		
		return m;
	}
}

package com.baylorsc.notes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baylorsc.notes.manager.NoteManager;
import com.baylorsc.notes.manager.TagManager;
import com.baylorsc.notes.model.Note;
import com.baylorsc.notes.model.Tag;
import com.baylorsc.notes.model.User;
import com.github.rjeschke.txtmark.Processor;

@Controller
@RequestMapping("/note")
public class NoteController extends AuthController
{
	@Autowired
	private NoteManager noteManager;
	
	@Autowired
	private TagManager tagManager;
	
	@RequestMapping(value="/create/view", method=RequestMethod.GET)
	public ModelAndView createView() {
		ModelAndView m = new ModelAndView("createNoteView");
		
		return m;
	}
	
	@RequestMapping(value="/create/submit", method=RequestMethod.POST)
	public ModelAndView create(@Valid Note note, BindingResult result, RedirectAttributes flashAttrs) {
		ModelAndView m = new ModelAndView();
		
		if(result.hasErrors()) {
			m.addObject("result", result);
			
			m.setViewName("createNoteView");
			
		}
		else {
			User currentUser = this.getCurrentUser();
			this.noteManager.createNote(currentUser.getId(), note.getContent());
			
			flashAttrs.addFlashAttribute("successMessage","Note created.");
			
			m.setViewName("redirect:/home/view");
		}
		
		return m;
	}

	@RequestMapping(value="/view", method=RequestMethod.GET) 
	public ModelAndView view(@RequestParam Long id) {
		ModelAndView m = new ModelAndView("noteView");
		
		User currentUser = this.getCurrentUser();
		Note note = this.noteManager.findNote(currentUser, id);
		
		// Markdown escape to keep #tags from turning into headers
		String escapeNote = note.getContent().replaceAll("#([A-Za-z0-9-]+)", "\\\\#$1");	
		String markdownContent = Processor.process(escapeNote);
		note.setContent(markdownContent);
		
		List<Tag> tags = this.tagManager.findNoteTags(currentUser, note.getId());
		
		m.addObject("note", note);
		m.addObject("tags", tags);
		
		return m;
	}
	
	@RequestMapping(value="/edit/view", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam Long id) {
		ModelAndView m = new ModelAndView("editNoteView");
		
		User currentUser = this.getCurrentUser();
		Note note = this.noteManager.findNote(currentUser, id);
		
		m.addObject("note", note);
		
		return m;
	}
	
	@RequestMapping(value="/edit/submit", method=RequestMethod.POST)
	public ModelAndView editSubmit(@Valid Note note, BindingResult result, RedirectAttributes flashAttrs) {
		ModelAndView m = new ModelAndView();
		
		if(result.hasErrors()) {
			m.addObject("result", result);
			
			flashAttrs.addFlashAttribute("errorMessage", "Save failed. Please correct the errors below.");
			
			m.setViewName("editNoteView");
		}
		else {
			User currentUser = this.getCurrentUser();
			this.noteManager.save(currentUser, note);
			
			flashAttrs.addFlashAttribute("successMessage","Note saved.");
			
			m.setViewName("redirect:/note/view?id="+note.getId());
		}
		
		return m;
	}
	
	@RequestMapping(value="/delete")
	public ModelAndView delete(@RequestParam Long[] noteIds, RedirectAttributes flashAttrs) {
		ModelAndView m = new ModelAndView("redirect:/home/view");
		
		User currentUser = this.getCurrentUser();
		this.noteManager.delete(currentUser, noteIds);
		
		flashAttrs.addFlashAttribute("successMessage", "Note deleted.");
		
		return m;
	}
}

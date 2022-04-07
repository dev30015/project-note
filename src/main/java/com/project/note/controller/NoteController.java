package com.project.note.controller;

import com.project.note.component.IAuthenticationFacade;
import com.project.note.entity.Note;
import com.project.note.exception.NoteNotFoundException;
import com.project.note.repository.NoteRepository;
import com.project.note.repository.UserRepository;
import com.project.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@RestController
public class NoteController {

    private final NoteService noteService;
    private final NoteRepository repository;
    private final UserRepository userRespo;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    NoteController(NoteRepository repository, NoteService noteService, UserRepository userRespo) {
        this.repository = repository;
        this.noteService= noteService;
        this.userRespo=userRespo;
    }

    /***
     * Get notes by user id
     * Only authorized user can read their own notes
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/notes")
    @PreAuthorize("#id == principal.id")
    Collection<Note> getUserNotes(@PathVariable Long id) {
        return repository.findNotesByUserId(id);
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    /**
     * Post notes via user id
     * Only authorized user can create a note
     * @param newNote
     * @return
     */
    @PreAuthorize("#id == principal.id")
    @PostMapping("/users/{id}/notes")
    Note newNote(@PathVariable Long id, @RequestBody Note newNote) {
        return repository.save(newNote);
    }


    @PreAuthorize("#id == principal.id")
    @PutMapping("/notes/{id}")
    Note replaceEmployee(@RequestBody Note newNote, @PathVariable Long id) {
        return repository.findById(id)
                .map(note -> {
                    note.setContent(newNote.getContent());
                    note.setDatePosted(Calendar.getInstance());
                    return repository.save(note);
                })
                .orElseGet(() -> {
                    newNote.setNoteID(id);
                    return repository.save(newNote);
                });
    }

    @GetMapping("/notes/pagination/{offset}/{pageSize}")
    private Page<Note> pagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Note> notesWithPagination = noteService.findNotesWithPagination(offset,pageSize);
        return notesWithPagination;
    }

    // Single item
    @Secured("ROLE_USER")
    @GetMapping("/notes/{id}")
    Note one(@PathVariable Long id) {
        System.out.println(id);
        //return "test";
        return repository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(String.valueOf(id)));
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/notes/{id}")
    void deleteNote(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

package com.project.note.controller;

import com.project.note.entity.Note;
import com.project.note.exception.NoteNotFoundException;
import com.project.note.repository.NoteRepository;
import com.project.note.service.NoteService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
public class NoteController {

    private final NoteService noteService;
    private final NoteRepository repository;

    NoteController(NoteRepository repository, NoteService noteService) {
        this.repository = repository;
        this.noteService= noteService;
    }

    @GetMapping("/notes")
    List<Note> all() {
        return repository.findAll();
    }


    @GetMapping("/notes/pagination/{offset}/{pageSize}")
    private Page<Note> pagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Note> notesWithPagination = noteService.findNotesWithPagination(offset,pageSize);
        return notesWithPagination;
    }

    @PostMapping("/notes")
    Note newNote(@RequestBody Note newNote) {
        return repository.save(newNote);
    }
    // Single item

    @GetMapping("/notes/{id}")
    Note one(@PathVariable Long id) {
        System.out.println(id);
        //return "test";
        return repository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(String.valueOf(id)));
    }

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

    @DeleteMapping("/notes/{id}")
    void deleteNote(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

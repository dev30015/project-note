package com.project.note.service;

import com.project.note.entity.Note;
import com.project.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public Page<Note> findNotesWithPagination(int offset, int pageSize) {
        Page<Note> notes = noteRepository.findAll(PageRequest.of(offset,pageSize));
        return notes;
    }
}

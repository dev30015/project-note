package com.project.note;

import com.project.note.entity.Note;
import com.project.note.entity.User;
import com.project.note.repository.NoteRepository;
import com.project.note.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class NoteTest {
        @Resource
        private NoteRepository noteRespo;

        @Resource
        private UserRepository userRespo;

        @Test
        void testNote() {
            List<Note> notes = noteRespo.findAll();
            for(Note note: notes) {
                System.out.println(note);
            }
        }

        @Test
        void testNoteById() {
            long id = 1;
            Optional<Note> note1 = noteRespo.findById(id);
            System.out.println("Note 1 is"+note1);
        }


        //save child entity by parent repository
        //test result: passed
        /*
        @Rollback(false)
        @Test
        @Transactional
        void testNewNote() {
            //adding new note as a logged in user
            Note newNote = new Note("hello I am writing a new note");
            System.out.println(newNote);
            User loggedInUser = userRespo.findByUsername("admin");
            System.out.println(loggedInUser);
            List<Note> notes = loggedInUser.getNotes();
            System.out.println("Getting user 1 notes....");
            for(Note note: notes) {
                System.out.println(note);
            }
            loggedInUser.addNote(newNote);
            userRespo.save(loggedInUser);

            System.out.println("Getting user 1 notes....");
            for(Note note: notes) {
                System.out.println(note);
            }
            //note.setUser(loggedInUser);
            //noteRespo.save(note);
        }
         */

        //@Test
        @Transactional
        @Rollback(false)
        void testNoteByNoteRespo() {
            Note newNote = new Note("hello I am writing a new note");
            User loggedInUser = userRespo.findByUsername("admin");
            newNote.setUser(loggedInUser);
            noteRespo.save(newNote);
        }

        @Test
        void getNotes() {
            for(Note note: noteRespo.findAll()) {
                System.out.println(note);
            }
        }

        @Test
        @Transactional
        void getNotesFromUsers() {
            for(Note note: userRespo.findByUsername("admin").getNotes()) {
                System.out.println(note);
            }
        }

        @Test
        @Transactional
        void updateNote() {
            Optional<Note> targetNote = noteRespo.findById(1L);
            targetNote.ifPresent( note -> {
                note.setContent("updated content");
                noteRespo.save(note);
            });

            for(Note note: userRespo.findByUsername("admin").getNotes()) {
                System.out.println(note);
            }
        }

        @Test
        @Transactional
        void deleteNote() {
            noteRespo.deleteById(1L);
            for(Note note: noteRespo.findAll()) {
                System.out.println(note);
            }
        }

        @Test
        void findNotesByUID() {
            Collection<Note> notes = noteRespo.findNotesByUserId(1L);
            for(Note note: notes) {
                System.out.println(note);
            }
        }
}

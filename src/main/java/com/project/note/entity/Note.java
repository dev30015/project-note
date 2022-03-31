package com.project.note.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name="notes")
//@Data
public class Note {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="nid")
    private long noteID;

    @Column(name="content")
    private String content;

    @Column(name="date_posted")
    private Calendar datePosted;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_uid",nullable=false)
    private User user;

    public Note() {
    }

    public Note(String content) {
        this.content = content;
        this.datePosted=Calendar.getInstance();
    }


    public long getNoteID() {
        return noteID;
    }

    public void setNoteID(long noteID) {
        this.noteID = noteID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Calendar datePosted) {
        this.datePosted = datePosted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        //experimental
        //may add twice
        //user.addNote(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(noteID, note.noteID) && Objects.equals(content, note.content) && Objects.equals(datePosted, note.datePosted) && Objects.equals(user, note.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteID, content, datePosted, user);
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteID='" + noteID + '\'' +
                ", content='" + content + '\'' +
                ", datePosted=" + datePosted +
                '}';
    }
}

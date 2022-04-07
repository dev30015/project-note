package com.project.note.repository;

import com.project.note.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    //find user who made most of the posts
    @Query(value="select * from notes n where n.user_uid=?1",nativeQuery=true)
    Collection<Note> findNotesByUserId(Long uid);
}

package com.jason.tics.dictionary.repository;

import com.jason.tics.dictionary.domain.extension.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 */
public interface NoteRepository extends JpaRepository<Note, Note.NoteId> {
    Note getNoteByWordEqualsAndUidEquals(String word, long uid);
}

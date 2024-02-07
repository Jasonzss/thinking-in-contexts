package com.jason.tics.dictionary.resource;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.dictionary.domain.extension.Note;
import com.jason.tics.dictionary.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RestController
@RequestMapping("/note/{uid}")
public class NoteResource {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/{word}")
    public ServerResponseEntity<Note> searchNote(@PathVariable String word, @PathVariable long uid){
        return ServerResponseEntity.success(noteRepository.getNoteByWordEqualsAndUidEquals(word, uid));
    }

    @PostMapping("/{word}")
    @PutMapping("/{word}")
    public ServerResponseEntity<Note> saveNote(@PathVariable long uid, @PathVariable String word,
                                               @RequestBody String note){
        return ServerResponseEntity.success(noteRepository.save(new Note(word, uid, note)));
    }

    @DeleteMapping("{word}")
    public ServerResponseEntity<Void> deleteNote(@PathVariable long uid, @PathVariable String word){
        noteRepository.deleteById(new Note.NoteId(word, uid));
        return ServerResponseEntity.success();
    }
}

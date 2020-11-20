package ru.studentsplatform.security.controllers;

import javassist.NotFoundException;
import ru.studentsplatform.security.model.Note;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/notes")
public class NotesRestController {

    public List<Note> allNotes = Stream.of(new Note(1L, "First note")
            , new Note(2L, "Second note")
            , new Note(3L, "Third note"))
            .collect(Collectors.toList());

    @GetMapping
    @PreAuthorize("hasAuthority('note:read')")
    public List<Note> getAll() {
        return allNotes;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('note:read')")
    public Note getById(@PathVariable Long id) throws NotFoundException {
        return allNotes.stream().filter(dev -> dev.getId().equals(id)).findFirst()
                .orElseThrow(() -> new NotFoundException("This user does not exist"));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('note:write')")
    public Note create(@RequestBody Note note) {
        allNotes.add(note);
        return note;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('note:write')")
    public void deleteById(@PathVariable Long id) {
        this.allNotes.removeIf(note -> note.getId().equals(id));
    }
}

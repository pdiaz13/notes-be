package com.example.demoSpringBoot.rest.controllers;


import com.example.demoSpringBoot.rest.model.Notes;
import com.example.demoSpringBoot.rest.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/notes") //Raiz de API
public class NotesController {

    @Autowired
    private NoteService noteService;

   @GetMapping
    public List<Notes> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping
    public void addNote(@RequestBody Notes note) {
        noteService.addNote(note);
    }

    @PutMapping("/{id}") //@PathVariable para obtener el valor del parámetro id
    public void updateNote(@PathVariable Long id, @RequestBody Notes note) {
        noteService.updateNoteById(id, note);
    }

    @DeleteMapping("/{id}") //@PathVariable para obtener el valor del parámetro id
    public void deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id); //llamada al metodo de servicio
    }



/*
    @GetMapping("/{id}")
    public ResponseEntity<Notes> getNoteById(@PathVariable Long id) {
        Optional<Notes> note = noteService.findById(id);
        return note.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Notes> createNote(@RequestBody Notes note) {
        Notes savedNote = noteService.save(note);
        return ResponseEntity.ok(savedNote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notes> updateNote(@PathVariable Long id, @RequestBody Notes note) {
        Optional<Notes> existingNote = noteService.findById(id);
        if (existingNote.isPresent()) {
            Notes updatedNote = noteService.save(note);
            return ResponseEntity.ok(updatedNote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
        noteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }*/

}

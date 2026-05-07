package com.ids.cursos.controller;

import com.ids.cursos.model.Instructor;
import com.ids.cursos.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/instructores")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<Instructor>> obtenerTodos() {
        List<Instructor> instructores = instructorService.obtenerTodos();
        return ResponseEntity.ok(instructores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> obtenerPorId(@PathVariable Long id) {
        Instructor instructor = instructorService.obtenerPorId(id);
        return ResponseEntity.ok(instructor);
    }

    @PostMapping
    public ResponseEntity<Instructor> crear(@RequestBody Instructor instructor) {
        Instructor instructorCreado = instructorService.crear(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> actualizar(
            @PathVariable Long id,
            @RequestBody Instructor instructorActualizado) {
        Instructor instructorActual = instructorService.actualizar(id, instructorActualizado);
        return ResponseEntity.ok(instructorActual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        instructorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
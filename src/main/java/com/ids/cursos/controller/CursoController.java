package com.ids.cursos.controller;

import com.ids.cursos.model.Curso;
import com.ids.cursos.model.NivelCurso;
import com.ids.cursos.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> obtenerTodos() {
        List<Curso> cursos = cursoService.obtenerTodos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Long id) {
        Curso curso = cursoService.obtenerPorId(id);
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> crear(@RequestBody Curso curso) {
        Curso cursoCreado = cursoService.crear(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(
            @PathVariable Long id,
            @RequestBody Curso cursoActualizado) {
        Curso cursoActual = cursoService.actualizar(id, cursoActualizado);
        return ResponseEntity.ok(cursoActual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<Curso>> obtenerPorNivel(@PathVariable NivelCurso nivel) {
        List<Curso> cursos = cursoService.obtenerPorNivel(nivel);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Curso>> obtenerPorCategoria(@PathVariable Long categoriaId) {
        List<Curso> cursos = cursoService.obtenerPorCategoria(categoriaId);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<Curso>> obtenerPorInstructor(@PathVariable Long instructorId) {
        List<Curso> cursos = cursoService.obtenerPorInstructor(instructorId);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Curso>> buscarPorNombre(@RequestParam String nombre) {
        List<Curso> cursos = cursoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(cursos);
    }
}
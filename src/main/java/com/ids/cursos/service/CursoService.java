package com.ids.cursos.service;

import com.ids.cursos.exception.BadRequestException;
import com.ids.cursos.exception.ResourceNotFoundException;
import com.ids.cursos.model.Categoria;
import com.ids.cursos.model.Curso;
import com.ids.cursos.model.Instructor;
import com.ids.cursos.model.NivelCurso;
import com.ids.cursos.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CategoriaService categoriaService;
    private final InstructorService instructorService;

    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso con ID " + id + " no encontrado"));
    }

    public Curso crear(Curso curso) {
        validarCurso(curso);

        Categoria categoria = categoriaService.obtenerPorId(curso.getCategoria().getId());
        Instructor instructor = instructorService.obtenerPorId(curso.getInstructor().getId());

        curso.setCategoria(categoria);
        curso.setInstructor(instructor);

        return cursoRepository.save(curso);
    }

    public Curso actualizar(Long id, Curso cursoActualizado) {
        Curso curso = obtenerPorId(id);

        if (cursoActualizado.getName() != null && !cursoActualizado.getName().isBlank()) {
            curso.setName(cursoActualizado.getName());
        }

        if (cursoActualizado.getDescripcion() != null) {
            curso.setDescripcion(cursoActualizado.getDescripcion());
        }

        if (cursoActualizado.getDuracionHoras() != null && cursoActualizado.getDuracionHoras() > 0) {
            curso.setDuracionHoras(cursoActualizado.getDuracionHoras());
        }

        if (cursoActualizado.getNivel() != null) {
            curso.setNivel(cursoActualizado.getNivel());
        }

        if (cursoActualizado.getCategoria() != null && cursoActualizado.getCategoria().getId() != null) {
            Categoria categoria = categoriaService.obtenerPorId(cursoActualizado.getCategoria().getId());
            curso.setCategoria(categoria);
        }

        if (cursoActualizado.getInstructor() != null && cursoActualizado.getInstructor().getId() != null) {
            Instructor instructor = instructorService.obtenerPorId(cursoActualizado.getInstructor().getId());
            curso.setInstructor(instructor);
        }

        return cursoRepository.save(curso);
    }

    public void eliminar(Long id) {
        Curso curso = obtenerPorId(id);
        cursoRepository.delete(curso);
    }

    public List<Curso> obtenerPorNivel(NivelCurso nivel) {
        if (nivel == null) {
            throw new BadRequestException("El nivel no puede ser nulo");
        }
        return cursoRepository.findByNivel(nivel);
    }

    public List<Curso> obtenerPorCategoria(Long categoriaId) {
        categoriaService.obtenerPorId(categoriaId);
        return cursoRepository.findByCategoriaId(categoriaId);
    }

    public List<Curso> obtenerPorInstructor(Long instructorId) {
        instructorService.obtenerPorId(instructorId);
        return cursoRepository.findByInstructorId(instructorId);
    }

    public List<Curso> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new BadRequestException("El campo de nombre en la busqueda no puede estar vacío");
        }
        return cursoRepository.findByNameContainingIgnoreCase(nombre);
    }

    private void validarCurso(Curso curso) {
        if (curso.getName() == null || curso.getName().isBlank()) {
            throw new BadRequestException("El nombre del curso no puede estar vacio");
        }

        if (curso.getDuracionHoras() == null || curso.getDuracionHoras() <= 0) {
            throw new BadRequestException("La duracion debe ser mayor a 0 horas");
        }

        if (curso.getNivel() == null) {
            throw new BadRequestException("El nivel del curso no puede ser nulo");
        }

        if (curso.getCategoria() == null || curso.getCategoria().getId() == null) {
            throw new BadRequestException("La categoria del curso no puede ser nula");
        }

        if (curso.getInstructor() == null || curso.getInstructor().getId() == null) {
            throw new BadRequestException("El instructor del curso no puede ser nulo");
        }
    }
}
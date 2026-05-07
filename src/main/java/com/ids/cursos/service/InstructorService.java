package com.ids.cursos.service;

import com.ids.cursos.exception.BadRequestException;
import com.ids.cursos.exception.ResourceNotFoundException;
import com.ids.cursos.model.Instructor;
import com.ids.cursos.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public List<Instructor> obtenerTodos() {
        return instructorRepository.findAll();
    }

    public Instructor obtenerPorId(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor con ID " + id + " no encontrado"));
    }

    public Instructor crear(Instructor instructor) {
        if (instructor.getName() == null || instructor.getName().isBlank()) {
            throw new BadRequestException("El nombre del instructor no puede estar vacío");
        }

        if (instructor.getEmail() == null || instructor.getEmail().isBlank()) {
            throw new BadRequestException("El email del instructor no puede estar vacío");
        }

        if (instructorRepository.existsByEmail(instructor.getEmail())) {
            throw new BadRequestException("Ya existe un instructor con el email: " + instructor.getEmail());
        }

        return instructorRepository.save(instructor);
    }

    public Instructor actualizar(Long id, Instructor instructorActualizado) {
        Instructor instructor = obtenerPorId(id);

        if (instructorActualizado.getName() != null && !instructorActualizado.getName().isBlank()) {
            instructor.setName(instructorActualizado.getName());
        }

        if (instructorActualizado.getEspecialidad() != null) {
            instructor.setEspecialidad(instructorActualizado.getEspecialidad());
        }

        if (instructorActualizado.getEmail() != null && !instructorActualizado.getEmail().isBlank()) {
            if (!instructor.getEmail().equals(instructorActualizado.getEmail())
                    && instructorRepository.existsByEmail(instructorActualizado.getEmail())) {
                throw new BadRequestException("Ya existe un instructor con el email: " + instructorActualizado.getEmail());
            }
            instructor.setEmail(instructorActualizado.getEmail());
        }

        return instructorRepository.save(instructor);
    }

    public void eliminar(Long id) {
        Instructor instructor = obtenerPorId(id);
        instructorRepository.delete(instructor);
    }
}
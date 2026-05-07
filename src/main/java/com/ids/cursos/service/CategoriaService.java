package com.ids.cursos.service;

import com.ids.cursos.exception.BadRequestException;
import com.ids.cursos.exception.ResourceNotFoundException;
import com.ids.cursos.model.Categoria;
import com.ids.cursos.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría con ID " + id + " no encontrada"));
    }

    public Categoria crear(Categoria categoria) {
        if (categoria.getName() == null || categoria.getName().isBlank()) {
            throw new BadRequestException("El nombre de la categoría no puede estar vacío");
        }

        if (categoriaRepository.existsByName(categoria.getName())) {
            throw new BadRequestException("Ya existe una categoría con el nombre: " + categoria.getName());
        }

        return categoriaRepository.save(categoria);
    }

    public Categoria actualizar(Long id, Categoria categoriaActualizada) {
        Categoria categoria = obtenerPorId(id);

        if (categoriaActualizada.getName() != null && !categoriaActualizada.getName().isBlank()) {
            categoria.setName(categoriaActualizada.getName());
        }

        if (categoriaActualizada.getDescripcion() != null) {
            categoria.setDescripcion(categoriaActualizada.getDescripcion());
        }

        return categoriaRepository.save(categoria);
    }

    public void eliminar(Long id) {
        Categoria categoria = obtenerPorId(id);
        categoriaRepository.delete(categoria);
    }
}
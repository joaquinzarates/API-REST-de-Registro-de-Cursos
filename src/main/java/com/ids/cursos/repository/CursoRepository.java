package com.ids.cursos.repository;

import com.ids.cursos.model.Curso;
import com.ids.cursos.model.NivelCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByNivel(NivelCurso nivel);

    List<Curso> findByCategoriaId(Long categoriaId);

    List<Curso> findByInstructorId(Long instructorId);

    List<Curso> findByNameContainingIgnoreCase(String name);

    boolean existsByCategoriaId(Long categoriaId);

    boolean existsByInstructorId(Long instructorId);
}
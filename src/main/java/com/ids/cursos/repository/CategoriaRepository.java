package com.ids.cursos.repository;

import com.ids.cursos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByName(String name);

    boolean existsByName(String name);
}

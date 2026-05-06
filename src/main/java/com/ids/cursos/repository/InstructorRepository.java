package com.ids.cursos.repository;


import com.ids.cursos.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Instructor findByEmail(String email);

    boolean existsByEmail(String email);

    Instructor findByName(String name);
}
package com.springstudy.repository;

import com.springstudy.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    void save(Student student);
    List<Student> getAll();
    Optional<Student> getById(Long id);
    void delete(Student student);
    void update(Student student);

}

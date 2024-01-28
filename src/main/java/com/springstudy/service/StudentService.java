package com.springstudy.service;

import com.springstudy.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    void save(Student student);
    List<Student> getAll();
    Student getById(Long id);
    void delete(Long id);

}

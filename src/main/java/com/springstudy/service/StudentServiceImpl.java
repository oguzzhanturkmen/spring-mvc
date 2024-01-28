package com.springstudy.service;

import com.springstudy.domain.Student;
import com.springstudy.exception.StudentNotFound;
import com.springstudy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    @Qualifier("studentRepositoryImpl")
    private StudentRepository studentRepository;

    @Override
    public void save(Student student) {
        studentRepository.save(student);

    }

    @Override
    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    @Override
    public Student getById(Long id) {
        Student student = studentRepository.getById(id).orElseThrow(() -> new StudentNotFound("Student not found"));
        return student;
    }

    @Override
    public void delete(Long id) {
        Student student = getById(id);
        studentRepository.delete(student);

    }
}

package com.springstudy.repository;

import com.springstudy.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class StudentRepositoryImpl implements StudentRepository{

    @Autowired
private SessionFactory sessionFactory;
    @Override
    public void save(Student student) {
        Student student1 = new Student();
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setGrade(student.getGrade());


        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(student1);
        transaction.commit();
        session.close();

    }

    @Override
    public List<Student> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Student> list = session.createQuery("from Student", Student.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public Optional<Student> getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Student> student = Optional.ofNullable(session.get(Student.class, id));
        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public void delete(Student student ){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(student);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Student student) {

    }


}

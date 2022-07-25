package com.example.onetomanyex.repository;


import com.example.onetomanyex.entity.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveStudent(Student student){

        entityManager.persist(student);
    }

    @Transactional
    public void updateStudent(Student student){
        entityManager.merge(student);
    }

    @Transactional
    public Student deleteStudent(Student student){

        entityManager.remove(student);
        return student;
    }


    @Transactional
    public int deleteAll(){
        Query query = entityManager.createQuery("delete from University");
        return query.executeUpdate();
    }

    public Optional<Student> findById(Long id){
        return Optional.of(entityManager.find(Student.class,id));
    }

    public List<Student> findAll(){
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }

}

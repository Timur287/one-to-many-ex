package com.example.onetomanyex.repository;

import com.example.onetomanyex.entity.Student;
import com.example.onetomanyex.entity.University;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class UniversityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveUniversity(University university){

        entityManager.persist(university);
    }

    @Transactional
    public void updateUniversity(University university){
        entityManager.merge(university);
    }

    @Transactional
    public void deleteUniversity(University university){

        entityManager.remove(university);
    }

    @Transactional
    public int deleteAll(){
        Query query = entityManager.createQuery("delete from University");
        return query.executeUpdate();
    }

    public Optional<University> findById(Long id){
        return Optional.of(entityManager.find(University.class,id));
    }

    public List<University> findAll(){

        TypedQuery<University> query = entityManager.createQuery("select u from University u", University.class);
        return query.getResultList();
    }

    @Transactional
    public List<Student> addStudentToUniversity(Long studentId, Long universityId){

        University university = entityManager.find(University.class, universityId);

        Student student = entityManager.find(Student.class, studentId);
        university.addStudent(student);
        entityManager.merge(university);

        return university.getStudents();
    }

    @Transactional
    public List<Student> deleteStudentFromUniversity(Long studentId, Long universityId){

        University university = entityManager.find(University.class, universityId);
        Student student = entityManager.find(Student.class, studentId);

        university.removeStudent(student);
        entityManager.merge(university);

        return university.getStudents();
    }
}

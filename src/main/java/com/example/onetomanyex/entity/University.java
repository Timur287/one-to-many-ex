package com.example.onetomanyex.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Long numberOfStudents;

    String address;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="student_university",
            joinColumns = @JoinColumn (name = "university_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    List<Student> students = new ArrayList<>();

    public void addStudent(Student student){
        students.add(student);
        student.getUniversities().add(this);
    }

    public void removeStudent(Student student){
        students.remove(student);
        student.getUniversities().remove(this);
    }
}

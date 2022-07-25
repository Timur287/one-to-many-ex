package com.example.onetomanyex.entity;

import com.example.onetomanyex.entity.University;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String surname;

    Integer course;

    String sex;

    Double avGrade;

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    List<University> universities = new ArrayList<>();


    public void addUniversity(University university){
        universities.add(university);
        university.getStudents().add(this);
    }

    public void removeUniversity(University university){
        universities.remove(university);
        university.getStudents().remove(this);
    }
}

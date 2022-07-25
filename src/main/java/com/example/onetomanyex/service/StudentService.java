package com.example.onetomanyex.service;

import com.example.onetomanyex.dto.StudentDto;
import com.example.onetomanyex.entity.Student;
import com.example.onetomanyex.exception.NotFoundException;
import com.example.onetomanyex.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentDto saveStudent(@Valid StudentDto studentDto) {

        Student student = Student.builder()
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .course(studentDto.getCourse())
                .sex(studentDto.getSex())
                .avGrade(studentDto.getAvGrade())
                .build();

        studentRepository.saveStudent(student);
        return studentDto;
    }


    public StudentDto updateStudent(@Valid StudentDto studentDto) {

        Student student = Student.builder()
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .course(studentDto.getCourse())
                .sex(studentDto.getSex())
                .avGrade(studentDto.getAvGrade())
                .build();

        studentRepository.updateStudent(student);
        return studentDto;
    }

    public StudentDto deleteStudent(StudentDto studentDto) {

        Student student = Student.builder()
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .course(studentDto.getCourse())
                .sex(studentDto.getSex())
                .avGrade(studentDto.getAvGrade())
                .build();

        studentRepository.deleteStudent(student);

        return studentDto;
    }

    public int deleteAll() {
        return studentRepository.deleteAll();
    }

    public Optional<Student> findById(Long id) {

        return studentRepository.findById(id);
    }

    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(student -> StudentDto.builder()
                .name(student.getName())
                .surname(student.getSurname())
                .sex(student.getSex())
                .course(student.getCourse())
                .avGrade(student.getAvGrade())
                .build()
        ).collect(Collectors.toList());
    }
}

package com.example.onetomanyex.controller;

import com.example.onetomanyex.dto.StudentDto;
import com.example.onetomanyex.entity.Student;
import com.example.onetomanyex.exception.NotFoundException;
import com.example.onetomanyex.repository.StudentRepository;
import com.example.onetomanyex.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save")
    public StudentDto saveStudent(@RequestBody StudentDto studentDto){
        return studentService.saveStudent(studentDto);
    }

    @PutMapping("/update")
    public StudentDto updateStudent(@RequestBody StudentDto studentDto){
        return studentService.updateStudent(studentDto);
    }

    @DeleteMapping("/deleteById/{student_id}")
    public StudentDto deleteById(@PathVariable("student_id") @Min(1) Long id){
        StudentDto studentDto = findStudentById(id);
        return studentService.deleteStudent(studentDto);
    }

    @DeleteMapping("/deleteAll")
    public int deleteAll(){
        return studentService.deleteAll();
    }

    @GetMapping("/findById/{student_id}")
    public StudentDto findStudentById(@PathVariable("student_id") @Min(1) Long id){
        Student student = studentService.findById(id).orElseThrow(() -> new NotFoundException("University with specified id not found"));

        return StudentDto.builder()
                .name(student.getName())
                .surname(student.getSurname())
                .sex(student.getSex())
                .course(student.getCourse())
                .avGrade(student.getAvGrade())
                .build();
    }

    @GetMapping("/findAll")
    public List<StudentDto> findAll(){
        return studentService.findAll();
    }


}

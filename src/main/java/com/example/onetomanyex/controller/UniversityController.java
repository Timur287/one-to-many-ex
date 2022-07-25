package com.example.onetomanyex.controller;

import com.example.onetomanyex.dto.StudentDto;
import com.example.onetomanyex.dto.UniversityDto;
import com.example.onetomanyex.entity.University;
import com.example.onetomanyex.exception.NotFoundException;
import com.example.onetomanyex.repository.UniversityRepository;
import com.example.onetomanyex.service.UniversityService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/university")
@AllArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @PostMapping("/save")
    public UniversityDto saveUniversity(@RequestBody UniversityDto universityDto){
        return universityService.saveUniversity(universityDto);
    }

    @PutMapping("/update")
    public UniversityDto updateUniversity(@RequestBody UniversityDto universityDto){
        return universityService.updateUniversity(universityDto);
    }

    @DeleteMapping("/deleteById/{university_id}")
    public UniversityDto deleteById(@PathVariable("university_id") @Min(1) Long id){
        UniversityDto universityDto = findUniversityById(id);
        return universityService.deleteUniversity(universityDto);
    }

    @DeleteMapping("/deleteAll")
    public int deleteAll(){
        return universityService.deleteAll();
    }

    @GetMapping("/findById/{university_id}")
    public UniversityDto findUniversityById(@PathVariable("university_id") @Min(1)Long id){

        University university = universityService.findUniversityById(id)
                .orElseThrow(()->new NotFoundException("University with specified id not found"));

        return UniversityDto.builder()
                .address(university.getAddress())
                .name(university.getName())
                .numberOfStudents(university.getNumberOfStudents())
                .build();
    }

    @GetMapping("/findAll")
    public List<UniversityDto> findAll(){
        return universityService.findAll();
    }

    @PatchMapping("/addStudentToUniversity/{student_id}/{university_id}")
    public List<StudentDto> addStudentToUniversity(@PathVariable("student_id") @Min(1) Long studentId, @PathVariable("university_id") @Min(1) Long universityId){
        return universityService.addStudentToUniversity(studentId,universityId);
    }

    @PatchMapping("/deleteStudentFromUniversity/{student_id}/{university_id}")
    public List<StudentDto> deleteStudentFromUniversity(@PathVariable("student_id") @Min(1) Long studentId, @PathVariable("university_id") @Min(1) Long universityId){
        return universityService.deleteStudentFromUniversity(studentId,universityId);
    }
}


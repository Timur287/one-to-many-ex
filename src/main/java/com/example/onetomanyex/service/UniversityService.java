package com.example.onetomanyex.service;

import com.example.onetomanyex.dto.StudentDto;
import com.example.onetomanyex.dto.UniversityDto;
import com.example.onetomanyex.entity.University;
import com.example.onetomanyex.exception.NotFoundException;
import com.example.onetomanyex.repository.UniversityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityDto saveUniversity(@Valid UniversityDto universityDto){

        University university = University.builder()
                .address(universityDto.getAddress())
                .name(universityDto.getName())
                .numberOfStudents(universityDto.getNumberOfStudents())
                .build();

        universityRepository.saveUniversity(university);
        return universityDto;
    }


    public UniversityDto updateUniversity(@Valid UniversityDto universityDto){
        University university = University.builder()
                .address(universityDto.getAddress())
                .name(universityDto.getName())
                .numberOfStudents(universityDto.getNumberOfStudents())
                .build();
        universityRepository.updateUniversity(university);
        return universityDto;
    }

    public UniversityDto deleteUniversity(UniversityDto universityDto){

        University university = University.builder()
                .address(universityDto.getAddress())
                .name(universityDto.getName())
                .numberOfStudents(universityDto.getNumberOfStudents())
                .build();

        universityRepository.deleteUniversity(university);
        return universityDto;
    }

    public int deleteAll(){
        return universityRepository.deleteAll();
    }

    public Optional<University> findUniversityById(Long id){
        Optional<University> university = universityRepository.findById(id);
        return university;
    }

    public List<UniversityDto> findAll(){
        return universityRepository.findAll().stream().map(university ->
                UniversityDto.builder()
                        .name(university.getName())
                        .numberOfStudents(university.getNumberOfStudents())
                        .address(university.getAddress())
                        .build()
        ).collect(Collectors.toList());
    }

    public List<StudentDto> addStudentToUniversity(Long studentId, Long universityId){

        return universityRepository.addStudentToUniversity(studentId,universityId).stream().map(stdnt->StudentDto.builder()
                .name(stdnt.getName())
                .surname(stdnt.getSurname())
                .avGrade(stdnt.getAvGrade())
                .sex(stdnt.getSex())
                .course(stdnt.getCourse())
                .build()
        ).collect(Collectors.toList());
    }

    public List<StudentDto> deleteStudentFromUniversity(Long studentId, Long universityId) {
        return universityRepository.deleteStudentFromUniversity(studentId,universityId).stream().map(stdnt->StudentDto.builder()
                .name(stdnt.getName())
                .surname(stdnt.getSurname())
                .avGrade(stdnt.getAvGrade())
                .sex(stdnt.getSex())
                .course(stdnt.getCourse())
                .build()
        ).collect(Collectors.toList());
    }
}

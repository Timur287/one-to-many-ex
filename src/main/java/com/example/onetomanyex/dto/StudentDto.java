package com.example.onetomanyex.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class StudentDto {

    @NotBlank
    String name;

    @NotBlank
    String surname;

    @Min(1)
    @Max(5)
    Integer course;

    @NotBlank
    String sex;

    @Min(2)
    @Max(10)
    Double avGrade;
}

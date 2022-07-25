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
public class UniversityDto {

    @NotBlank
    String name;

    @Min(100)
    @Max(100000)
    Long numberOfStudents;

    @NotBlank
    String address;


}

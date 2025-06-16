package com.main.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private LocalDate dob;
    private List<CourseDTO> courses;
    private Integer courseCount;
    private Double gpa;
    private String rank;
} 
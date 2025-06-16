package com.main.app.service;

import com.main.app.dto.CourseDTO;
import com.main.app.entity.Course;
import com.main.app.entity.Student;
import com.main.app.repository.CourseRepository;
import com.main.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public CourseDTO getCourseById(Integer id) {
        Course course = courseRepository.findById(id).orElse(null);
        return course != null ? convertToDTO(course) : null;
    }
    
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }
    
    public CourseDTO updateCourse(Integer id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            course.setName(courseDTO.getName());
            course.setScore(courseDTO.getScore());
            Course updatedCourse = courseRepository.save(course);
            return convertToDTO(updatedCourse);
        }
        return null;
    }
    
    public boolean deleteCourse(Integer id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<CourseDTO> searchCoursesByName(String name) {
        return courseRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<CourseDTO> getCoursesByScore(Float score) {
        return courseRepository.findByScore(score).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private CourseDTO convertToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setScore(course.getScore());
        dto.setStudentId(course.getStudent().getId());
        return dto;
    }
    
    private Course convertToEntity(CourseDTO dto) {
        Course course = new Course();
        course.setId(dto.getId());
        course.setName(dto.getName());
        course.setScore(dto.getScore());
        
        Student student = studentRepository.findById(dto.getStudentId()).orElse(null);
        course.setStudent(student);
        
        return course;
    }
} 
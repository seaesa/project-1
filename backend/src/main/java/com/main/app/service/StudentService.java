package com.main.app.service;

import com.main.app.dto.StudentDTO;
import com.main.app.dto.CourseDTO;
import com.main.app.entity.Student;
import com.main.app.entity.Course;
import com.main.app.repository.StudentRepository;
import com.main.app.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public StudentDTO getStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        return student != null ? convertToDTO(student) : null;
    }
    
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }
    
    public StudentDTO updateStudent(Integer id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setName(studentDTO.getName());
            student.setAddress(studentDTO.getAddress());
            student.setPhone(studentDTO.getPhone());
            student.setDob(studentDTO.getDob());
            Student updatedStudent = studentRepository.save(student);
            return convertToDTO(updatedStudent);
        }
        return null;
    }
    
    public boolean deleteStudent(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<StudentDTO> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<CourseDTO> getStudentCourses(Integer studentId) {
        List<Course> courses = courseRepository.findByStudent_IdOrderByScoreDesc(studentId);
        return courses.stream()
                .map(this::convertCourseToDTO)
                .collect(Collectors.toList());
    }
    
    private StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAddress(student.getAddress());
        dto.setPhone(student.getPhone());
        dto.setDob(student.getDob());
        
        // Get courses and calculate analytics
        List<Course> courses = courseRepository.findByStudent_IdOrderByScoreDesc(student.getId());
        dto.setCourses(courses.stream().map(this::convertCourseToDTO).collect(Collectors.toList()));
        dto.setCourseCount(courses.size());
        
        if (!courses.isEmpty()) {
            double totalScore = courses.stream().mapToDouble(Course::getScore).sum();
            double gpa = totalScore / courses.size();
            dto.setGpa(gpa);
            dto.setRank(calculateRank(gpa));
        } else {
            dto.setGpa(0.0);
            dto.setRank("Chưa có điểm");
        }
        
        return dto;
    }
    
    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setAddress(dto.getAddress());
        student.setPhone(dto.getPhone());
        student.setDob(dto.getDob());
        return student;
    }
    
    private CourseDTO convertCourseToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setScore(course.getScore());
        dto.setStudentId(course.getStudent().getId());
        return dto;
    }
    
    private String calculateRank(double gpa) {
        if (gpa >= 9.0) return "Xuất sắc";
        if (gpa >= 8.0) return "Giỏi";
        if (gpa >= 7.0) return "Khá";
        if (gpa >= 5.0) return "Trung bình";
        return "Yếu";
    }
} 
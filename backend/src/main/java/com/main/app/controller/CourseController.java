package com.main.app.controller;

import com.main.app.dto.CourseDTO;
import com.main.app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Integer id) {
        CourseDTO course = courseService.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO savedCourse = courseService.saveCourse(courseDTO);
        return ResponseEntity.ok(savedCourse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Integer id, @RequestBody CourseDTO courseDTO) {
        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);
        if (updatedCourse != null) {
            return ResponseEntity.ok(updatedCourse);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<CourseDTO>> searchCourses(@RequestParam String name) {
        List<CourseDTO> courses = courseService.searchCoursesByName(name);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/score/{score}")
    public ResponseEntity<List<CourseDTO>> getCoursesByScore(@PathVariable Float score) {
        List<CourseDTO> courses = courseService.getCoursesByScore(score);
        return ResponseEntity.ok(courses);
    }
} 